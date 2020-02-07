package pcapp.aop;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import pcapp.exceptions.RequestException;
import pcapp.exceptions.RequestNotFound;

@ControllerAdvice
public class GeneralControllerAdvice {

// removes empty spaces in registration form
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

// returns customized error page in case of Exception
	@ExceptionHandler(Exception.class)
	public String generalError(Exception e, Model model) {
		model.addAttribute("errorMsg", e.getLocalizedMessage());
		return "customError";
	}

// returns json error message if Request not found
	@ExceptionHandler(RequestException.class)
	public ResponseEntity<RequestNotFound> requestNotFound(RequestException e) {
		RequestNotFound rnf = new RequestNotFound();
		rnf.setStatus(HttpStatus.NOT_FOUND.value());
		rnf.setMessage(e.getMessage());
		rnf.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<> (rnf, HttpStatus.NOT_FOUND) ;
	}

}
