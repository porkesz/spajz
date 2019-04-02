package mychamber.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mychamber.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>{

}
