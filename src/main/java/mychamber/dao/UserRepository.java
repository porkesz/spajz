package mychamber.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import mychamber.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
