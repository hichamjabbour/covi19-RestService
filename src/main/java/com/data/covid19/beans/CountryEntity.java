package com.data.covid19.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="country")  
public class CountryEntity {
	  
	  
	  @Setter
	  @Getter
	  @Id
	  @GeneratedValue( strategy=GenerationType.AUTO )
	  private Long id;
	  
	  @Column(unique = true)
	  @Setter
	  @Getter
	  private String countryName;
}
