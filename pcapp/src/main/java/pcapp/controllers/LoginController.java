package pcapp.controllers;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import pcapp.dtos.CrmUser;
import pcapp.entities.Request;
import pcapp.entities.RequestStatus;
import pcapp.entities.User;
import pcapp.services.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Value("${greeting}")
	//"#{'${computer_parts}
	private String greeting;


// no authorization required
	@GetMapping("/")
	public String showLanding() {
		System.out.println(greeting);
		return "landing";
	}

// after login
	@GetMapping("/home")
	public String showHome(Model model) {
		model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
		
		// check if user has NOT_SUBMITTED requests. 
		// If yes, offer the first from list to edit
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByName(userName);
		List<Request> requestList = user.getRequestList();
		for(Request r: requestList) {
			if(r.getRequestStatus()==RequestStatus.NOT_SUBMITTED) {
				String requestStatus = "NOT_SUBMITTED"; 
				model.addAttribute("requestStatus", requestStatus);
		}
		}

		return "home";
	}

// login page
	@GetMapping("/loginForm")
	public String showLogin() {
		return "login";
	}

// registration pages
	@GetMapping("/register/showRegistrationForm")
	public String showRegister(Model model) {
		model.addAttribute("crmUser", new CrmUser());
		return "registration";
	}
	
	// check if user with username exists
	@RequestMapping("/register/validateUser")
	public @ResponseBody String validateApp(@RequestParam("username") String username, ModelMap model) {
		String mssg= null;
		User checkedUser = userService.findByName(username);
		if(checkedUser!=null) {
			mssg="User with name: "+ checkedUser.getUsername() + " exists. ";
		}
		
		return mssg;
	}

	@PostMapping("/register/processRegistrationForm")
	public String sumbitRegistration(@Valid @ModelAttribute("crmUser") CrmUser crmUser, BindingResult br, Model model) {

		String userName = crmUser.getUsername();
		logger.info("processing registration for user: " + userName);

		// if submitted data not @Valid
		if (br.hasErrors()) {
			return "registration";
		}

		// if user already exists
		if (userService.findByName(userName) != null) {
			model.addAttribute("crmUser", new CrmUser());
			model.addAttribute("errorWarning", "Username has been taken");
			return "registration";
		}
		logger.info("Successfully created user: " + crmUser.getUsername());
		model.addAttribute("userName", crmUser.getUsername());
		userService.saveUser(crmUser);
		return "home";

	}


// Access Denied

	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}

	/*
	 * http.authorizeRequests() .antMatchers("/").permitAll() // public access to
	 * home page .antMatchers("/app/**").hasRole("EMPLOYEE")
	 * .antMatchers("/admin/**").hasRole("ADMIN")
	 * .antMatchers("/leader/**").hasRole("MANAGER") .and() .formLogin()
	 * .loginPage("/login") .loginProcessingUrl("/authUser") .and() .logout()
	 * .logoutSuccessUrl("/") .permitAll() .and() .exceptionHandling()
	 * .accessDeniedPage("/accessDenied");
	 */
}
