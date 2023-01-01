package com.example.caching.redismanager.service;

import com.example.caching.exception.PersonNotFoundException;
import com.example.caching.redisbasic.utility.Utility;
import com.example.caching.redismanager.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MysqlRedisBusinessService {

    private static final Logger logger = LoggerFactory.getLogger(MysqlRedisBusinessService.class);

    @Autowired
    private MysqlRedisService mysqlRedisService;


    public ResponseEntity<String> getPerson(long personId) throws JsonProcessingException, PersonNotFoundException {
        logger.info("MysqlRedisBusinessService - from business layer of personId {}", personId);
        Person person = mysqlRedisService.getPerson(personId);
        return new ResponseEntity<>(Utility.objectToJSONAndPrettyPrint(person), HttpStatus.OK);
    }

}
