package mychamber.controller;

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

import mychamber.model.Menu;
import mychamber.model.Recipe;
import mychamber.model.User;
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
	
	@DeleteMapping("/menus")
	public ResponseEntity<Menu> deleteMenu(@Valid @RequestBody int menuId)
	{	
		menuService.delete(menuId);
		return ResponseEntity.ok().build();		
	}
	
}
