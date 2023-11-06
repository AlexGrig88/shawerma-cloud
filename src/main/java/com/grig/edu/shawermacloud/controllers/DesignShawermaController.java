package com.grig.edu.shawermacloud.controllers;

import com.grig.edu.shawermacloud.models.Ingredient;
import com.grig.edu.shawermacloud.models.Shawerma;
import com.grig.edu.shawermacloud.models.ShawermaOrder;
import com.grig.edu.shawermacloud.repositories.InMemoryIngredients;
import com.grig.edu.shawermacloud.repositories.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("shawermaOrder")
public class DesignShawermaController {

    private final IngredientRepository ingredientsRepo;

    public DesignShawermaController(@Qualifier("inMemoryIngredients") IngredientRepository repository) {
        this.ingredientsRepo = repository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        for (var type : Ingredient.Type.values()) {
            model.addAttribute(
                    type.toString().toLowerCase(), filteredByType(ingredientsRepo.findAll(), type));
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

    @PostMapping
    public String processShawerma(@Valid Shawerma shawerma,
                                  Errors errors,
                                  @ModelAttribute ShawermaOrder shawermaOrder) {
        if (errors.hasErrors()) {
            return "/design";
        }
        shawermaOrder.addShawerma(shawerma);
        log.info("Processing shawerma: {}", shawerma);
        return "redirect:/orders/current";
    }
}
