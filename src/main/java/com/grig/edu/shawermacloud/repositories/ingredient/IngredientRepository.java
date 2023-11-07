package com.grig.edu.shawermacloud.repositories.ingredient;

import com.grig.edu.shawermacloud.models.Ingredient;
import com.grig.edu.shawermacloud.repositories.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends BaseRepository<Ingredient, String> {
}
