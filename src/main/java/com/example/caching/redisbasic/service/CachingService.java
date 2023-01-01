package com.example.caching.redisbasic.service;

import com.example.caching.redisbasic.utility.Utility;
import com.example.caching.redisbasic.repo.RedisRepository;
import com.example.caching.redisbasic.request.RedisJson;
import com.example.caching.redisbasic.request.RedisString;
import com.example.caching.redisbasic.request.RedisStringList;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CachingService {

    @Autowired
    private RedisRepository redisRepository;

    public boolean isKeyPresent(String key) {
        return redisRepository.isKeyPresent(key);
    }

    public <HV> List<HV> retrieveList(String key, int start, int end) {
        return redisRepository.getList(key, start, end);
    }

    public <HV> HV get(String key, String hashKey) {
        return redisRepository.get(key, hashKey);
    }

    public void save(RedisString redisString) {
        if(redisString.getTtl() > 0){
            redisRepository.save(redisString.getKey(), redisString.getHashKey(), redisString.getValue(),
                                 redisString.getTtl(), redisString.getTimeUnit());
        } else {
            redisRepository.save(redisString.getKey(), redisString.getHashKey(), redisString.getValue());
        }
    }

    public void save(RedisJson redisJson) throws JsonProcessingException {
        if(redisJson.getTtl() > 0){
            redisRepository.save(redisJson.getKey(), redisJson.getHashKey(), Utility.objectToJSONAndPrettyPrint(redisJson.getPerson()),
                                 redisJson.getTtl(), redisJson.getTimeUnit());
        } else {
            redisRepository.save(redisJson.getKey(), redisJson.getHashKey(), Utility.objectToJSONAndPrettyPrint(redisJson.getPerson()));
        }
    }

    public void save(RedisStringList redisStringList) {
        if(redisStringList.getTtl() > 0){
            redisRepository.save(redisStringList.getKey(), redisStringList.getNames(),
                                 redisStringList.getTtl(), redisStringList.getTimeUnit());
        } else {
            redisRepository.save(redisStringList.getKey(), redisStringList.getNames());
        }
    }

}
