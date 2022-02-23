package com.cos.jpabook.repository;

import lombok.Data;

@Data
public class OrderItemQueryDto {

	private Integer orderId;
	private String itemName;
	private int orderPrice;
	private int count;
	
	public OrderItemQueryDto(Integer orderId, String itemName, int orderPrice, int count) {
		this.orderId = orderId;
		this.itemName = itemName;
		this.orderPrice = orderPrice;
		this.count = count;
	}
	

	
	
}
