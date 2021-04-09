//@Service and @PostConstruct these are the two annotations from Spring Boot.


package com.data.covid19.services;

import java.io.FileInputStream;
import java.net.URI;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;




@Service  
public class FirebaseInitialize {  
static String userDirectory = System.getProperty("user.dir");

@Autowired
private Environment env;

@PostConstruct  
public void initialize() {  
	
try {  
StringBuilder sb = new StringBuilder("file:");
sb.append(userDirectory);
sb.append(env.getProperty("firebase.json.location"));
URL url = new URL(sb.toString());
FileInputStream serviceAccount =  
new FileInputStream(url.getPath());  
FirebaseOptions options = new FirebaseOptions.Builder()  
.setCredentials(GoogleCredentials.fromStream(serviceAccount))  
.setDatabaseUrl("https://covid19-eurecom.firebaseio.com")
.build(); 

FirebaseApp.initializeApp(options);

} catch (Exception e) {  
e.printStackTrace();  
}}}
