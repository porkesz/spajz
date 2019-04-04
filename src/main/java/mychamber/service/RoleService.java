package mychamber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mychamber.dao.RoleRepository;
import mychamber.model.Role;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public Role findByRole(String role) {
		return roleRepository.findByRole(role);
	}
}
