package com.example.caching.redisbasic.controller;

import com.example.caching.redisbasic.request.RedisJson;
import com.example.caching.redisbasic.request.RedisString;
import com.example.caching.redisbasic.request.RedisStringList;
import com.example.caching.redisbasic.service.BusinessService;
import com.example.caching.redisbasic.service.CachingService;
import com.example.caching.redisbasic.utility.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/${spring.application.name}")
public class BasicCachingController {

  private static final Logger logger = LoggerFactory.getLogger(BasicCachingController.class);

  @Autowired private CachingService cachingService;

  @Autowired private BusinessService businessService;

  @PostMapping("/string")
  public ResponseEntity<String> saveString(@Valid @RequestBody RedisString redisString)
    throws JsonProcessingException {
    logger.info("postString with redisString object request body: {}", Utility.objectToJSONAndPrettyPrint(redisString));
    return businessService.saveString(redisString);
  }

  @GetMapping("/string/{key}/{hashKey}")
  public ResponseEntity<String> retrieveString(@PathVariable String key, @PathVariable String hashKey)
      throws JsonProcessingException {
    logger.info("getString with key: {} and hashLey: {}", key, hashKey);
    return businessService.get(key, hashKey);
  }

  @PostMapping("/json")
  public ResponseEntity<String> saveJson(@Valid @RequestBody RedisJson redisJson)
      throws JsonProcessingException {
    logger.info("postJson with redisJson object request body: {}", Utility.objectToJSONAndPrettyPrint(redisJson));
    return businessService.saveJsonAsString(redisJson);
  }

  @GetMapping("/stringlist/{key}")
  public ResponseEntity<String> getStringList(@PathVariable String key)
      throws JsonProcessingException {
    logger.info("getStringList with key: {}", key);
    return businessService.getAllFromList(key);
  }

  @PostMapping("/stringlist")
  public ResponseEntity<String> postStringList(@Valid @RequestBody RedisStringList redisStringList)
      throws JsonProcessingException {
    logger.info("postJson with redisStringList object request body: {}", Utility.objectToJSONAndPrettyPrint(redisStringList));
    return businessService.saveListOfString(redisStringList);
  }

}
