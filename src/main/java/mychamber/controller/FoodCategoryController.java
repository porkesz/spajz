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

import mychamber.model.FoodCategory;
import mychamber.service.FoodCategoryService;

@RestController
@RequestMapping("/api/private")
public class FoodCategoryController {

	@Autowired
	FoodCategoryService foodCategoryService;
	
	@PostMapping("/foodCategories")
	public FoodCategory createFoodCategory(@Valid @RequestBody FoodCategory foodCategory) 
	{
		return foodCategoryService.save(foodCategory);
	}
	
	@GetMapping("/foodCategories")
	public List<FoodCategory> getAllFoodCategory()
	{
		return foodCategoryService.findAll();
	}
	
	@GetMapping("/foodCategories/{id}")
	public ResponseEntity<FoodCategory> getFoodCategoryById(@PathVariable(value="id") Integer id)
	{	
		FoodCategory foodCategory = foodCategoryService.findOne(id);
		
		if (foodCategory == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(foodCategory);
	}
	
	@PutMapping("/foodCategories/{id}")
	public ResponseEntity<FoodCategory> updateFoodCategory(@PathVariable(value="id") Integer id,@Valid @RequestBody FoodCategory foodCategoryDetails)
	{
		FoodCategory foodCategory = foodCategoryService.findOne(id); 
		
		if (foodCategory == null) {
			return ResponseEntity.notFound().build();
		}
		
		foodCategory.setName(foodCategoryDetails.getName());
		FoodCategory updateFoodCategory = foodCategoryService.save(foodCategory);
		
		return ResponseEntity.ok().body(updateFoodCategory);
	}
	
	@DeleteMapping("/foodCategories/{id}")
	public ResponseEntity<FoodCategory> deleteFoodCategory(@PathVariable(value="id") Integer id)
	{	
		FoodCategory foodCategory = foodCategoryService.findOne(id);
		
		if (foodCategory == null) {
			return ResponseEntity.notFound().build();
		}
		
		foodCategoryService.delete(foodCategory);
		
		return ResponseEntity.ok().build();		
	}
	
}
