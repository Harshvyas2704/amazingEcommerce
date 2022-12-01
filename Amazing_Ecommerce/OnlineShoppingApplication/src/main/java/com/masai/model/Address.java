package com.masai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	
	@NotNull(message = "please provide street name")
	private String street;
	
	@NotNull(message = "please provide building name")
	private String buildingName;
	
	@NotNull(message = "please provide city")
	private String city;
	
	@NotNull(message = "please provide state")
	private String state;
	
	@NotNull(message = "please provide country")
	private String country;
	
	@Pattern(regexp = "([1-9]{1}[0-9]{5}|[1-9]{1}[0-9]{3}\\\\s[0-9]{3})",
			 message = "please provide Indian pincode only")
	private String pincode;
	

}
