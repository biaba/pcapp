package pcapp.daos;

import java.util.List;

import pcapp.entities.User;

public interface UserDao {
	
	Long saveUser(User user);
	
	User findByUserName(String userName);
	
	User findById(Long id);	
	
	void deleteUser(User user);
	
	List<User> loadUsers();

}
