package com.masai.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderid;
	
	private LocalDateTime localdatetime;
	
	private String orderstatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	Customer customer;

	@OneToMany(cascade = CascadeType.ALL)
	List<Products> productlist;
	
	@OneToOne(cascade = CascadeType.ALL)
	Address address;

}
