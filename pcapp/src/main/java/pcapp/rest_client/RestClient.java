package pcapp.rest_client;

import java.util.List;

import org.springframework.http.HttpStatus;

import pcapp.models.Kid;

public interface RestClient {
	List<Kid> getAllKids();

	//Person getById(Long id);

	//HttpStatus addKid(Kid kid);

	//void updatePerson(Person person);

	//void deletePerson(Long id);

}
