package pcapp.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import pcapp.dtos.CrmUser;
import pcapp.entities.User;

public interface UserService extends UserDetailsService{
	
	User findByName(String userName);
	
	Long saveUser(CrmUser crmUser);
	
	User findUserById(Long id);
	
	String deleteUser(Long id);
	
	List<User> findAllUsers();

}
