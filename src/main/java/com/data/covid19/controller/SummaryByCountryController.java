package com.data.covid19.controller;

import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.covid19.bo.SummaryByCountry;
import com.data.covid19.services.SummaryByCountryService;


@RestController
@RequestMapping(path = "/api")
public class SummaryByCountryController {
	// @Autowired
	// private Environment env;
	 
	 @Autowired
	 public SummaryByCountryService summaryByCountryService;  
		
	 private final Logger LOG = LogManager.getLogger(getClass());
	 
	 @RequestMapping(value =  "/test/{name}",method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)  
	 public String getTestPathparam( @PathVariable(value = "name") String name) throws InterruptedException, ExecutionException{  
	 LOG.debug("getSummaryByCountryDetails with name=%s", name);
	 return name;  
	 } 

	 @RequestMapping(value =  "/summary/{name}",method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)  
	 public SummaryByCountry getSummaryByCountry( @PathVariable(value = "name") String name) throws InterruptedException, ExecutionException{  
	 LOG.debug("getSummaryByCountryDetails with name=%s", name);
	 return summaryByCountryService.getSummaryByCountryDetails(name);  
	 } 
	 
	 @RequestMapping(value =  "/createSummary",method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)  
	 public String createSummaryByCountry(@RequestHeader("name") String name ,@RequestHeader("password") String password,@RequestBody SummaryByCountry summaryByCountry ) throws InterruptedException, ExecutionException { 
	 LOG.debug("createSummaryByCountry" );
	 return summaryByCountryService.saveSummaryByCountryDetails(summaryByCountry);
	 }  
	 
	 @RequestMapping(value =  "/updateSummary",method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)  
	 public String updateSummaryByCountry(@RequestHeader("name") String name ,@RequestHeader("password") String password ,@RequestBody SummaryByCountry summaryByCountry ) throws InterruptedException, ExecutionException {  
	  LOG.debug("updateSummaryByCountry");
	  return summaryByCountryService.updateSummaryByCountry(summaryByCountry);
	 }  
	 
	 @RequestMapping(value =  "/deleteSummary/{name}",method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE)  
	 public String deleteSummaryByCountry(@PathVariable(value = "name") String name) throws InterruptedException, ExecutionException{ 
	 LOG.debug("deleteSummaryByCountry with name=%s", name);		 
	 return summaryByCountryService.deleteSummaryByCountry(name);
	 }  
}
