package com.masai.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer customerId;
	
	@NotNull
	@Pattern(regexp = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$",
			 message = "please provide a valid firstname")
	private String firstName;
	
	@NotNull
	@Pattern(regexp = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$",
			 message = "please provide a valid lastname")
	private String lastName;
	
	@NotNull
	@Pattern(regexp = "[7896]{1}[0-9]{9}",
			 message = "please provide a valid mobile number")
	private String mobileNumber;
	
	@Pattern(regexp = "^(.+)@(\\S+)$",
			 message = "please provide a valid email address")
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> addresslist= new ArrayList<>();

}
