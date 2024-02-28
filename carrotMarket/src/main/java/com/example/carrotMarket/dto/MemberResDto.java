package com.example.carrotMarket.dto;

import com.example.carrotMarket.entity.member.Member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResDto {

	private String name;
	private String nickName;
	private String phoneNum;
	private String address;
	private int latitude;
	private int longitude;

	public MemberResDto(Member member) {
		this.name = member.getName();
		this.nickName = member.getNickName();
		this.phoneNum = member.getPhoneNum();
		this.address = member.getAddress();
		this.latitude = member.getLatitude();
		this.longitude = member.getLongitude();
	}
}
