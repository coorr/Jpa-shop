package com.cos.jpabook.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.jpabook.domain.Delivery;
import com.cos.jpabook.domain.DeliveryStatus;
import com.cos.jpabook.domain.Member;
import com.cos.jpabook.domain.Order;
import com.cos.jpabook.domain.OrderItem;
import com.cos.jpabook.domain.item.Item;
import com.cos.jpabook.repository.ItemRepository;
import com.cos.jpabook.repository.MemberRepository;
import com.cos.jpabook.repository.OrderRepository;
import com.cos.jpabook.repository.OrderSearch;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	// 주문
	@Transactional
	public Integer order(Integer memberId, Integer itemId, int count) {
		// 엔티티 조회
		Member member = memberRepository.findone(memberId);
		Item item = itemRepository.findOne(itemId);
		
		// 배송정보 생성
		Delivery delivery =new Delivery();
		delivery.setAddress(member.getAddress());
		delivery.setStatus(DeliveryStatus.ready);
		
		// 주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		// 주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);
		
		// 주문 저장
		orderRepository.save(order);
		log.info("member : ", order.getMember()) ;
		return order.getId();
	}
	
	// 취소
	@Transactional
	public void cancelOrder(Integer orderId) {
		// 엔티티 조회
		Order order = orderRepository.findOne(orderId);
		// 주문 취소
		order.cancel();
	}
	
	// 검색
	public List<Order> findOrders(OrderSearch ordersearch) {
		return orderRepository.findAll(ordersearch);
	}
}






















