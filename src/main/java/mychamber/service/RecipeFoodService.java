package mychamber.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mychamber.dao.RecipeFoodRepository;
import mychamber.model.Recipe;
import mychamber.model.RecipeFood;

@Service
public class RecipeFoodService {
	
	@Autowired
	RecipeFoodRepository recipeFoodRepository;
	
	public Optional<List<RecipeFood>> allFoodForRecipe(Recipe recipe) {
		return recipeFoodRepository.findByRecipe(recipe);
	}
	
	public List<RecipeFood> findAll() {
		return recipeFoodRepository.findAll();
	}

	public RecipeFood save(RecipeFood recipeFood) {
		return recipeFoodRepository.save(recipeFood);
	}

	public void delete(RecipeFood recipeFood) {
		recipeFoodRepository.delete(recipeFood);
	}
}
