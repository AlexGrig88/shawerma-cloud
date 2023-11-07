package com.grig.edu.shawermacloud.repositories.ingredient;

import com.grig.edu.shawermacloud.models.Ingredient;
import com.grig.edu.shawermacloud.repositories.IngredientRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryRepository implements IngredientRepository {
    private final List<Ingredient> ingredients;
    {
        ingredients = List.of(
                new Ingredient("FLTO", "Flour Tortilla", "Пшеничная тортилья", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", "Кукурузная тортилья", Ingredient.Type.WRAP),
                new Ingredient("PITA", "Pita bread", "Лаваш", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", "Мраморная говядина", Ingredient.Type.MEAT),
                new Ingredient("CARN", "Carnitas", "Свинина", Ingredient.Type.MEAT),
                new Ingredient("MUTT", "Mutton", "Баранина", Ingredient.Type.MEAT),
                new Ingredient("TMTO", "Diced Tomatoes", "Помидоры", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", "Салат Латук", Ingredient.Type.VEGGIES),
                new Ingredient("CUCU", "Cucumber", "Свежий огурчик", Ingredient.Type.VEGGIES),
                new Ingredient("CABG", "Cabbage", "Капуста", Ingredient.Type.VEGGIES),
                new Ingredient("ONIO", "Onion", "Лук-порей", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", "Чеддер", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", "Монтерей Джек", Ingredient.Type.CHEESE),
                new Ingredient("GODA", "Gouda", "Гауда", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", "Сальса", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream",  "Сметана", Ingredient.Type.SAUCE),
                new Ingredient("MUST", "Mustard",  "Горчица", Ingredient.Type.SAUCE)
        );

    }
    public InMemoryRepository() {
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredients;
    }
}
