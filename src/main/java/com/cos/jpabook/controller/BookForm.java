package com.cos.jpabook.controller;

import lombok.Data;

@Data
public class BookForm {

	private Integer id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	private String author;
	private String isbn;
}
