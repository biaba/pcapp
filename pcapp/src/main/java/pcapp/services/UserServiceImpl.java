package pcapp.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pcapp.daos.RoleDao;
import pcapp.daos.UserDao;
import pcapp.dtos.CrmUser;
import pcapp.entities.Request;
import pcapp.entities.RequestStatus;
import pcapp.entities.Role;
import pcapp.entities.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	@Transactional
	public User findByName(String userName) {
		
		return  userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public Long saveUser(CrmUser crmUser) {
		User user = new User();
		Role role = new Role();
		Long savedUserId;
		
		user.setUsername(crmUser.getUsername());		
		user.setPassword(bcryptEncoder.encode(crmUser.getPassword()));
		// by default role = USER
		role = roleDao.findRoleByName("ROLE_APPLICANT");
		user.addRole(role);
		user.setEmail(crmUser.getEmail());
		
		savedUserId = new Long( userDao.saveUser(user));
		
		return savedUserId;
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public User findUserById(Long id) {
		
		return userDao.findById(id);
	}

	@Override
	@Transactional
	public String deleteUser(Long id) {
		
		// message to be sent to Controller if success
		// in case Exception is thrown/ or User can not be found/ or User has ROLE_MANAGER or ROLE_ADMIN/ or request not completed
		// message got changed to "User can not be deleted"
		String message = "success";

		try {
			User user = userDao.findById(id);
			
			// checking user roles
			for(Role r: user.getRoles()) {
				if(r.getRoleName().equals("ROLE_MANAGER")||r.getRoleName().equals("ROLE_ADMIN")) {
					throw new Exception ("not allowed");
				}
			}
			// checking request statuses
			for(Request r: user.getRequestList()) {
				if(r.getRequestStatus()==RequestStatus.NOT_SUBMITTED || r.getRequestStatus()==RequestStatus.SUBMITTED) {
					throw new Exception ("not allowed");
				}
			}
			userDao.deleteUser(user);
		}
		catch(Exception e) {
			message="User can not be deleted";
			System.out.println("User can not be deleted");
		}
		
		return message;
	}

	@Override
	@Transactional
	public List<User> findAllUsers() {
		
		return userDao.loadUsers();
	}

}
