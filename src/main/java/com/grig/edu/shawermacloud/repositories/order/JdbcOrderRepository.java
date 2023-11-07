package com.grig.edu.shawermacloud.repositories.order;

import com.grig.edu.shawermacloud.models.IngredientRef;
import com.grig.edu.shawermacloud.models.Shawerma;
import com.grig.edu.shawermacloud.models.ShawermaOrder;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final String ORDER_SAVE = "insert into Shawerma_Order "
            + "(delivery_name, delivery_street, delivery_city, "
            + "delivery_state, delivery_zip, cc_number, "
            + "cc_expiration, cc_cvv, placed_at) "
            + "values (?,?,?,?,?,?,?,?,?)";

    private final String SHAWERMA_SAVE = "insert into Shawerma "
            + "(name, created_at, shawerma_order_id, shawerma_order_key) "
            + "values (?, ?, ?, ?)";

    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    @Override
    public Optional<ShawermaOrder> save(ShawermaOrder order) {
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(ORDER_SAVE,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
                );
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());
        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                order.getDeliveryName(),
                                order.getDeliveryStreet(),
                                order.getDeliveryCity(),
                                order.getDeliveryState(),
                                order.getDeliveryZip(),
                                order.getCcNumber(),
                                order.getCcExpiration(),
                                order.getCcCVV(),
                                order.getPlacedAt()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        int i = 0;
        for (var shawerma : order.getShawermaList()) {
            saveShawerma(orderId, i++, shawerma);
        }
        return Optional.of(order);
    }

    private long saveShawerma(long orderId, int orderKey, Shawerma shawerma) {
        shawerma.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        SHAWERMA_SAVE,
                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
                );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                shawerma.getName(),
                                shawerma.getCreatedAt(),
                                orderId,
                                orderKey));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long shawermaId = keyHolder.getKey().longValue();
        shawerma.setId(shawermaId);

        saveIngredientRefs(shawermaId, shawerma.getIngredients());

        return shawermaId;
    }

    private void saveIngredientRefs(long shawermaId, List<IngredientRef> ingredientRefs) {
        int key = 0;
        for (var ingredientRef : ingredientRefs) {
            jdbcOperations.update(
                    "insert into Ingredient_Ref (ingredient_id, shawerma, shawerma_key) "
                            + "values (?, ?, ?)",
                    ingredientRef.getIngredient(), shawermaId, key++);
        }
    }

    @Override
    public Optional<ShawermaOrder> findById(Long id) {
        try {
            ShawermaOrder order = jdbcOperations.queryForObject(
                    "select id, delivery_name, delivery_street, delivery_city, "
                            + "delivery_state, delivery_zip, cc_number, cc_expiration, "
                            + "cc_cvv, placed_at from ShawermaOrder_Order where id=?",
                    (row, rowNum) -> {
                        ShawermaOrder shawaOrder = new ShawermaOrder();
                        shawaOrder.setId(row.getLong("id"));
                        shawaOrder.setDeliveryName(row.getString("delivery_name"));
                        shawaOrder.setDeliveryStreet(row.getString("delivery_street"));
                        shawaOrder.setDeliveryCity(row.getString("delivery_city"));
                        shawaOrder.setDeliveryState(row.getString("delivery_state"));
                        shawaOrder.setDeliveryZip(row.getString("delivery_zip"));
                        shawaOrder.setCcNumber(row.getString("cc_number"));
                        shawaOrder.setCcExpiration(row.getString("cc_expiration"));
                        shawaOrder.setCcCVV(row.getString("cc_cvv"));
                        shawaOrder.setPlacedAt(new Date(row.getTimestamp("placed_at").getTime()));
                        shawaOrder.setShawermaList(findShawermaListByOrderId(row.getLong("id")));
                        return shawaOrder;
                    }, id);
            return Optional.of(order);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    private List<Shawerma> findShawermaListByOrderId(long orderId) {
        return jdbcOperations.query(
                "select id, name, created_at from Shawerma "
                        + "where shawerma_order_id=? order by shawerma_order_key",
                (row, rowNum) -> {
                    Shawerma shawerma = new Shawerma();
                    shawerma.setId(row.getLong("id"));
                    shawerma.setName(row.getString("name"));
                    shawerma.setCreatedAt(new Date(row.getTimestamp("created_at").getTime()));
                    shawerma.setIngredients(findIngredientsByShawermaId(row.getLong("id")));
                    return shawerma;
                },
                orderId);
    }

    private List<IngredientRef> findIngredientsByShawermaId(long shawermaId) {
        return jdbcOperations.query(
                "select ingredient_id from Ingredient_Ref "
                        + "where shawerma = ? order by shawerma_key",
                (row, rowNum) -> {
                    return new IngredientRef(row.getString("ingredient"));
                },
                shawermaId);
    }
}
