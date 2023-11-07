package com.grig.edu.shawermacloud.repositories;

import com.grig.edu.shawermacloud.models.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends BaseRepository<Ingredient, Long> {
}
