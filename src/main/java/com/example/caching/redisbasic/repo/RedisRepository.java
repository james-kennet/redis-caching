package com.example.caching.redisbasic.repo;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface RedisRepository {

  Boolean isKeyPresent(String hashKey);

  <HV> List<HV> getList(String hashKey, int start, int end);

  <HV> HV get(String key, String hashKey);

  <HK, HV> void save(String key, HK hashKey, HV value);

  <HK, HV> void save(String key, HK hashKey, HV value, long ttlValue, TimeUnit timeUnit);

  void save(String key, List<String> values);

  /**
  void saveAll(String key, List<String> values, long ttlValue, TimeUnit timeUnit);
  */
  void save(String key, List<String> values, long ttlValue, TimeUnit timeUnit);

}
