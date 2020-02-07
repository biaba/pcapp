package pcapp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pcapp.daos.RequestDao;
import pcapp.entities.Request;
import pcapp.entities.RequestStatus;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	RequestDao requestDao;

	@Override
	@Transactional
	public void saveRequest(Request request) {
		requestDao.createRequest(request);
	}

	@Override
	@Transactional
	public String changeRequestStatus(Request request) {
		// Status change allowed from SUBMITTED to COMPLETED only (when required parts are provided to user). 
		// In case the user has not submitted the request, the status can not be changed to completed
		String message;
		try {
		if(request.getRequestStatus()==RequestStatus.SUBMITTED) {
			request.setRequestStatus(RequestStatus.COMPLETED);
			requestDao.updateRequestStatus(request);
			message="Status changed";
		}
		else {
			message="Not allowed";
		}
		
		}
		catch(Exception e){
			message="Not allowed";
		}
		
		return message;
	}

	
	@Override
	@Transactional
	public Request getRequest(Long id) {
		return requestDao.findRequest(id);
	}

	@Override
	@Transactional
	public List<Request> getRequests() {
		return requestDao.findRequests();
	}


}
