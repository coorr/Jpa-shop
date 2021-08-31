package com.cos.jpabook.repository;

import java.time.LocalDateTime;

import com.cos.jpabook.domain.Address;
import com.cos.jpabook.domain.Order;
import com.cos.jpabook.domain.OrderStatus;

import lombok.Data;

@Data
public class OrderSimpleQueryDto {

		private Integer orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;
		
		public OrderSimpleQueryDto(Order order) {
			orderId= order.getId();
			name= order.getMember().getName();
			orderDate=order.getOrderDate();
			orderStatus=order.getStatus();
			address=order.getDelivery().getAddress();
		
	}
}
