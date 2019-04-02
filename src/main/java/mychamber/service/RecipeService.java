package mychamber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mychamber.dao.RecipeRepository;
import mychamber.model.Recipe;

@Service
public class RecipeService {
	
	@Autowired
	RecipeRepository recipeRepository;
	
	public List<Recipe> findAll() {
		return recipeRepository.findAll();
	}

	public Recipe findOne(int id) {
		return recipeRepository.findOne(id);
	}

	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	public void delete(Recipe recipe) {
		recipeRepository.delete(recipe);
	}
}
