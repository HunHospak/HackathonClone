package com.opal.brew.production.services;

import com.opal.brew.production.models.Recipe;
import java.util.List;

public interface RecipeService {

  void save(Recipe recipe);

  List<Recipe> findAll();

  boolean isValid(Recipe recipe);

  Recipe findByRecipeName(String name);
}
