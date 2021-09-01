package com.cos.jpabook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.jpabook.domain.Member;
import com.cos.jpabook.domain.Order;
import com.cos.jpabook.domain.item.Item;
import com.cos.jpabook.repository.OrderSearch;
import com.cos.jpabook.service.ItemService;
import com.cos.jpabook.service.MemberService;
import com.cos.jpabook.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {
 
	private final OrderService orderService;
	private final MemberService memberService;
	private final ItemService itemService;
	
	@GetMapping("/order")
	public String createForm(Model model) {
		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();
		
		model.addAttribute("items",items);
		model.addAttribute("members",members);
		
		return "order/orderForm";
	}
	
	@PostMapping("/order")
	public String create(@RequestParam("memberId") Integer memberId,
							@RequestParam("itemId") Integer itemId,
							@RequestParam("count") int count) {
		
		orderService.order(memberId, itemId, count);
		return "redirect:/orders";
	}
	
	@GetMapping("/orders")
	public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
		List<Order> orders = orderService.findOrders(orderSearch);
		model.addAttribute("orders",orders);
		
		return "order/orderList";
	}
	
	@PostMapping(value = "/orders/{orderId}/cancel")
	 public String cancelOrder(@PathVariable("orderId") Integer orderId) {
	 orderService.cancelOrder(orderId);
	 return "redirect:/orders";
	 }
	 
}












