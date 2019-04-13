package mychamber.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mychamber.model.Recipe;
import mychamber.model.RecipeFood;
import mychamber.pojo.RecipeWithRecipeFood;
import mychamber.service.RecipeFoodService;
import mychamber.service.RecipeService;

@RestController
@RequestMapping("/api")
public class RecipeController {

	@Autowired
	RecipeService recipeService;
	
	@Autowired
	RecipeFoodService recipeFoodService;
	
	@PostMapping("/recipes")
	public Recipe createRecipe(@Valid @RequestBody Recipe recipe) 
	{
		return recipeService.save(recipe);
	}
	
	@GetMapping("/recipes")
	public List<Recipe> getAllRecipe()
	{
		return recipeService.findAll();
	}
	
	@GetMapping("/recipes/{id}")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable(value="id") Integer id)
	{	
		Recipe recipe = recipeService.findOne(id);
		
		if (recipe == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(recipe);
	}
	
	@PutMapping("/recipes/{id}")
	public ResponseEntity<Recipe> updateRecipe(@PathVariable(value="id") Integer id,@Valid @RequestBody Recipe recipeDetails)
	{
		Recipe recipe = recipeService.findOne(id); 
		
		if (recipe == null) {
			return ResponseEntity.notFound().build();
		}
		
		recipe.setName(recipeDetails.getName());
		recipe.setDescription(recipeDetails.getDescription());
		Recipe updateRecipe = recipeService.save(recipe);
		
		return ResponseEntity.ok().body(updateRecipe);
	}
	
	@DeleteMapping("/recipes/{id}")
	public ResponseEntity<Recipe> deleteRecipe(@PathVariable(value="id") Integer id)
	{	
		Recipe recipe = recipeService.findOne(id);
		
		if (recipe == null) {
			return ResponseEntity.notFound().build();
		}
		
		recipeService.delete(recipe);
		
		return ResponseEntity.ok().build();		
	}
	
	@GetMapping("/recipeWithRecipeFood/{id}")
	public RecipeWithRecipeFood getRecipeWithRecipeFoodByRecipeId(@PathVariable(value="id") Integer id) 
	{
		Recipe recipe = recipeService.findOne(id);
		List<RecipeFood> recipeFoods = recipeFoodService.allFoodForRecipe(recipe).get();
		RecipeWithRecipeFood recipeWithRecipeFoods = new RecipeWithRecipeFood(recipe, recipeFoods);
		
		return recipeWithRecipeFoods;
	}
	
}
