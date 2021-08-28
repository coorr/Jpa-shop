package com.cos.jpabook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cos.jpabook.domain.Member;

@Repository
public class MemberRepository  {

//	@PersistenceContext
//	private EntityManager em;
//	
//	public Integer save(Member member) {
//		em.persist(member);
//		return member.getId();
//	}
//	
//	public Member find(Integer id) {
//		return em.find(Member.class, id);
//	}
}
