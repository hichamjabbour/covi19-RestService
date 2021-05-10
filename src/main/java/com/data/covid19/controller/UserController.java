package com.data.covid19.controller;

import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.covid19.bo.User;
import com.data.covid19.services.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
	
	 @Autowired
	 UserService userService;
	
	 private final Logger LOG = LogManager.getLogger(getClass());
	 
	 @RequestMapping(value =  "/test/{name}",method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)  
	 public String getTestPathparam( @PathVariable(value = "name") String name) throws InterruptedException, ExecutionException{  
	 LOG.debug("getSummaryByCountryDetails with name=%s", name);
	 return name;  
	 } 
	 
	 @RequestMapping(value =  "/{emailAdress}",method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)  
	 public User getUserDetails( @PathVariable(value = "emailAdress") String emailAdress) throws InterruptedException, ExecutionException{  
	 LOG.debug("getUserDetails with emailAdress=%s", emailAdress);
	 return userService.getUserDetails(emailAdress);  
	 } 
	 
	 
	 @RequestMapping(value =  "/add",method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)  
	 public String saveUserDetails( @RequestBody User userDetails) throws InterruptedException, ExecutionException{  
	 LOG.debug("saveUserDetails with emailAdress=%s", userDetails.getEmailAdress());
	 if(userService.saveUserDetails(userDetails))
		 return "successfully created user";
	  return "user could not be created";
	 } 

}
