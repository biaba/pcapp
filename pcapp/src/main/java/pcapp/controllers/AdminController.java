package pcapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pcapp.entities.User;
import pcapp.services.RequestService;
import pcapp.services.UserService;

// FOR ADMIN ------------------------------

@Controller
public class AdminController {


	@Autowired
	UserService userService;
	
	@Autowired
	RequestService requestService;
	
	// view all users with option to delete
	@GetMapping("/admin/users")
	public String viewUsers(Model model) {
		
		List<User> userList = userService.findAllUsers();
		model.addAttribute("users", userList);
		return "users";
	}

	// view single user
	@GetMapping("/admin/user/{userId}")
	public String viewUser(@PathVariable("userId") Long userId, Model model) {
		
		User user = userService.findUserById(userId);
		System.out.println(user);
		model.addAttribute("user", user);
		return "user";
	}

	// deleting user
	@PostMapping("admin/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		
		String message= userService.deleteUser(id);
		String text = "Check if user: "
				+ " Has role 'manager' or 'admin'. "
				+ " Has not completed requests";
		model.addAttribute("text", text);
		model.addAttribute("message", message);
		model.addAttribute("users", userService.findAllUsers());
		return "users";
	}

	// view all requests
	@GetMapping("/admin/requests")
	public String viewRequests(Model model) {
		
		model.addAttribute("requests", requestService.getRequests());
		return "requests";
	}


}
