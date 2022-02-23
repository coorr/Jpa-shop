//package com.cos.jpabook;
//
//import javax.persistence.EntityManager;
//
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.cos.jpabook.domain.Address;
//import com.cos.jpabook.domain.Member;
//import com.cos.jpabook.domain.Order;
//import com.cos.jpabook.domain.OrderStatus;
//import com.cos.jpabook.domain.item.Book;
//import com.cos.jpabook.domain.item.Item;
//import com.cos.jpabook.repository.OrderRepository;
//import com.cos.jpabook.service.OrderService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class OrderServiceTest {
//
//	@Autowired EntityManager em;
//	@Autowired OrderService orderService;
//	@Autowired OrderRepository orderRepository;
//	
//	@Test
//	public void 상품주문() throws Exception {
//		 Member member = new Member();
//		 member.setName("회원1");
//		 member.setAddress(new Address("서울", "강가", "123-123"));
//		 em.persist(member);
//		 
//		 Book book = new Book();
//		 book.setName("시골 JAP");
//		 book.setPrice(10000);
//		 book.setStockQuantity(10);
//		 em.persist(book);
//		 
//		 int orderCount = 2;
//		 
//		 Integer orderId = orderService.order(member.getId(), book.getId(), orderCount);
//		 
//		 Order getOrder = orderRepository.findOne(orderId);
//		 Assert.assertEquals("상품 주문시 상태는 ORDER", OrderStatus.order,getOrder.getStatus());
//		 Assert.assertEquals("주문한상품 종류 수 ", 1, getOrder.getOrderItems().size());
//		 Assert.assertEquals("주문 가격 총 수량 " , 10000*orderCount, getOrder.getTotalPrice());
//		 Assert.assertEquals("주문 수량만큼 재고 줄어듬",  10, book.getStockQuantity());
//	}
//
//	@Test
//	public void 주문취소() throws Exception {
//		
//	}
//	
//	@Test
//	public void 상품주문_재고수량초() throws Exception {
//		
//	}
//	
//}
