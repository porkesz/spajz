package mychamber.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mychamber.model.RecipeFood;
import mychamber.service.RecipeFoodService;

@RestController
@RequestMapping("/api")
public class RecipeFoodController {

	@Autowired
	RecipeFoodService recipeFoodService;
	
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
