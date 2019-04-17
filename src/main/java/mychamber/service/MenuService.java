package mychamber.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mychamber.dao.MenuRepository;
import mychamber.model.Menu;
import mychamber.model.User;

@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepository;
	
	public Optional<List<Menu>> allMenuByUser(User user) {
		return menuRepository.findByUser(user);
	}
	
	public Menu findOne(int id) {
		return menuRepository.findOne(id);
	}
	
	public Menu save(Menu menu) {
		return menuRepository.save(menu);
	}

	public void delete(int menuId) {
		menuRepository.delete(menuId);;
	}
}
