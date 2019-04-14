package mychamber.pojo;

import java.util.List;

public class RecipeWithRecipeFoodSave {
	
	private String name;
	
	private String description;
	
	private List<RecipeFoodSave> foods;

	public RecipeWithRecipeFoodSave() {
	}

	
	public RecipeWithRecipeFoodSave(String name, String description, List<RecipeFoodSave> foods) {
		super();
		this.name = name;
		this.description = description;
		this.foods = foods;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<RecipeFoodSave> getFoods() {
		return foods;
	}


	public void setFoods(List<RecipeFoodSave> foods) {
		this.foods = foods;
	}
	
}
