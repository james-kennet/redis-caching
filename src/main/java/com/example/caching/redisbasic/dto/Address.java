package com.example.caching.redisbasic.dto;

import lombok.Data;

@Data
public class Address {

	private long id;
	private String street;
	private String city;
	private String country;
}
