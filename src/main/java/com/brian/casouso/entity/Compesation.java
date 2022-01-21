package com.brian.casouso.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Compesation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6353963609310956029L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private CompesationsType type;
	
	@Column
	private int amount;
	
	@Column
	private String description;
	
	@Column
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private LocalDate date;
	
	

	@Column
	private Long employee_id;
	

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	public Compesation(Long id) {
		super();
		this.id = id;
	}

	public Compesation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CompesationsType getType() {
		return type;
	}

	public void setType(String type) {
		this.type = CompesationsType.valueOf(type);
	}
	
	public void setType(CompesationsType type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	

}