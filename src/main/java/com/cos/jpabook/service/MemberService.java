package com.cos.jpabook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.jpabook.domain.Member;
import com.cos.jpabook.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	@Autowired private final MemberRepository memberRepository;
	
	// 회원가입 
	@Transactional
	public Integer join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원이다다다");
		}
	}
	
	// 회원 전체 조회 
	
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Member findOne(Integer memberId) {
		return memberRepository.findone(memberId);
	}
	
	@Transactional
	public void update(Integer id, String name) {
		Member member = memberRepository.findone(id);
		member.setName(name);
	}
}
