package mychamber.dao;

import mychamber.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer> {

}