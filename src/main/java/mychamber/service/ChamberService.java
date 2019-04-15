package mychamber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mychamber.dao.ChamberRepository;
import mychamber.model.Chamber;
import mychamber.model.User;

@Service
public class ChamberService {

	@Autowired
	ChamberRepository chamberRepository;
	
	public List<Chamber> allByUser(User user) {
		return chamberRepository.findByUser(user);
	}
	
	public Chamber findOne(int id) {
		return chamberRepository.findOne(id);
	}

	public Chamber save(Chamber chamber) {
		return chamberRepository.save(chamber);
	}

	public void delete(Chamber chamber) {
		chamberRepository.delete(chamber);
	}
}
