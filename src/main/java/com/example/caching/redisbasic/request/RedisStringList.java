package com.example.caching.redisbasic.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
public class RedisStringList {

  @NotNull(message = "key is required.")
  private String key;

  @NotNull private List<String> names;

  private long ttl;
  private TimeUnit timeUnit;
}
