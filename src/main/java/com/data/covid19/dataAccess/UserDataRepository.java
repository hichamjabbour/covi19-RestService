package com.data.covid19.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.data.covid19.beans.UserEntity;


/**
 * We are extending JpaRepository using two generics - UserEntity & Long. UserEntity is the entity that is being managed and the primary key of UserEntity (Which is Id) is of type Long.
 * We use @Transactional to make sure the save(…) operation is running in a transaction and to allow setting the readOnly-flag. This causes some performance optimizations inside the persistence provider as well as on the database level.
 */
public interface UserDataRepository extends JpaRepository<UserEntity, Long> {
	
	
	 /** Bind  the queries to the Java method that executes them using the Spring Data JPA @Query annotation with Named Parameters **/
	 @Query("select u from #{UserEntity} u where u.emailAddress = :emailAddress")
	 UserEntity findByEmailAddress(@Param("emailAddress")String emailAddress);
}
