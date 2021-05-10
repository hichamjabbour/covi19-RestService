package com.data.covid19.services;

import java.util.concurrent.ExecutionException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.data.covid19.beans.CountryEntity;
import com.data.covid19.beans.UserEntity;
import com.data.covid19.bo.User;
import com.data.covid19.configuration.CountryNotFoundException;
import com.data.covid19.configuration.UserNotFoundException;

@Service
public class UserService {
	 // private final Logger LOG = LogManager.getLogger(getClass());
		
	@PersistenceContext
    public EntityManager em;
	 
	 public User getUserDetails(String emailAdress) throws InterruptedException, ExecutionException {
		 
		 /**
		  * The CriteriaBuilder interface serves as the main factory of criteria queries and criteria query elements. It can be obtained either by the EntityManagerFactory's getCriteriaBuilder method or by the EntityManager's getCriteriaBuilder method (both methods are equivalent).
		  
		  CriteriaBuilder cb = em.getCriteriaBuilder();*/
		 
		  TypedQuery<UserEntity> typedQuery = em.createQuery("SELECT c FROM UserEntity c WHERE c.emailAdress = :emailAdress", UserEntity.class);
		  typedQuery.setParameter("emailAdress", emailAdress);
		  
		  UserEntity userEntity=null;
		  
		  try {
			  userEntity = typedQuery.getSingleResult();
		  }
		  
		  catch(Exception e)
		  {
			throw new UserNotFoundException("User not found", e.getCause());  
		  }

		 
		 User user = new User();
		 user.setFirstName(userEntity.getFirstName());
		 user.setLastName(userEntity.getLastName());
		 user.setEmailAdress(userEntity.getEmailAdress());
		 user.setPassword(userEntity.getPassword());
		 user.setPhoneNumber(userEntity.getPhoneNumber().toString());
		 user.setAge(new Integer(userEntity.getAge()).toString());
		 user.setCountryName(userEntity.getCountryEntity().getCountryName());
		 
		 return user;  
	  
		 }  
	 
	  @Transactional
      public boolean saveUserDetails(User userDetails) throws InterruptedException, ExecutionException {
		  
		  int age = 0;
		  Long phoneNumber = new Long(0);
		  
		  try { 
         
    	      age = new Integer(userDetails.getAge());
    	      phoneNumber = new Long(userDetails.getPhoneNumber());
		  }
		  
		  catch(Exception e)
			 {
				throw new UserNotFoundException("Wrong data input for the user", e.getCause());  
			 }
		
    	 
    	 if(age < 18)
			 throw new CountryNotFoundException("You are not allowed to create new users",new Throwable(String.valueOf(age)));

    	  
    	 final String countryName = userDetails.getCountryName();
    	 
    	 CountryEntity countryEntity=null;
		 TypedQuery<CountryEntity> typedQuery = em.createQuery("SELECT c FROM CountryEntity c WHERE c.countryName = :countryName", CountryEntity.class);
		 typedQuery.setParameter("countryName", "France");
		 
    	 try {   		 
    		 countryEntity =  typedQuery.getSingleResult();
    	 }
		 
		 catch(Exception e)
    	 {
			 throw new CountryNotFoundException("Country not found", e.getCause());   
    	 }			 
		 
		if(!countryEntity.getCountryName().equals(countryName))
		 throw new CountryNotFoundException("You are not allowed to create new users",new Throwable(countryName));

		 
		 UserEntity user = new UserEntity();
		 user.setFirstName(userDetails.getFirstName());
		 user.setLastName(userDetails.getLastName());
		 user.setEmailAdress(userDetails.getEmailAdress());
		 user.setPassword(userDetails.getPassword());
		 
		
			 user.setPhoneNumber(phoneNumber);
			 user.setAge(age);

		 
		

		 user.setCountryEntity(countryEntity);
		 
		 try
		 {
		   em.persist(user);
		 }
		 
		 catch(Exception e)
		 {
		   throw new UserNotFoundException("Could not create the user", e.getCause());  
		 }

		 return true;  
		 
	  
		 }  
}
