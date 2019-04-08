package mychamber.controller;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mychamber.model.Role;
import mychamber.model.User;
import mychamber.pojo.UserRegistration;
import mychamber.service.CustomUserDetailsService;
import mychamber.service.RoleService;

@RestController
public class UserController {
	
	@Autowired
	CustomUserDetailsService userService;
	
	@Autowired
	RoleService roleService;
	
	@PostMapping(value = "/register")
    public User register(@RequestBody UserRegistration userRegistration){
        User user = new User();
        user.setFirstname(userRegistration.getFirstname());
        user.setLastname(userRegistration.getLastname());
        user.setEmail(userRegistration.getEmail());
        user.setUsername(userRegistration.getUsername());
        user.setPassword(userRegistration.getPassword());
        
        Role role = roleService.findByRole("USER"); 
        user.setRoles(Stream.of(role)
                .collect(Collectors.toCollection(HashSet::new)));
        
        return userService.save(user);
    }
	
	@GetMapping(value ="/getUsername")
    public String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
