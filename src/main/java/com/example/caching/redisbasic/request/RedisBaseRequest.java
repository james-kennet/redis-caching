package com.example.caching.redisbasic.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class RedisBaseRequest {

	@NotNull(message = "key is required.")
	private String key;

	@NotNull(message = "hashKey is required.")
	private String hashKey;

	private long ttl;
	private TimeUnit timeUnit;

}
