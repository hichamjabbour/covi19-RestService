package com.data.covid19;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Covid19Application extends SpringBootServletInitializer{
	 
	  @Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(Covid19Application.class);
	   }

	public static void main(String[] args) {
		SpringApplication.run(Covid19Application.class, args);
	}
	
	

}
