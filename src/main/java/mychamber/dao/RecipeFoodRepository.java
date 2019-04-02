package mychamber.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mychamber.model.Recipe;
import mychamber.model.RecipeFood;

public interface RecipeFoodRepository extends JpaRepository<RecipeFood, Integer>{
	Optional<List<RecipeFood>> findByRecipe(Recipe recipe);
}
