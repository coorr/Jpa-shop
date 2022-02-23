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
import com.cos.jpabook.repository.OrderFlatDto;
import com.cos.jpabook.repository.OrderQueryDto;
import com.cos.jpabook.repository.OrderQueryRepository;
import com.cos.jpabook.repository.OrderRepository;
import com.cos.jpabook.repository.OrderSearch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

	private final OrderRepository orderRepository;
	private final OrderQueryRepository orderQueryRepository;
	
	@GetMapping("/api/v1/orders")  // 모든 데이터를 조회
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
	public List<OrderDtos> orderV2() { // 컬렉션 조회
		List<Order> orders = orderRepository.findAll(new OrderSearch());
		List<OrderDtos> collect = orders.stream()
								.map(o -> new OrderDtos(o))
								.collect(Collectors.toList());
		return collect;
	}
	@GetMapping("/api/v3/orders")
	public List<OrderDtos> orderV3() {  // 페치 조인으로 한번 쿼리로 조회 -  페이지 불가능(다대일, 일대일은 가능)
										// 컬렉션은 페치 조인 대신에 지연 로딩 유지 @BatchSize로 최적화로 해야함
		List<Order> orders = orderRepository.findAllWithItem();
		List<OrderDtos> collect = orders.stream()
								.map(o -> new OrderDtos(o))
								.collect(Collectors.toList());
		return collect;
	}
	
	@GetMapping("/api/v4/orders")			// 특정 컬렉션 조회 최적화 - 특정 아이템 한개일 경우
	public List<OrderQueryDto> orderV4() {  // 일반 조인 DTO 2개로 해서 직접 조회 3번쿼리
		return orderQueryRepository.findOrderQueryDtos();
	}
	
	@GetMapping("/api/v5/orders")    		// 일대다에서 많은 아이템을 출력할 때 유용함
	public List<OrderQueryDto> orderV5() {  //  v4랑 비슷한데 아이템 쿼리 한번으로 2번쿼리(인 쿼리)
											// IN 절을 활용해서 메모리에 미리 조회해서 최적화
		return orderQueryRepository.findByAllDto_optimization();
	}
	
	@GetMapping("/api/v6/orders")			// 일대다에서 1:n에서 n만큼 데이터가 출력됨
	public List<OrderFlatDto> orderV6() {  // 쿼리를 통해 1번 쿼리 - 페이지불가능
		return orderQueryRepository.findByAllDto_flat();
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





















