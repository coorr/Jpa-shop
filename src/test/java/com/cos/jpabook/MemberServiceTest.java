package com.cos.jpabook;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cos.jpabook.domain.Member;
import com.cos.jpabook.repository.MemberRepository;
import com.cos.jpabook.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@Rollback(false)
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Test
	public void register() throws Exception {
		// given
		Member member = new Member();
		member.setName("Kimsadsa");
		
		// when
		Integer saveId = memberService.join(member);
		
		// then
		Assert.assertEquals(member, memberRepository.findone(saveId));
		
	}
	@Test
	public void 중복_회원_예외() throws Exception {
		Member member1 = new Member();
		member1.setName("kim3");
		Member member12 = new Member();
		member12.setName("kim3");
		
		memberService.join(member1);
		try {
			memberService.join(member12);
		} catch (IllegalStateException e) {
			return;
		}
		
		Assert.fail("예외가 발생한다.");
	}
}
