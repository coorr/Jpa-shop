package com.cos.jpabook.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;
	
	private LocalDateTime orderDate;   // 주문시간
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status; // 주문상태 - order, cancel
	
	//==연관관계 메서드==//
	 public void setMember(Member member) {
	 this.member = member;
	 member.getOrders().add(this);
	 }
	 public void addOrderItem(OrderItem orderItem) {
	 orderItems.add(orderItem);
	 orderItem.setOrder(this);
	 }
	 public void setDelivery(Delivery delivery) {
	 this.delivery = delivery;
	 delivery.setOrder(this);
	 }
	
	
}
