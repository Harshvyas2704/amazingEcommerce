package com.masai.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrentUserSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(unique = true)
	private Integer UserId;
	
	private String uniqueId;
	
	private LocalDateTime timestamp;

	
	public CurrentUserSession(Integer userId, String uniqueId, LocalDateTime timestamp) {
		// to create object without Id
		
		super();
		UserId = userId;
		this.uniqueId = uniqueId;
		this.timestamp = timestamp;
	}
	
	

}
