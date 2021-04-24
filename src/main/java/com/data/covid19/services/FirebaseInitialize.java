//@Service and @PostConstruct these are the two annotations from Spring Boot.


package com.data.covid19.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;




@Service  
public class FirebaseInitialize {  

//@Autowired
//private Environment env;
	
@Autowired
ResourceLoader resourceLoader;

@PostConstruct  
public void initialize() {  
	
	
    
    Resource resource = resourceLoader.getResource("classpath:serviceaccount.json");

    try (InputStream input = resource.getInputStream())
    {
        FirebaseOptions options = new FirebaseOptions.Builder()  
                .setCredentials(GoogleCredentials.fromStream(input))  
                .setDatabaseUrl("https://covid19-eurecom.firebaseio.com")
                .build(); 
        
        FirebaseApp.initializeApp(options);

    }
    
    catch (IOException e) {
        e.printStackTrace();
   
    }

	

}
}