package pcapp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pcapp.daos.KidDao;
import pcapp.entities.Kid;

@Service
public class KidService {
	
	@Autowired
	KidDao kidDao;

// create/update
	@Transactional
	public void saveKid(Kid kid) {
		kidDao.saveThisKid(kid);
	}
// read - one/all	
	@Transactional
	public List<Kid> getAll(){
		return kidDao.getAllKids();
	}
	
	@Transactional
	public Kid getOne(int id) {
		return kidDao.getKid(id);
	}

// delete
	@Transactional
	public void deleteKid(int id) {
		kidDao.deleteKid(id);
	}

}

