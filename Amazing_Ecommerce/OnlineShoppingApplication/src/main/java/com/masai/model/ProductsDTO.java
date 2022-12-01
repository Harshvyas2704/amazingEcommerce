package com.masai.model;

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
public class ProductsDTO {
	
	@NotNull(message = "please provide product name")
	@Pattern(regexp = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$",
		 	 message = "please provide a valid product name")
	private String productName;
	
	@NotNull(message = "please provide product price")
	private Double price;

}
