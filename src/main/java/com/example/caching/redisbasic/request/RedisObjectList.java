package com.example.caching.redisbasic.request;

import com.example.caching.redisbasic.dto.Person;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

@Data
public class RedisObjectList {

  @NotNull(message = "key is required.")
  private String key;

  @NotNull(message = "hashKey is required.")
  private String hashKey;

  @NotNull
  private Person person;

  private long ttl;
  private TimeUnit timeUnit;
}
