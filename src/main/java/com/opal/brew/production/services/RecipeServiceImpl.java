package com.opal.brew.production.services;

import com.opal.brew.production.models.Recipe;
import com.opal.brew.production.repositories.RecipeRepository;
import java.util.List;

import com.opal.brew.production.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

  private RecipeRepository recipeRepository;

  @Autowired
  public RecipeServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public void save(Recipe recipe) {
    recipeRepository.save(recipe);
  }

  @Override
  public List<Recipe> findAll() {
    return recipeRepository.findAll();
  }

  @Override
  public boolean isValid(Recipe recipe) {
    return recipe.getRecipeName() != null && recipeRepository.findByRecipeName(recipe.getRecipeName()) == null;
  }

  @Override
  public Recipe findByRecipeName(String name){
    return recipeRepository.findByRecipeName(name);
  }
}
