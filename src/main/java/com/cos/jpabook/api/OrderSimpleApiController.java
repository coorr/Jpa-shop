package com.cos.jpabook.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.jpabook.domain.Address;
import com.cos.jpabook.domain.Order;
import com.cos.jpabook.domain.OrderStatus;
import com.cos.jpabook.repository.OrderRepository;
import com.cos.jpabook.repository.OrderSearch;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

	private final OrderRepository orderRepository;
	
	@GetMapping("/api/v1/simple-orders")
	public List<Order> orderV1() {
		List<Order> all = orderRepository.findAll(new OrderSearch());
		for(Order order :all ) {
			order.getMember().getName();
			order.getDelivery().getAddress(); 
		}
		return all; 
	}
	
	@GetMapping("/api/v2/simple-orders")
	public List<SimpleOrderDto> orderV2() {
		List<Order> orders = orderRepository.findAll(new OrderSearch());
		List<SimpleOrderDto> collect = orders.stream()
										.map(o-> new SimpleOrderDto(o))
										.collect(Collectors.toList());
		return collect;
	}
	
	@GetMapping("/api/v3/simple-orders") 
	public List<SimpleOrderDto> orderV3() {
		List<Order> orders = orderRepository.findAllWithMemberDelivery();
		List<SimpleOrderDto> collect = orders.stream()
									.map(o -> new SimpleOrderDto(o))
									.collect(Collectors.toList());
		
		return collect;
	}
	
	@Data
	static class SimpleOrderDto {
		private Integer orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;
		
		public SimpleOrderDto(Order order) {
			orderId= order.getId();
			name= order.getMember().getName();
			orderDate=order.getOrderDate();
			orderStatus=order.getStatus();
			address=order.getDelivery().getAddress();
		}
	}
	
}


















