package com.data.covid19.bo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class BeanViewer {
	private final Logger LOG = LogManager.getLogger(getClass());

	  @EventListener
	  public void showBeansRegistered(ApplicationReadyEvent event) {
	    String[] beanNames = event.getApplicationContext()
	      .getBeanDefinitionNames();

	      for(String beanName: beanNames) {
	        LOG.info("{}", beanName);
	      }
	  }
}
