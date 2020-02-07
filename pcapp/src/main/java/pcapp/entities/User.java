package pcapp.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String email;
	
// one user can have many roles
// fetchType = default -lazy. we get applicant roles by calling getters()
// cascade = all operations except DELETE are performed on associated table
	@ManyToMany(fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH,
			CascadeType.DETACH})
	@JoinTable(name="users_roles",
				joinColumns=@JoinColumn(name="user_id"),
				inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();

// one user can have many requests
// uni-directional mapping. FetchType - eager. Always when getting user all his requests are loaded	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Request> requestList;
	
	public User() {
		
	}
	
	
	public User(String username,String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(String username, String password,String email, Set<Role> roles) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	// methods to add/delete role
	// returns true if success
	public boolean addRole(Role role) {
		boolean added = roles.add(role);
		boolean added2= role.getUsers().add(this);
		
		return added && added2;
	}
	
	public boolean deleteRole(Role role) {
		boolean deleted = roles.remove(role);
		boolean deleted2 = role.getUsers().remove(this);
		
		return deleted && deleted2;
	}
	// convenience method for adding/deleting requests
	
	public boolean addRequest(Request request) {
		boolean added = this.requestList.add(request);
		
		return added;
	}
	
	public boolean deleteRequest(Request request) {
		boolean removed = this.requestList.remove(request);
		
		return removed;
	}
	
	// overriding toString
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", roles="
				+ roles + ", requestList=" + requestList + "]";
	}

	// setters and getters
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<Request> getRequestList() {
		return requestList;
	}


	public void setRequestList(List<Request> requestList) {
		this.requestList = requestList;
	}


		

}

