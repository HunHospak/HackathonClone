package com.opal.brew.controllers;

import com.opal.brew.production.models.Recipe;
import com.opal.brew.production.services.RecipeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {

  private RecipeService recipeService;

  @Autowired
  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @PostMapping("/recipe/save")
  public ResponseEntity saveNewRecipe(@RequestBody Recipe recipe) {
    if (recipeService.isValid(recipe)) {
      recipeService.save(recipe);
      return new ResponseEntity(HttpStatus.CREATED);
    } else {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/recipe/list")
  public ResponseEntity listAllRecipe() {
    List<Recipe> recipes = recipeService.findAll();
    if (recipes.isEmpty()) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    } else {
      return ResponseEntity.ok(recipes);
    }
  }
}
