package com.cos.jpabook.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("M")
public class Movie extends Item {

	private String director;
	private String actor;
}
