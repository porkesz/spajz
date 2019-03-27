package mychamber.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mychamber.model.Food;
import mychamber.service.FoodService;

@RestController
@RequestMapping("/api")
public class FoodController {

	@Autowired
	FoodService foodService;
	
	@PostMapping("/foods")
	public Food createFood(@Valid @RequestBody Food food) 
	{
		return foodService.save(food);
	}
	
	@GetMapping("/foods")
	public List<Food> getAllFoods()
	{
		return foodService.findAll();
	}
	
	@GetMapping("/foods/{id}")
	public ResponseEntity<Food> getFoodById(@PathVariable(value="id") Integer id)
	{	
		Food food = foodService.findOne(id);
		
		if (food == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(food);
	}
	
	@PutMapping("/foods/{id}")
	public ResponseEntity<Food> updateFood(@PathVariable(value="id") Integer id,@Valid @RequestBody Food foodDetails)
	{
		Food food = foodService.findOne(id); 
		
		if (food == null) {
			return ResponseEntity.notFound().build();
		}
		
		food.setFoodCategory(foodDetails.getFoodCategory());
		food.setName(foodDetails.getName());
		food.setUnit(foodDetails.getUnit());
		food.setCalorie(foodDetails.getCalorie());
		Food updateFood = foodService.save(food);
		
		return ResponseEntity.ok().body(updateFood);
	}
	
	@DeleteMapping("/foods/{id}")
	public ResponseEntity<Food> deleteFood(@PathVariable(value="id") Integer id)
	{	
		Food food = foodService.findOne(id);
		
		if (food == null) {
			return ResponseEntity.notFound().build();
		}
		
		foodService.delete(food);
		
		return ResponseEntity.ok().build();		
	}
	
}
