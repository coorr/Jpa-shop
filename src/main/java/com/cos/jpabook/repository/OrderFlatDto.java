package com.cos.jpabook.repository;

import java.time.LocalDateTime;

import com.cos.jpabook.domain.Address;
import com.cos.jpabook.domain.OrderStatus;

import lombok.Data;

@Data
public class OrderFlatDto {
	
	private Integer orderId;
	private String name;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	private Address address;
	
	private String itemName;
	private int orderPrice;
	private int count;
	
	public OrderFlatDto(Integer orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address,
			String itemName, int orderPrice, int count) {
		this.orderId = orderId;
		this.name = name;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.address = address;
		
		this.itemName = itemName;
		this.orderPrice = orderPrice;
		this.count = count;
	}
	
}
