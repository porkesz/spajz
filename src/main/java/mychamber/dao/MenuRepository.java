package mychamber.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mychamber.model.Menu;
import mychamber.model.Recipe;
import mychamber.model.User;

public interface MenuRepository extends JpaRepository<Menu, Integer>{
	Optional<List<Menu>> findByUser(User user);
	Optional<List<Menu>> findByRecipe(Recipe recipe);
}
