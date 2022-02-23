package com.cos.jpabook.repository;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.swing.ListModel;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

	private final EntityManager em;
	
	public List<OrderQueryDto> findOrderQueryDtos() {
		List<OrderQueryDto> result = findOrders();
		
		result.forEach(o -> {
			List<OrderItemQueryDto> orderItems =findOrderItems(o.getOrderId());
			o.setOrderItems(orderItems);
		});
		return result;
	}
	
	public List<OrderQueryDto> findByAllDto_optimization() {
		List<OrderQueryDto> result = findOrders();
		
		List<Integer> orderIds = result.stream()
					.map(o -> o.getOrderId())
					.collect(Collectors.toList());
		
		List<OrderItemQueryDto> orderItems = em.createQuery(
				"select new com.cos.jpabook.repository.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
				" from OrderItem oi" +
				" join oi.item i" +
				" where oi.order.id in :orderIds", OrderItemQueryDto.class)
				.setParameter("orderIds", orderIds)
				.getResultList();
		
		Map<Integer, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
					.collect(Collectors.groupingBy(OrderItemQueryDto -> OrderItemQueryDto.getOrderId()));
		
		result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));
		return result;
		
	}
	
	public List<OrderItemQueryDto> findOrderItems(Integer orderId) {
		return em.createQuery(
				"select new com.cos.jpabook.repository.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
				" from OrderItem oi" +
				" join oi.item i" +
				" where oi.order.id = :orderId", OrderItemQueryDto.class)
				.setParameter("orderId", orderId)
				.getResultList();
	}
	
	public List<OrderQueryDto> findOrders() {
		return em.createQuery("select new com.cos.jpabook.repository.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)"
							+ " from Order o"
							+ " join o.member m"
							+ " join o.delivery d", OrderQueryDto.class).getResultList();
	}
	
	public List<OrderFlatDto> findByAllDto_flat() {
		return em.createQuery(
					"select new com.cos.jpabook.repository.OrderFlatDto(o.id, m.name, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count)" +
					" from Order o" +
					" join o.member m" +
					" join o.delivery d" +
					" join o.orderItems oi" +
					" join oi.item i", OrderFlatDto.class)
				.getResultList();
	}

	
}	
























