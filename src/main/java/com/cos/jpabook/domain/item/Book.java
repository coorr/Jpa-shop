package com.cos.jpabook.domain.item;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("B")
public class Book extends Item{

	private String author;
	private String isbn;
}
