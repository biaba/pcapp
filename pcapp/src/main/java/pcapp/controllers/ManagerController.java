package pcapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pcapp.services.RequestService;
import pcapp.services.UserService;


// FOR MANAGER ------------------------------

@Controller
public class ManagerController {
	
	@Autowired
	RequestService requestService;
	
	@Autowired
	UserService userService;


	// view all requests. Option to delete and to change status
		@GetMapping("/leader")
		public String showRequests(Model model) {
			model.addAttribute("requests", requestService.getRequests());
			return "requests";
		}
	
	// change request status to completed
	// Status change allowed from SUBMITTED to COMPLETED only (when required parts are provided to user). 
	// In case the user has not submitted the request, the status can not be changed to completed
		@PostMapping("/leader/changeStatus/{id}")
		public String changeRequestStatus(@PathVariable("id") Long id, Model model) {
			
			String message = requestService.changeRequestStatus(requestService.getRequest(id));
			if(message.equals("Not allowed"));{
				model.addAttribute("text", "As a manager you are allowed to change from SUBMITTED to COMPLETED only (when required parts are provided)");
			}
			model.addAttribute("message", message);			
			model.addAttribute("requests", requestService.getRequests());
			return "requests";
		}

	// view single request	
		@GetMapping("/leader/showRequest/{id}")
		public String showRequest(@PathVariable("id") Long id, Model model) {
			
			model.addAttribute("request", requestService.getRequest(id));
			String userName  = userService.findUserById(requestService.getRequest(id).getUserId()).getUsername();
			model.addAttribute("userName", userName);
			return "request";
		}


}
