package mychamber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mychamber.dao.FoodCategoryRepository;
import mychamber.model.FoodCategory;

@Service
public class FoodCategoryService {
	
	@Autowired
	FoodCategoryRepository foodCategoryRepository;

	public List<FoodCategory> findAll() {
		return foodCategoryRepository.findAll();
	}

	public FoodCategory findOne(int id) {
		return foodCategoryRepository.findOne(id);
	}

	public FoodCategory save(FoodCategory foodCategory) {
		return foodCategoryRepository.save(foodCategory);
	}

	public void delete(FoodCategory foodCategory) {
		foodCategoryRepository.delete(foodCategory);
	}
}
