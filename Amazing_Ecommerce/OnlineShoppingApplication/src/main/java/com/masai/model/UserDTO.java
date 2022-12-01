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
public class UserDTO {
	
	@NotNull(message = "enter your mobile number")
	@Pattern(regexp = "[7896]{1}[0-9]{9}",
			 message = "provide a valid mobile number")
	private String mobileNo;
	
	@NotNull(message = "enter your password")
	private String password;

}
