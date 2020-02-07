package pcapp.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pcapp.entities.ComputerPart;
import pcapp.entities.Request;
import pcapp.entities.RequestStatus;
import pcapp.services.ComputerPartService;
import pcapp.services.RequestService;

@RestController
@RequestMapping("/rest")
public class RequestControllerRest {

	@Autowired
	RequestService requestService;
	
	@Autowired
	ComputerPartService computerPartService;

	@GetMapping("/requests")
	public List<Request> getAllRequests() {

		return requestService.getRequests();
	}

	// this method is used for external orders. Shop may order pc parts.
	// Non registered customers are ordering pc parts under id - 25
	// By sending request PC parts are required (cpList) and text - optional (text).
	@PostMapping("/requests")
	public void createNewRequest(@RequestBody Request request) {

		// if set to null || 0 new Request will be created (hibernate saveOrUpdate()).
		// In case an existing Id will be provided may replace an existing request
		request.setId((long) 0);
		Date today = Calendar.getInstance().getTime();
		request.setDateSubmitted(today);
		// external customers are ordering pc parts via shop. Id - 25
		request.setUserId((long) 25);
		// orders coming from shop don't have option to be saved for later submission
		request.setRequestStatus(RequestStatus.SUBMITTED);
		if (request.getText().isEmpty()) {
			request.setText("no particular requirements");
		}

		// Converting from received List (used in json as array of strings) to HashSet
		// (field used
		// in entity)

		List<String> cpList = request.getCpList();
		System.out.println(cpList);
		HashSet<ComputerPart> cpSet = new HashSet<>();
		for (String l : cpList) {
			cpSet.add(computerPartService.findComputerPartByName(l));
		}

		request.setComputerParts(cpSet);
		System.out.println(request + " <<<<<<<<<<<<<<<<<<<<<<<<<<<");

		// save request
		requestService.saveRequest(request);

	}
}
