package com.grig.edu.shawermacloud.converters;

import com.grig.edu.shawermacloud.models.Ingredient;
import com.grig.edu.shawermacloud.repositories.ingredient.IngredientRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;
    private Map<String, Ingredient> map;

    public IngredientByIdConverter(@Qualifier("jdbcIngredientRepository") IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    private Ingredient convertForImmutableIngredients(String id) {
        map = ingredientRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Ingredient::getId, i -> i));
        return map.get(id);
    }
}
