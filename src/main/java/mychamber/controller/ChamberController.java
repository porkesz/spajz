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

import mychamber.model.Chamber;
import mychamber.model.Food;
import mychamber.model.User;
import mychamber.pojo.RecipeFoodSave;
import mychamber.service.ChamberService;
import mychamber.service.CustomUserDetailsService;
import mychamber.service.FoodService;

@RestController
@RequestMapping("/api")
public class ChamberController {

	@Autowired
	ChamberService chamberService;
	
	@Autowired
	FoodService foodService;
	
	@Autowired
	CustomUserDetailsService userService;
	
	@GetMapping("/chambers/{id}")
	public Chamber getChamber(@PathVariable(value="id") Integer id)
	{	
		Chamber chamber = chamberService.findOne(id);
		
		return chamber;		
	}
	
	@GetMapping("/chambersByUser")
	public List<Chamber> findChambersByUserId()
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) userService.loadUserByUsername(username);
		
		return chamberService.allByUser(user);
	}
	
	@GetMapping("/chamber/availableFoods")
	public List<Food> getAvailableFoods()
	{
		List<Chamber> userChambers = this.findChambersByUserId();
		List<Food> foods = foodService.findAll();
		
		for (Chamber chamber : userChambers) {
			foods.removeIf(c -> c.getName().equals(chamber.getFood().getName()));	
		}
		
		return foods;
	}
	
	@PostMapping("/chambers/addFoodsToChamber")
	public ResponseEntity<Chamber> createChamber(@Valid @RequestBody List<RecipeFoodSave> foodQuantity) 
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) userService.loadUserByUsername(username);
		
		for ( RecipeFoodSave recipeFoodSave : foodQuantity) 
		{ 
			Food food = foodService.findOne(recipeFoodSave.getFoodId());
		    Chamber chamber = new Chamber();
		    
		    chamber.setFood(food);
		    chamber.setUser(user);
		    chamber.setQuantity(recipeFoodSave.getQuantity());
		    
		    chamberService.save(chamber);
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/chambers/{id}")
	public Chamber updateChamber(@PathVariable(value="id") Integer id, @Valid @RequestBody Chamber chamber) 
	{
		chamberService.save(chamber);
		
		return chamber;
	}
	
	@DeleteMapping("/chambers/{id}")
	public ResponseEntity<Chamber> deleteChamber(@PathVariable(value="id") Integer id)
	{	
		Chamber chamber = chamberService.findOne(id);
		chamberService.delete(chamber);
		
		return ResponseEntity.ok().build();		
	}
}
