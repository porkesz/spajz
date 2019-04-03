package mychamber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mychamber.service.CustomUserDetailsService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	CustomUserDetailsService userService;

}
