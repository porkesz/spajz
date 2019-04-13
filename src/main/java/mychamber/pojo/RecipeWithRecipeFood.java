package mychamber.pojo;

import java.util.List;

import mychamber.model.Recipe;
import mychamber.model.RecipeFood;

public class RecipeWithRecipeFood {

	private Recipe recipe;
	
	private List<RecipeFood> recipeFoods;

	public RecipeWithRecipeFood() {
	}
	
	public RecipeWithRecipeFood(Recipe recipe, List<RecipeFood> recipeFoods) {
		super();
		this.recipe = recipe;
		this.recipeFoods = recipeFoods;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public List<RecipeFood> getRecipeFoods() {
		return recipeFoods;
	}

	public void setRecipeFoods(List<RecipeFood> recipeFoods) {
		this.recipeFoods = recipeFoods;
	}

}
