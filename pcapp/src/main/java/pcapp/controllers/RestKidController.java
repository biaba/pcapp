package pcapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pcapp.entities.Kid;
import pcapp.services.KidService;


@RestController
@RequestMapping("/api")
public class RestKidController {

	
	@Autowired
	private KidService kidService;
	

// creating
	@PostMapping("/kids")
	public Kid createKid(@RequestBody Kid kid) {
		
		kidService.saveKid(kid);
		System.out.println("kid: ++++++++++++++" + kid);
		return kid;
		
	}
	
// reading
	
	@GetMapping("/kids")
	public List<Kid> getAllKids(){
		
		return kidService.getAll();
		
	}
	
	@GetMapping("/kids/{id}")
	public Kid getKid(@PathVariable int id) throws Exception {
		
		Kid kid = kidService.getOne(id);
		
		if (kid == null) {
			throw new Exception();
		}
		
		return kid;
	}
	
	
// updating
	@PutMapping("/kids")
	public Kid updateKid(@RequestBody Kid kid) {
		
		kidService.saveKid(kid);
		
		return kid;
		
	}
	
	
// deleting
	@DeleteMapping("/kids/{id}")
	public String deleteKid(@PathVariable int id) {
		kidService.deleteKid(id);
		return "kid with id: "+ id + " deleted";
	}	

}