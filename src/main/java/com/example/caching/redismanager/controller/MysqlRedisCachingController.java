package com.example.caching.redismanager.controller;

import com.example.caching.exception.PersonNotFoundException;
import com.example.caching.redismanager.service.MysqlRedisBusinessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/${spring.application.name}")
public class MysqlRedisCachingController {

  private static final Logger logger = LoggerFactory.getLogger(MysqlRedisCachingController.class);

  @Autowired
  private MysqlRedisBusinessService mysqlRedisBusinessService;



  @GetMapping("/mysql-redis/{id}")
  public ResponseEntity<String> getString(@PathVariable String id)
    throws JsonProcessingException, PersonNotFoundException {
    logger.info("Start retrieving person id {}", id);
    return mysqlRedisBusinessService.getPerson(Long.parseLong(id));
  }

}
