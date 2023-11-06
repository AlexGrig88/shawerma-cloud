package com.grig.edu.shawermacloud.converters;

import com.grig.edu.shawermacloud.models.Ingredient;
import com.grig.edu.shawermacloud.repositories.InMemoryIngredients;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> map;

    public IngredientByIdConverter() {
        this.map = InMemoryIngredients.get()
                .stream()
                .collect(Collectors.toMap(Ingredient::getId, i -> i));
    }

    @Override
    public Ingredient convert(String id) {
        return map.get(id);
    }
}
