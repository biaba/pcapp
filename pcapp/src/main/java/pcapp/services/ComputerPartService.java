package pcapp.services;

import pcapp.entities.ComputerPart;

public interface ComputerPartService {
	
	Long saveComputerPart(ComputerPart computerPart);
	
	ComputerPart findComputerPartByName(String cpName);
	
	ComputerPart findById(Long id);

}
