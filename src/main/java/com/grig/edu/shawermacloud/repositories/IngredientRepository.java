package com.grig.edu.shawermacloud.repositories;

import com.grig.edu.shawermacloud.models.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository {

    public List<Ingredient> findAll();
}
