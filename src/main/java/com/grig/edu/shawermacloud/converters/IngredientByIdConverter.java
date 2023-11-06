package com.grig.edu.shawermacloud.converters;

import com.grig.edu.shawermacloud.models.Ingredient;
import com.grig.edu.shawermacloud.repositories.InMemoryIngredients;
import com.grig.edu.shawermacloud.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientsRepo;
    private Map<String, Ingredient> map;

    public IngredientByIdConverter(@Qualifier("inMemoryIngredients") IngredientRepository ingredientsRepo) {
        this.ingredientsRepo = ingredientsRepo;
    }

    @Override
    public Ingredient convert(String id) {
        map = ingredientsRepo.findAll()
                .stream()
                .collect(Collectors.toMap(Ingredient::getId, i -> i));
        return map.get(id);
    }
}
