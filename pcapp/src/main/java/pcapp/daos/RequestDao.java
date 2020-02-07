package pcapp.daos;

import java.util.List;

import pcapp.entities.Request;

public interface RequestDao {
	
	void createRequest(Request request);
	
	Request findRequest(Long id);
	
	List<Request> findRequests();
	
	public void updateRequestStatus(Request request);

}
