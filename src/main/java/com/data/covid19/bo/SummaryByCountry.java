package com.data.covid19.bo;
import lombok.Getter;
import lombok.Setter;

public class SummaryByCountry {
	@Setter
	@Getter
	private int ActiveCases;
	
	@Setter
	@Getter
	private float ActiveCasesRate;
	
	@Setter
	@Getter
	private String Country;
	
	@Setter
	@Getter
	private String CountryCode;
	
	@Setter
	@Getter
	private String Date;
	
	@Setter
	@Getter
	private float MortalityRate;
	
	@Setter
	@Getter
	private int NewConfirmed;
	
	@Setter
	@Getter
	private int NewDeaths;
	
	@Setter
	@Getter
	private int NewRecovered;
	
	@Setter
	@Getter
	private float RecoveryRate;
	
	@Setter
	@Getter
	private int TotalConfirmed;
	
	@Setter
	@Getter
	private int TotalDeaths;
	
	@Setter
	@Getter
	private int TotalRecovered;
}
