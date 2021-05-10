package com.data.covid19;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import com.data.covid19.configuration.MyBean;

/**
 * This is the entry class of our spring boot application and it configure the spring aoplication context.
 * Spring will scan for all componnents in this package and the ones inside it (@ScanComponent which is included in SpringBootApplication annotation)
 * The CommandLineRunner interface indicates that a bean should run when it is contained within a SpringApplication. It can be used to create command line applications in Spring Boot.
 * **/
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Covid19Application  extends SpringBootServletInitializer
implements CommandLineRunner 
{
	  /**
	   *  Now we can access the methods of the application context.
	   */
	  @Autowired
	  private ApplicationContext applicationContext; 
	  

	  @Override
	  /**
	   * Displaying the current application context name and id as well as displayng the scanned components.
	   */
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
