package pcapp.services;

import java.util.List;

import pcapp.entities.Request;

public interface RequestService {
	
	void saveRequest(Request request);
	
	String changeRequestStatus(Request request);
	
	Request getRequest(Long id);
	
	List<Request> getRequests();

}



