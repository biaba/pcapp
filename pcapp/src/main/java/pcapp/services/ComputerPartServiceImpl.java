package pcapp.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pcapp.daos.ComputerPartDao;
import pcapp.daos.RequestDao;
import pcapp.entities.ComputerPart;

@Service
public class ComputerPartServiceImpl implements ComputerPartService {

	@Autowired
	ComputerPartDao computerPartDao;
	
	@Autowired
	RequestDao requestDao;
	
	@Override
	@Transactional
	public Long saveComputerPart(ComputerPart computerPart) {
		
		return computerPartDao.saveComputerPart(computerPart);
	}

	@Override
	@Transactional
	public ComputerPart findComputerPartByName(String cpName) {
		return computerPartDao.findByName(cpName);
	}

	@Override
	@Transactional
	public ComputerPart findById(Long id) {
		ComputerPart cp = computerPartDao.findById(id);
		
		return cp;
	}

}
