package com.data.covid19.controller;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.covid19.bo.SummaryByCountry;
import com.data.covid19.services.SummaryByCountryService;

@RestController  
public class SummaryByCountryController {

	SummaryByCountryService summaryByCountryService;  
	
	 @GetMapping("/getSummaryByCountryDetails")  
	 public SummaryByCountry getSummaryByCountry(@RequestParam String name ) throws InterruptedException, ExecutionException{  
	 return summaryByCountryService.getSummaryByCountryDetails(name);  
	 }  
	 @PostMapping("/createSummaryByCountry")  
	 public String createSummaryByCountry(@RequestBody SummaryByCountry summaryByCountry ) throws InterruptedException, ExecutionException {  
	 return summaryByCountryService.saveSummaryByCountryDetails(summaryByCountry);  
	 }  
	 @PutMapping("/updateSummaryByCountry")  
	 public String updateSummaryByCountry(@RequestBody SummaryByCountry summaryByCountry ) throws InterruptedException, ExecutionException {  
	 return summaryByCountryService.updateSummaryByCountry(summaryByCountry);
	 }  
	 @DeleteMapping("/deleteSummaryByCountry")  
	 public String deleteSummaryByCountry(@RequestParam String name){  
	 return summaryByCountryService.deleteSummaryByCountry(name);
	 }  

}
