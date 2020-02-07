package pcapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import pcapp.dtos.CrmUser;

@Component
@Aspect
public class EmailAspect {
	
	@Autowired
	public JavaMailSender emailSender;
	
	@After("execution(* pcapp.controllers.LoginController.sumbitRegistration(..))" )
	public void emailToAdminUserCreated(JoinPoint jp) {
		CrmUser[] crm = (CrmUser[]) jp.getArgs();
		CrmUser crmUser = crm[0];
		System.out.println("AFTER +++ Email to admin will be sent here ++++++++++++"+ crmUser);
		SimpleMailMessage message = new SimpleMailMessage(); 
	    message.setTo("baiba.skujevska@gmail.com"); 
	    message.setSubject("test"); 
	    message.setText("text comes here"); 
	    
	    emailSender.send(message);
	}
	
	@Before("execution(* pcapp.controllers.LoginController.sumbitRegistration(..))" )
	public void emailToAdminUserNotCreatedYet(JoinPoint jp) {
		CrmUser[] crm = (CrmUser[]) jp.getArgs();
		CrmUser crmUser = crm[0];
		System.out.println("BEFORE +++ Email to admin will be sent here ++++++++++++"+ crmUser);
		SimpleMailMessage message = new SimpleMailMessage(); 
	    message.setTo("baiba.skujevska@gmail.com"); 
	    message.setSubject("test"); 
	    message.setText("text comes here"); 
	    
	    emailSender.send(message);
	}

}
