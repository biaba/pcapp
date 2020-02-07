package pcapp.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pcapp.entities.ComputerPart;
import pcapp.entities.Request;
import pcapp.entities.RequestStatus;
import pcapp.entities.User;
import pcapp.models.Kid;
import pcapp.rest_client.RestClient;
import pcapp.services.ComputerPartService;
import pcapp.services.RequestService;
import pcapp.services.UserService;

//FOR ALL AUTHENTICATED USERS ------------------------------
@Controller
public class RequestController {

	@Autowired
	private UserService userService;

	@Autowired
	private RequestService requestService;

	@Autowired
	private ComputerPartService computerPartService;

	@Value("#{'${computer_parts}'.split(',')}")
	private List<String> computerParts;


// making request
	@GetMapping("/app")
	public String makeRequest(Model model) {

		Request request = new Request();

		// computer parts and text to be completed in form
		model.addAttribute("computerParts", computerParts);
		model.addAttribute("request", request);
		System.out.println(request);
		return "app";
	}

// submitting request
	@PostMapping(value={"/app/submitRequest"})
	public String submitRequest(@Valid @ModelAttribute("request") Request request, BindingResult br, Model model) {
		
		// if errors, return request page
		if (br.hasErrors()) {
			model.addAttribute("computerParts", computerParts);
			return "app";
		}
		if (request.getCpList().isEmpty()) {
			model.addAttribute("cpError", "You have to choose at least one computer part");
			model.addAttribute("computerParts", computerParts);
			return "app";
		}
		
		// setting todays date
		Date today = Calendar.getInstance().getTime();
		request.setDateSubmitted(today);

		// setting user_id for request
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByName(userName);
		request.setUserId(user.getId());

		// setting computer part list
		//Converting from received List (used in request form) to HashSet (field used in entity)

		List<String> cpList = request.getCpList();
		HashSet<ComputerPart> cpSet = new HashSet<>();
		for (String l : cpList) {
			cpSet.add(computerPartService.findComputerPartByName(l));
		}
		
		request.setComputerParts(cpSet);

		// save request
		requestService.saveRequest(request);

		// check if user has NOT_SUBMITTED requests.
		// If yes, offer the first from list to edit
		User user2 = userService.findByName(userName);
		List<Request> requestList = user2.getRequestList();
		for (Request r : requestList) {
			if (r.getRequestStatus() == RequestStatus.NOT_SUBMITTED) {
				String requestStatus = "NOT_SUBMITTED";
				model.addAttribute("requestStatus", requestStatus);
			}
		}
		model.addAttribute("userName", userName);
		return "home";
	}

// editing request (was saved for later submission) 
	@GetMapping("/app/edit")
	public String editRequest(Model model) {

		// getting the user id
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByName(userName);

		List<Request> requestList = user.getRequestList();
		// added the first request found with status NOT_SUBMITTED
		Request request = null;
		for (Request r : requestList) {
			if (r.getRequestStatus() == RequestStatus.NOT_SUBMITTED) {
				request = r;
			}

		}
		
		// adding computer parts list
		model.addAttribute("computerParts", computerParts);
		model.addAttribute("request", request);

		return "app";
	}

}
