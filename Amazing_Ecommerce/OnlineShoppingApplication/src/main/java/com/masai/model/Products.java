package com.masai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
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
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@NotNull(message = "please input a valid product name")
	@Pattern(regexp = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$",
			 message = "please input a valid product name")
	private String productName;
	
	@NotNull(message = "please input a valid product name")
	private Double price;
	
	@Pattern(regexp = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$",
			 message = "please input a valid color")
	private String color; 
	
	private String dimension;
	
	private String specification;
	
	@NotNull(message = "please input a valid manufacturer")
	private String manufacturer;
	
	@Min(value = 1,message = "quantity should be minimum one")
	private Integer quantity;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	// this is to map enum with JPA
	// https://www.baeldung.com/jpa-persisting-enums-in-jpa
	// refer this link for further reference

}
