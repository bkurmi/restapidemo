package com.techforum.api.restapidemo.beans;

import javax.persistence.Id;

public class Billionair {

	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String career;

	
	public Billionair() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Billionair(String firstName, String lastName, String career) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.career = career;
	}

	public Billionair(Long id, String firstName, String lastName, String career) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.career = career;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

}