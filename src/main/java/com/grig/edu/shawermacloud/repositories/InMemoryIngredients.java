package com.grig.edu.shawermacloud.repositories;

import com.grig.edu.shawermacloud.models.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryIngredients implements IngredientRepository {
    private final List<Ingredient> ingredients;
    {
        ingredients = List.of(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );

    }
    private InMemoryIngredients() {
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredients;
    }
}
