package com.example.carrotMarket.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.carrotMarket.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberService memberService;

}
