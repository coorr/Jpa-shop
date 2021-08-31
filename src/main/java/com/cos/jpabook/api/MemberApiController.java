package com.cos.jpabook.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.checkerframework.checker.units.qual.m;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.jpabook.domain.Member;
import com.cos.jpabook.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

	private final MemberService memberService;
	
	
	@GetMapping("/api/v1/members")
	public List<Member> memberV1() {
		return memberService.findMembers();
	}
	
	@GetMapping("/api/v2/members")
	public Result memberV2() {
		List<Member> members = memberService.findMembers();
		List<MemberDto> collect = members.stream()
									.map(m -> new MemberDto(m.getName(),m.getId()))
									.collect(Collectors.toList());
		
		return new Result(collect);
	}
	
	@Data @AllArgsConstructor
	static class Result<T> {
		private T data;
	}
	@Data @AllArgsConstructor
	static class MemberDto {
		private String name;
		private Integer age;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("api/v1/members")
	public CreateMemberRequest saveMemberV1(@RequestBody @Valid MemberRequest request) {
			Member member = new Member();
			member.setName(request.getName());
			
			Integer id = memberService.join(member);
			return new CreateMemberRequest(id);
	}
	
	@PutMapping("/api/v2/members/{id}")
	public UpdateMemberResponse updateMemberV2(@PathVariable("id") Integer id, @RequestBody @Valid UpdateMemberRequest request) {
		memberService.update(id, request.getName());
		Member findMember = memberService.findOne(id);
		return new UpdateMemberResponse(findMember.getId(),findMember.getName());
	}
	
	
	
	@Data
	static class UpdateMemberRequest {private String name;}
	
	@Data 
	@AllArgsConstructor
	static class UpdateMemberResponse { 
		private Integer id; private String name;
		}
	
	@Data
	static class MemberRequest {
		private String name;
		
	}
	@Data
	static class CreateMemberRequest {
		private Integer id;
		
		public CreateMemberRequest(Integer id) {
			this.id=id;
		}
	}
}
