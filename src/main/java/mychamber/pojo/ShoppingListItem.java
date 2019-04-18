package mychamber.pojo;

public class ShoppingListItem {
	
	String foodName;
	
	int foodQuantity;
	
	String foodUnit;

	public ShoppingListItem() {
	}
	
	public ShoppingListItem(String foodName, int foodQuantity, String foodUnit) {
		super();
		this.foodName = foodName;
		this.foodQuantity = foodQuantity;
		this.foodUnit = foodUnit;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodQuantity() {
		return foodQuantity;
	}

	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}

	public String getFoodUnit() {
		return foodUnit;
	}

	public void setFoodUnit(String foodUnit) {
		this.foodUnit = foodUnit;
	}
	
}
