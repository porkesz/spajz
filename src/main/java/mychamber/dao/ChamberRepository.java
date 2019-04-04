package mychamber.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mychamber.model.Chamber;
import mychamber.model.User;

public interface ChamberRepository  extends JpaRepository<Chamber, Integer>{
	List<Chamber> findByUser(User user);
}
