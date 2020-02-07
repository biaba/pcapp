package pcapp.daos;

import pcapp.entities.ComputerPart;

public interface ComputerPartDao {
	
	Long saveComputerPart(ComputerPart computerPart);
	
	ComputerPart findByName(String cpName);
	
	ComputerPart findById(Long id);

}
