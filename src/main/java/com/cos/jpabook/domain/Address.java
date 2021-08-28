package com.cos.jpabook.domain;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable // jpa 내장타입, 어딘가에 내장할 수 있게
@Data
public class Address {
	private String city;
	private String street;
	private String zipcode;

	protected Address() {

	}

	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}

}
