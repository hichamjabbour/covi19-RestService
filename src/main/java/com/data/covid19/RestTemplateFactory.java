package com.data.covid19;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//the RestTemplate with Basic Authentication will require manual intervention, so instead of declaring the bean directly, a Spring FactoryBean will be used for more flexibility.
@Component
public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {
    
    private RestTemplate restTemplate;
    
	@Autowired
    public RestTemplate getObject() {
        return restTemplate;
    }
    public Class<RestTemplate> getObjectType() {
        return RestTemplate.class;
    }
    public boolean isSingleton() {
        return true;
    }

    @SuppressWarnings("deprecation")
	public void afterPropertiesSet() {
        HttpHost host = new HttpHost("localhost", 8080, "http");
        restTemplate = new RestTemplate(
          new HttpComponentsClientHttpRequestFactoryBasicAuth(host));
        
        restTemplate.getInterceptors().add(
        		  new BasicAuthorizationInterceptor("name", "password"));
    }
}
