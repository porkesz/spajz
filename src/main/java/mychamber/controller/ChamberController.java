package mychamber.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mychamber.model.Chamber;
import mychamber.model.User;
import mychamber.service.ChamberService;
import mychamber.service.CustomUserDetailsService;

@RestController
@RequestMapping("/api")
public class ChamberController {

	@Autowired
	ChamberService chamberService;
	
	@Autowired
	CustomUserDetailsService userService;
	
	@GetMapping("/chambersByUserId/{id}")
	public List<Chamber> findChamberByUserId(@PathVariable(value="id") Integer id)
	{
		User user = userService.findOne(id);
		return chamberService.allByUser(user);
	}
	
	@PostMapping("/chambers")
	public Chamber createChamber(@Valid @RequestBody Chamber chamber) 
	{
		return chamberService.save(chamber);
	}
	
	@DeleteMapping("/chambers")
	public ResponseEntity<Chamber> deleteChamber(@Valid @RequestBody Chamber chamber)
	{	
		chamberService.delete(chamber);
		return ResponseEntity.ok().build();		
	}
}
