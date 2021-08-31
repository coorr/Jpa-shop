package com.cos.jpabook.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.jpabook.domain.Address;
import com.cos.jpabook.domain.Order;
import com.cos.jpabook.domain.OrderItem;
import com.cos.jpabook.domain.OrderStatus;
import com.cos.jpabook.repository.OrderRepository;
import com.cos.jpabook.repository.OrderSearch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

	private final OrderRepository orderRepository;
	
	@GetMapping("/api/v1/orders")
	public List<Order> orderV1() {
		List<Order> all = orderRepository.findAll(new OrderSearch());
		for(Order order : all ) {
			order.getMember().getName();
			order.getDelivery().getAddress();
			List<OrderItem> orderItems = order.getOrderItems();
			orderItems.stream().forEach(o -> o.getItem().getPrice());
		}
		return all;
	}
	
	@GetMapping("/api/v2/orders")
	public List<OrderDtos> orderV2() {
		List<Order> orders = orderRepository.findAll(new OrderSearch());
		List<OrderDtos> collect = orders.stream()
								.map(o -> new OrderDtos(o))
								.collect(Collectors.toList());
		return collect;
	}
	@GetMapping("/api/v3/orders")
	public List<OrderDtos> orderV3() {
		List<Order> orders = orderRepository.findAllWithItem();
		List<OrderDtos> collect = orders.stream()
								.map(o -> new OrderDtos(o))
								.collect(Collectors.toList());
		return collect;
	}
	
	
	@Getter
	static class OrderDtos {
		private Integer orderId;
		private String name;   
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;
		private List<OrderItemDto> orderItems;
		
		public OrderDtos(Order order) {
			orderId=order.getId();
			name=order.getMember().getName();
			orderDate=order.getOrderDate();
			orderStatus=order.getStatus();
			address=order.getDelivery().getAddress();
			orderItems=order.getOrderItems().stream()
					.map(orderItem -> new OrderItemDto(orderItem))
					.collect(Collectors.toList());
		}
	}
	@Getter
	static class OrderItemDto {
		private String itemName;
		private int orderPrice;
		private int count;
		
		public OrderItemDto(OrderItem orderItem) {
			itemName=orderItem.getItem().getName();
			orderPrice=orderItem.getOrderPrice();
			count=orderItem.getCount();
		}
	}
}





















