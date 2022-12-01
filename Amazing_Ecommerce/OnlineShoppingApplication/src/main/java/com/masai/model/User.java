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
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@NotNull(message = "please input your name")	
	private String name;
	
	@NotNull(message = "please enter your mobile number")
	@Pattern(regexp = "[7896]{1}[0-9]{9}",
			 message = "provide a valid mobile number")
	private String mobile;
	
	@NotNull(message = "please type your password")
	private String password;

}
