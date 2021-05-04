package com.data.covid19;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Covid19Application  extends SpringBootServletInitializer
implements CommandLineRunner // The CommandLineRunner interface indicates that a bean should run when it is contained within a SpringApplication. It can be used to create command line applications in Spring Boot.
{
	  @Autowired
	  private ApplicationContext applicationContext; // Now we can access the methods of the application context.


	  @Override
	  public void run(String... args) throws Exception {
		  System.out.println(applicationContext.getDisplayName());
	      System.out.println(applicationContext.getId());
	      MyBean myBean = applicationContext.getBean(MyBean.class);
	      System.out.println(myBean.getApplicationId());
		
	  }
	 
	  @Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(Covid19Application.class);
	   }

	public static void main(String[] args) {
		SpringApplication.run(Covid19Application.class, args);
	}

	
	

}
