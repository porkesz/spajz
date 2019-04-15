package mychamber.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mychamber.model.Food;
import mychamber.model.Recipe;
import mychamber.model.RecipeFood;
import mychamber.model.User;
import mychamber.pojo.RecipeFoodSave;
import mychamber.pojo.RecipeWithRecipeFood;
import mychamber.pojo.RecipeWithRecipeFoodSave;
import mychamber.service.CustomUserDetailsService;
import mychamber.service.FoodService;
import mychamber.service.RecipeFoodService;
import mychamber.service.RecipeService;

@RestController
@RequestMapping("/api")
public class RecipeController {

	@Autowired
	RecipeService recipeService;
	
	@Autowired
	RecipeFoodService recipeFoodService;
	
	@Autowired
	CustomUserDetailsService userService;
	
	@Autowired
	FoodService foodService;
	
	@PostMapping("/recipes")
	public Recipe createRecipe(@Valid @RequestBody Recipe recipe) 
	{
		return recipeService.save(recipe);
	}
	
	@PostMapping("/recipeWithRecipeFoodSave")
	public Recipe createRecipeWithRecipeFoodSave(@Valid @RequestBody RecipeWithRecipeFoodSave recipeSave) 
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) userService.loadUserByUsername(username);
		Recipe recipe = new Recipe();
		
		recipe.setName(recipeSave.getName());
		recipe.setDescription(recipeSave.getDescription());
		recipe.setUser(user);
		
		Recipe createdRecipe = recipeService.save(recipe);
		
		for ( RecipeFoodSave recipeFoodSave : recipeSave.getFoods()) 
		{ 
			Food food = foodService.findOne(recipeFoodSave.getFoodId());
		    RecipeFood recipeFood = new RecipeFood();
		    
		    recipeFood.setRecipe(createdRecipe);
		    recipeFood.setQuantity(recipeFoodSave.getQuantity());
		    recipeFood.setFood(food);
		    
		    recipeFoodService.save(recipeFood);
		}
	
		return createdRecipe;
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
	public ResponseEntity<Recipe> updateRecipe(@PathVariable(value="id") Integer id,@Valid @RequestBody RecipeWithRecipeFoodSave recipeSave)
	{
		Recipe recipe = recipeService.findOne(id); 
		
		if (recipe == null) {
			return ResponseEntity.notFound().build();
		}
		
		recipe.setName(recipeSave.getName());
		recipe.setDescription(recipeSave.getDescription());
		
		Recipe updateRecipe = recipeService.save(recipe);
		
		List<RecipeFood> recipeFoods = recipeFoodService.allFoodForRecipe(recipe).get();
		
		for (RecipeFood recipeFood : recipeFoods) 
		{
			recipeFoodService.delete(recipeFood);
		}
		
		for ( RecipeFoodSave recipeFoodSave : recipeSave.getFoods()) 
		{ 
			Food food = foodService.findOne(recipeFoodSave.getFoodId());
		    RecipeFood recipeFood = new RecipeFood();
		    
		    recipeFood.setRecipe(updateRecipe);
		    recipeFood.setQuantity(recipeFoodSave.getQuantity());
		    recipeFood.setFood(food);
		    
		    recipeFoodService.save(recipeFood);
		}
		
		return ResponseEntity.ok().body(updateRecipe);
	}
	
	@DeleteMapping("/recipes/{id}")
	public ResponseEntity<Recipe> deleteRecipe(@PathVariable(value="id") Integer id)
	{	
		Recipe recipe = recipeService.findOne(id);
		List<RecipeFood> recipeFoods = recipeFoodService.allFoodForRecipe(recipe).get();
		
		if (recipe == null) {
			return ResponseEntity.notFound().build();
		}
		
		for (RecipeFood recipeFood : recipeFoods) {
			recipeFoodService.delete(recipeFood);
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
