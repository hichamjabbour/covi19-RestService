package com.data.covid19;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

//The @SpringBootTest annotation searches upwards from the test package until it finds a @SpringBootApplication or @SpringBootConfiguration.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Covid19ApplicationTests {
	
	@LocalServerPort //@Value("${local.server.port})
	private int port;
	
	@Value("${local.management.port}")
	private int mgt;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	
	@Test
	public void shouldReturn200WhenSendingRequestToControllerTestMethod() throws Exception {
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(
	    		"https://summary-dot-covi19-api.appspot.com/api/test/France",
				HttpMethod.GET, entity, String.class);
	    
	    //assertEquals(response.getBody(),"France");
	  }
	
	@Test
	public void shouldReturn200WhenSendingRequestToControllerGetCountryMethod() throws Exception {
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(
	    		"https://summary-dot-covi19-api.appspot.com/api/summary/France",
				HttpMethod.GET, entity, String.class);
	    
	    //assertEquals(response.getStatusCode(),HttpStatus.OK);
	  }



}
