package com.cos.jpabook.domain.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.cos.jpabook.domain.Category;
import com.cos.jpabook.exception.NotEnoughStockException;

import lombok.Data;

@Entity
@Data
@Table(name = "item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   // 한개의 테이블에 많은 컬럼이 담겨있음
@DiscriminatorColumn(name = "dtype") //
public abstract class Item {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Integer id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<Category>();
	
	// 비즈니스 로직
	// stock 증가
	public void addStock(int quantity) {this.stockQuantity += quantity;}
	// stock 감소
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if(restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		} 
		this.stockQuantity = restStock;
	}
}
