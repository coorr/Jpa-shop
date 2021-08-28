package com.cos.jpabook.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("A")
public class Album extends Item {  // 앨범
	private String artist;
	private String etc;
}
