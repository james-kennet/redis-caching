package com.example.caching.redisbasic.dto;

import lombok.Data;

import java.util.List;

@Data
public class Person {

  private long id;
  private String name;
  private String gender;
  private List<Address> addresses;

}
