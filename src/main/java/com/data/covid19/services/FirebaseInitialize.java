//@Service and @PostConstruct these are the two annotations from Spring Boot.


package com.data.covid19.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;




@Service  
public class FirebaseInitialize {  

//@Autowired
//private Environment env;

@PostConstruct  
public void initialize() {  
	
Properties prop = new Properties();
	
try (InputStream inputStream = getClass()
             .getClassLoader().getResourceAsStream("application.properties")) {
         
         prop.load(inputStream);
         String userDirectory = prop.getProperty("user.dir");
         String firebaseLocation = prop.getProperty("firebase.json.location");

         StringBuilder sb = new StringBuilder("file:");
         sb.append(userDirectory);
         sb.append(firebaseLocation);
         URL url = new URL(sb.toString());
         FileInputStream serviceAccount =  
         new FileInputStream(url.getPath());  
         FirebaseOptions options = new FirebaseOptions.Builder()  
         .setCredentials(GoogleCredentials.fromStream(serviceAccount))  
         .setDatabaseUrl("https://covid19-eurecom.firebaseio.com")
         .build(); 

         FirebaseApp.initializeApp(options);


     } catch (IOException e) {
         e.printStackTrace();
    
     }
}
}