package com.example.caching.redisbasic.service;

import com.example.caching.redisbasic.constants.Constants;
import com.example.caching.redisbasic.request.RedisJson;
import com.example.caching.redisbasic.request.RedisString;
import com.example.caching.redisbasic.request.RedisStringList;
import com.example.caching.redisbasic.utility.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

	@Autowired
	private CachingService cachingService;

	public ResponseEntity<String> get(String key, String hashKey) throws JsonProcessingException {
		String val = cachingService.get(key, hashKey);
		if(StringUtils.isNotBlank(val)){
			return new ResponseEntity<>(Utility.objectToJSONAndPrettyPrint(val), HttpStatus.OK);
		}
		return new ResponseEntity<>(key + " ==>> KEY and " + hashKey + " ==> hashKey NOT FOUND", HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<String> getAllFromList(String key) throws JsonProcessingException {
		if(cachingService.isKeyPresent(key)){
			List<String> list = cachingService.retrieveList(key, 0, -1);
			return new ResponseEntity<>(Utility.objectToJSONAndPrettyPrint(list), HttpStatus.OK);
		}
		return new ResponseEntity<>(key + " ==>> KEY NOT FOUND", HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<String> saveString(RedisString redisString) throws JsonProcessingException {
		cachingService.save(redisString);
		return new ResponseEntity<>(Constants.RESPONSE_SUCCESS, HttpStatus.CREATED);
	}

	public ResponseEntity<String> saveJsonAsString(RedisJson redisJson) throws JsonProcessingException {
		cachingService.save(redisJson);
		return new ResponseEntity<>(Constants.RESPONSE_SUCCESS, HttpStatus.CREATED);
	}

	public ResponseEntity<String> saveListOfString(RedisStringList redisStringList) throws JsonProcessingException {
		cachingService.save(redisStringList);
		return new ResponseEntity<>(Constants.RESPONSE_SUCCESS, HttpStatus.CREATED);
	}

}
