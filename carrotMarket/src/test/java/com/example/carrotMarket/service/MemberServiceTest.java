package com.example.carrotMarket.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.carrotMarket.dto.MemberResDto;
import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private MemberService memberService;

	@DisplayName("내 프로필 조회 성공")
	@Test
	void successMyProfile() {
	    //given
		Long memberId = 1L;

		given(memberRepository.findById(anyLong())).willReturn(
			Optional.of(new Member(1L, "이름", "닉네임", "010-1234-5678", "주소", 1, 1, null, null)));

	    //when
		MemberResDto result = memberService.getMyProfile(memberId);

		//then
		assertThat(result.getAddress()).isEqualTo("주소");
		assertThat(result.getNickName()).isEqualTo("닉네임");
		assertThat(result.getName()).isEqualTo("이름");

	}

}
