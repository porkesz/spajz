package mychamber.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mychamber.model.Food;
import mychamber.model.Recipe;
import mychamber.model.RecipeFood;
import mychamber.service.RecipeFoodService;
import mychamber.service.RecipeService;

@RestController
@RequestMapping("/api")
public class RecipeFoodController {

	@Autowired
	RecipeFoodService recipeFoodService;
	
	@Autowired
	RecipeService recipeService;
	
	@GetMapping("/recipeFoodsByRecipeId/{id}")
	public List<RecipeFood> findRecipeFoodsByRecipeId(@PathVariable(value="id") Integer id)
	{
		Recipe recipe = recipeService.findOne(id);
		Optional<List<RecipeFood>> recipeFood = recipeFoodService.allFoodForRecipe(recipe);
		return recipeFood.get();
	}
	
	@GetMapping("/recipeFoods")
	public List<RecipeFood> getAllFoods()
	{
		return recipeFoodService.findAll();
	}
	
	@PostMapping("/recipeFoods")
	public RecipeFood createRecipeFood(@Valid @RequestBody RecipeFood recipeFood) 
	{
		return recipeFoodService.save(recipeFood);
	}
	
	@DeleteMapping("/recipeFoods")
	public ResponseEntity<RecipeFood> deleteRecipeFood(@Valid @RequestBody RecipeFood recipeFood)
	{	
		recipeFoodService.delete(recipeFood);
		return ResponseEntity.ok().build();		
	}
}
