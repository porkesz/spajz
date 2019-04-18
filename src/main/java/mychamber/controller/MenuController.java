package mychamber.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mychamber.model.Chamber;
import mychamber.model.Menu;
import mychamber.model.Recipe;
import mychamber.model.RecipeFood;
import mychamber.model.User;
import mychamber.pojo.ShoppingListItem;
import mychamber.service.ChamberService;
import mychamber.service.MenuService;
import mychamber.service.RecipeFoodService;
import mychamber.service.RecipeService;

@RestController
@RequestMapping("/api")
public class MenuController {
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	RecipeFoodService recipeFoodService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	UserDetailsService userService;
	
	@Autowired
	ChamberService chamberService;
	
	@GetMapping("/menusByUser")
	public ResponseEntity<List<Menu>> findMenuByUser()
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) userService.loadUserByUsername(username);
		List<Menu> menus = menuService.allMenuByUser(user).get();
		
		return ResponseEntity.ok().body(menus);
	}
	
	@GetMapping("/menusByUser/{id}")
	public ResponseEntity<Boolean> isRecipeInUserMenu(@PathVariable(value="id") Integer id)
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) userService.loadUserByUsername(username);
		Optional<List<Menu>> menus = menuService.allMenuByUser(user);
		
		if (menus.isPresent()) {
			for (Menu menu : menus.get()) {
				if (menu.getRecipe().getId() == id) {
					return ResponseEntity.ok().body(true);
				}
			}
		}
		
		return ResponseEntity.ok().body(false);
	}

	@PostMapping("/menus")
	public ResponseEntity<Menu> createMenu(@Valid @RequestBody Recipe recipe) 
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) userService.loadUserByUsername(username);
		
		Menu menu = new Menu();
		menu.setUser(user);
		menu.setRecipe(recipe);
		menuService.save(menu);
		
		return ResponseEntity.ok().body(menu);
	}
	
	@DeleteMapping("/menus/{id}")
	public ResponseEntity<Menu> deleteMenu(@PathVariable(value="id") Integer id)
	{	
		menuService.delete(id);
		return ResponseEntity.ok().build();		
	}
	
	@GetMapping("/shoppingList")
	public ResponseEntity<List<ShoppingListItem>> getShoppingList()
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) userService.loadUserByUsername(username);
		Optional<List<Menu>> menus = menuService.allMenuByUser(user);
		List<ShoppingListItem> shoppingList = new ArrayList<>();
		List<Chamber> chamberList = chamberService.allByUser(user);
		
		if (menus.isPresent()) {
			for (Menu menu : menus.get()) {
				Optional<List<RecipeFood>> recipeFoods = recipeFoodService.allFoodForRecipe(menu.getRecipe());
				if (recipeFoods.isPresent()) {
					for (RecipeFood recipeFood : recipeFoods.get()) {
						ShoppingListItem shoppingListItem = new ShoppingListItem();
						shoppingListItem.setFoodName(recipeFood.getFood().getName());
						shoppingListItem.setFoodUnit(recipeFood.getFood().getUnit());
						shoppingListItem.setFoodQuantity(recipeFood.getQuantity());
						if (this.isExistsShoppingListItem(shoppingListItem, shoppingList)) {
							for (ShoppingListItem listItem : shoppingList) {
								if (listItem.getFoodName() == shoppingListItem.getFoodName()) {
									listItem.setFoodQuantity(listItem.getFoodQuantity() + shoppingListItem.getFoodQuantity());
								}
							}
						} else {
							shoppingList.add(shoppingListItem);
						}
					}
				}
			}
		}
		
		for (ShoppingListItem shoppingListItem : shoppingList) {
			for (Chamber chamber : chamberList) {
				if (shoppingListItem.getFoodName() == chamber.getFood().getName()) {
					shoppingListItem.setFoodQuantity(shoppingListItem.getFoodQuantity() - chamber.getQuantity());
				}
			}
		}
		
		shoppingList.removeIf(c -> c.getFoodQuantity() <= 0 );
		
		return ResponseEntity.ok().body(shoppingList);
	}
	
	private Boolean isExistsShoppingListItem(ShoppingListItem shoppingListItem, List<ShoppingListItem> shoppingList)
	{
		for (ShoppingListItem listItem : shoppingList) {
			if (listItem.getFoodName() == shoppingListItem.getFoodName()) {
				return true;
			}
		}
		
		return false;
	}
}
