package pcapp.rest_client;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pcapp.entities.ComputerPart;
import pcapp.entities.Request;
import pcapp.entities.RequestStatus;
import pcapp.exceptions.RequestException;
import pcapp.models.Kid;
import pcapp.services.ComputerPartService;
import pcapp.services.RequestService;

@Service
public class RestClientImpl implements RestClient{

	private RestTemplate restTemplate;
	private String crmRestUrl;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public RestClientImpl(RestTemplate theRestTemplate,
							@Value("${rest.url}") String theUrl) {
	
		restTemplate = theRestTemplate;
		crmRestUrl = theUrl;
		logger.info("Loaded property: rest.url=" + crmRestUrl);
	}
	
	@Override
	public List<Kid> getAllKids() {
		
		logger.info("in getCustomers(): Calling REST API " + crmRestUrl);

		// make REST call
		ResponseEntity<List<Kid>> responseEntity = 
											restTemplate.exchange(crmRestUrl, HttpMethod.GET, null, 
																  new ParameterizedTypeReference<List<Kid>>() {});

		// get the list of customers from response
		List<Kid> kids = responseEntity.getBody();

		logger.info("in getCustomers(): customers" + kids);
		
		return kids;
	}


	/*
	@Autowired
	RequestService requestService;
	
	@Autowired
	ComputerPartService computerPartService;
	
	@Autowired
	SessionFactory sf;
	
// THIS WORKS	
	@GetMapping("/kids")
	public Kid getKid() {
		Kid kid = new Kid("mario");
		
		return kid;
	}
	
// THIS DOESN'T. 	
	@PostMapping("/kids")
	public Kid addKid(@RequestBody Kid kid) {
		
		//Session s = sf.getCurrentSession(); 
		//s.save(kid);
		System.out.println(kid);
		
		return kid;
	}

	@GetMapping("/requests")
	public List<Request> allRequests() {

		return requestService.getRequests();

	}

	@GetMapping("/requests/{id}")
	public Request getRequest(@PathVariable("id") int id) throws RequestException {
		if (requestService.getRequest((long) id) == null) {
			throw new RequestException("Request with id: " + id + " not found.");
		}
		return requestService.getRequest((long) id);
	}


*/

}
