package com.example.caching.redismanager.service;

import com.example.caching.exception.PersonNotFoundException;
import com.example.caching.redismanager.entity.Person;
import com.example.caching.redismanager.repo.MysqlRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MysqlRedisService {

    private static final Logger logger = LoggerFactory.getLogger(MysqlRedisService.class);

    @Autowired
    private MysqlRedisRepository mysqlRedisRepository;

    @Cacheable(value="person", key="#personId")
    public Person getPerson(long personId) throws PersonNotFoundException {
        logger.info("MysqlRedisService - Retrieving person id {} in MySql", personId);
        Optional<Person> personOpt = mysqlRedisRepository.findById(personId);
        if(personOpt.isPresent()) {
            return personOpt.get();
        }
        throw new PersonNotFoundException("Person id " + personId + " not found.");
    }

}
