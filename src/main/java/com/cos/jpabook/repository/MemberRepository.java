package com.cos.jpabook.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cos.jpabook.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository  // 스프링 빈으로 등록한 
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;
	
	public void save(Member member) {
		em.persist(member);
	}
	
	public Member findone(Integer id) {
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll() {
		List<Member> result =  em.createQuery("select m from Member m", Member.class).getResultList();
		return result;
	}
	
	public List<Member> findByName(String name) { 
		return em.createQuery("select m from Member m where m.name =:name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
	
}
