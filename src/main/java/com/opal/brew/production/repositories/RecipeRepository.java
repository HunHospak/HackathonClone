package com.opal.brew.production.repositories;

import com.opal.brew.production.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

  Recipe findByRecipeName(String recipeName);
}
