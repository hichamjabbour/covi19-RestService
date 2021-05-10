package com.data.covid19.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring Boot injects the application context into the parameter of the setApplicationContext() method, where we get the Id of the Spring application. (The Id here is the name of the application.)
 */
@Component
public class MyBean implements ApplicationContextAware {

    private String applicationId;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) 
            throws BeansException {

        applicationId = applicationContext.getId();
    }

    public String getApplicationId() {

        return applicationId;
    }
}