package com.example.caching.redismanager.repo;

import com.example.caching.redismanager.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlRedisRepository extends JpaRepository<Person, Long> {

}
