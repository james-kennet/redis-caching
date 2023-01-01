package com.example.caching.redisbasic.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

  @Autowired private RedisTemplate<String, Object> redisTemplate;

  @Override
  public Boolean isKeyPresent(String hashKey) {
    return redisTemplate.hasKey(hashKey);
  }

  @Override
  public <HV> List<HV> getList(String hashKey, int start, int end) {
    ListOperations<String, Object> ops = redisTemplate.opsForList();
    return (List<HV>) ops.range(hashKey, start, end);
  }

  @Override
  public <HV> HV get(String key, String hashKey) {
    return (HV) redisTemplate.opsForHash().get(key, hashKey);
  }

  @Override
  public <HK, HV> void save(String key, HK hashKey, HV value) {
    redisTemplate.opsForHash().put(key, hashKey, value);
  }

  @Override
  public <HK, HV> void save(String key, HK hashKey, HV value, long ttlValue, TimeUnit timeUnit) {
    redisTemplate.opsForHash().put(key, hashKey, value);
    redisTemplate.expire(key, ttlValue, timeUnit);
  }

  @Override
  public void save(String key, List<String> values) {
    redisTemplate.opsForList().rightPushAll(key, values);
  }

  /**
   //Careful!!! this will save arrays of values in the next index
  @Override
  public void saveAll(String key, List<String> values, long ttlValue, TimeUnit timeUnit) {
    redisTemplate.opsForList().rightPushAll(key, values);
    redisTemplate.expire(key, ttlValue, timeUnit);
  }
   */

  @Override
  public void save(String key, List<String> values, long ttlValue, TimeUnit timeUnit) {
    for(String value : values){
      redisTemplate.opsForList().rightPush(key, value);
    }
    redisTemplate.expire(key, ttlValue, timeUnit);
  }
}