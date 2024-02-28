package com.example.carrotMarket.service;

import org.springframework.stereotype.Service;
import com.example.carrotMarket.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

}
