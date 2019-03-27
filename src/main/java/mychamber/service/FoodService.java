package mychamber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mychamber.dao.FoodRepository;
import mychamber.model.Food;

@Service
public class FoodService {
	
	@Autowired
	FoodRepository foodRepository;

	public List<Food> findAll() {
		return foodRepository.findAll();
	}

	public Food findOne(int id) {
		return foodRepository.findOne(id);
	}

	public Food save(Food food) {
		return foodRepository.save(food);
	}

	public void delete(Food food) {
		foodRepository.delete(food);
	}

}
