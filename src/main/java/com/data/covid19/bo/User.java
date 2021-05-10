package com.data.covid19.bo;

import lombok.Getter;
import lombok.Setter;

public class User {

  @Setter
  @Getter
  private String firstName;
  
  @Setter
  @Getter
  private String lastName;
  
  @Setter
  @Getter
  private String emailAdress;
  
  @Setter
  @Getter
  private String password;
  
  @Setter
  @Getter
  private String phoneNumber;
  
  @Setter
  @Getter
  private String age;
  
  @Setter
  @Getter
  private String countryName;
}
