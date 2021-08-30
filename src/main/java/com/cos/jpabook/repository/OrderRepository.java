package com.cos.jpabook.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.cos.jpabook.domain.Order;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Data
public class OrderRepository {

	private final EntityManager em;
	
	public void save(Order order) {
		em.persist(order);
	}
	public Order findOne(Integer id) {
		return em.find(Order.class, id);
	}
	
	public List<Order> findAll(OrderSearch orderSearch) {
		String jpql = "select o From Order o join o.member m";
		 boolean isFirstCondition = true;
		 //주문 상태 검색
		 if (orderSearch.getOrderStatus() != null) {
		 if (isFirstCondition) {
			 jpql += " where";
			 isFirstCondition = false;
		 } else {
			 jpql += " and";
		 }
		 jpql += " o.status = :status";
		 }
		 
		 //회원 이름 검색
		 if (StringUtils.hasText(orderSearch.getMemberName())) {
		 if (isFirstCondition) {
			 jpql += " where";
			 isFirstCondition = false;
		 } else {
			 jpql += " and";
		 }
		 	jpql += " m.name like :name";
		 }
		 
		 TypedQuery<Order> query = em.createQuery(jpql, Order.class)
		 .setMaxResults(1000); //최대 1000건
		 if (orderSearch.getOrderStatus() != null) {
			 query = query.setParameter("status", orderSearch.getOrderStatus());
		 }
		 if (StringUtils.hasText(orderSearch.getMemberName())) {
			 query = query.setParameter("name", orderSearch.getMemberName());
		 }
		 return query.getResultList();
	}
}












