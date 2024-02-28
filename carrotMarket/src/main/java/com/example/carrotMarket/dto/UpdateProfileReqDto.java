package com.example.carrotMarket.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UpdateProfileReqDto {

	private String name;
	private String nickName;
	private String phoneNum;
	private String address;
	private int latitude;
	private int longitude;

	public UpdateProfileReqDto(String name, String nickName, String phoneNum, String address, int latitude,
		int longitude) {
		this.name = name;
		this.nickName = nickName;
		this.phoneNum = phoneNum;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
