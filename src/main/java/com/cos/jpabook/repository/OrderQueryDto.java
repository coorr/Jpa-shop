package com.cos.jpabook.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.cos.jpabook.domain.Address;
import com.cos.jpabook.domain.OrderStatus;

import lombok.Data;

@Data
public class OrderQueryDto {

	private Integer orderId;
	private String name;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	private Address address;
	private List<OrderItemQueryDto> orderItems;
	
	public OrderQueryDto(Integer orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus,
			Address address) {
		this.orderId = orderId;
		this.name = name;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.address = address;
		
	}
	


	
}
