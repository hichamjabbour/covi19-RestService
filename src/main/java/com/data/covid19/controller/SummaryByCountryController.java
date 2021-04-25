package com.data.covid19.controller;

import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.covid19.bo.SummaryByCountry;
import com.data.covid19.services.SummaryByCountryService;

@RestController
public class SummaryByCountryController {
	 @Autowired
	 public SummaryByCountryService summaryByCountryService;  
		
	 private final Logger LOG = LogManager.getLogger(getClass());
	 
	 @RequestMapping(value =  "/api/test/{name}",method = RequestMethod.GET , produces = "application/json")  
	 public String getTestPathparam( @PathVariable(value = "name") String name) throws InterruptedException, ExecutionException{  
	 LOG.debug("getSummaryByCountryDetails with name=%s", name);
	 return name;  
	 } 

	 @RequestMapping(value =  "/api/summary/{name}",method = RequestMethod.GET , produces = "application/json")  
	 public SummaryByCountry getSummaryByCountry( @PathVariable(value = "name") String name) throws InterruptedException, ExecutionException{  
	 LOG.debug("getSummaryByCountryDetails with name=%s", name);
	 return summaryByCountryService.getSummaryByCountryDetails(name);  
	 } 
	 
	 @RequestMapping(value =  "/api/createSummary",method = RequestMethod.POST , consumes = "application/json")  
	 public String createSummaryByCountry(@RequestBody SummaryByCountry summaryByCountry ) throws InterruptedException, ExecutionException { 
	 LOG.debug("createSummaryByCountry" );
	 return summaryByCountryService.saveSummaryByCountryDetails(summaryByCountry);  
	 }  
	 
	 @RequestMapping(value =  "/api/updateSummary",method = RequestMethod.PUT , consumes = "application/json")  
	 public String updateSummaryByCountry(@RequestBody SummaryByCountry summaryByCountry ) throws InterruptedException, ExecutionException {  
	  LOG.debug("updateSummaryByCountry");
	  return summaryByCountryService.updateSummaryByCountry(summaryByCountry);
	 }  
	 
	 @RequestMapping(value =  "/api/deleteSummary/{name}",method = RequestMethod.DELETE , consumes = "application/json")  
	 public String deleteSummaryByCountry(@PathVariable(value = "name") String name){ 
	 LOG.debug("deleteSummaryByCountry with name=%s", name);		 
	 return summaryByCountryService.deleteSummaryByCountry(name);
	 }  
}
