package com.grig.edu.shawermacloud.controllers;

import com.grig.edu.shawermacloud.models.Ingredient;
import com.grig.edu.shawermacloud.models.Shawerma;
import com.grig.edu.shawermacloud.models.ShawermaOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("shawermaOrder")
public class DesignShawermaController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        var ingredients = List.of(
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

        for (var type : Ingredient.Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), filteredByType(ingredients, type));
        }
    }

    private List<Ingredient> filteredByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream()
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "shawerma")
    public Shawerma shawerma() {
        return new Shawerma();
    }

    @ModelAttribute(name = "shawermaOrder")
    public ShawermaOrder shawermaOrder() {
        return new ShawermaOrder();
    }

    @GetMapping
    public String showDesignShawerma() {
        return "design";
    }
}
