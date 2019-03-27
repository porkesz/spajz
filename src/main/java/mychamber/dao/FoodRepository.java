package mychamber.dao;

import mychamber.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FoodRepository extends JpaRepository<Food, Integer> {

}