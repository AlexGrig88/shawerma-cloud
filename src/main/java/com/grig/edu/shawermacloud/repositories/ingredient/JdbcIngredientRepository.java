package com.grig.edu.shawermacloud.repositories.ingredient;

import com.grig.edu.shawermacloud.models.Ingredient;
import com.grig.edu.shawermacloud.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository  {

    private static final String SELECT_ALL = "SELECT id, name, ru_name, type FROM ingredient" ;
    private static final String SELECT_BY_ID = "SELECT id, name, ru_name, type FROM ingredient WHERE id=?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query(SELECT_ALL, this::mapRow);
    }

    @Override
    public Optional<Ingredient> findById(Long id) {
        var ingredients = jdbcTemplate.query(SELECT_BY_ID, this::mapRow, id);
        return ingredients.size() == 0 ?
                Optional.empty() :
                Optional.of(ingredients.get(0));
    }

    @Override
    public Optional<Ingredient> save(Ingredient entity) {
        return IngredientRepository.super.save(entity);
    }

    private Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(
          rs.getString("id"),
          rs.getString("name"),
          rs.getString("ru_name"),
          Ingredient.Type.valueOf(rs.getString("type"))
        );
    }
}
