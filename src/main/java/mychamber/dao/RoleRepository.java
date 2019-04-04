package mychamber.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mychamber.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRole(String role);
}
