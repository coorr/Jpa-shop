//package com.cos.jpabook;
//
//import static org.junit.jupiter.api.Assertions.fail;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.cos.jpabook.domain.Member;
//
//
//@SpringBootTest
//class JapshopApplicationTests {
//
//	@Autowired MemberRepository memberRepository;
//	
//	@Test
//	@Transactional
//	@Rollback(false)
//	public void testMember() throws Exception {
//		
//		Member member = new Member();
//		member.setUsername("김진성");
//		
//		Integer saveId = memberRepository.save(member);
//
//		Member findMember = memberRepository.find(saveId);
//		
////		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
////		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//	}
//}
