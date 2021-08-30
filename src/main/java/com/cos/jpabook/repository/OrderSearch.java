package com.cos.jpabook.repository;

import com.cos.jpabook.domain.OrderStatus;

import lombok.Data;

@Data
public class OrderSearch {
	private String memberName; // 회원이름
	private OrderStatus orderStatus; // 주문 상태(order,cancel)
}
