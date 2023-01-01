package com.example.caching.redisbasic.request;

import com.example.caching.redisbasic.dto.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class RedisJson extends RedisBaseRequest {

  @NotNull(message = "Person object is required.")
  private Person person;

}




