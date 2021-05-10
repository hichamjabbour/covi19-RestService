package com.data.covid19.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")  
public class UserEntity {
	  
	  @Setter
	  @Getter
	  @Id
	  @GeneratedValue( strategy=GenerationType.AUTO )
	  private Long id;

	  @Setter
	  @Getter
	  @Column
	  private String firstName;
	  
	  @Setter
	  @Getter
	  @Column
	  private String lastName;
	  
	  @Setter
	  @Getter
	  @Column(unique = true,nullable = false)
	  private String emailAdress;
	  
	  @Setter
	  @Getter
	  @Column
	  private String password;
	  
	  @Setter
	  @Getter
	  @Column(unique = true,nullable = true)
	  private Long phoneNumber;
	  
	  @Setter
	  @Getter
	  @Column
	  private int age;
	  
	  @Setter
	  @Getter
	  @ManyToOne
	  private CountryEntity countryEntity;
}
