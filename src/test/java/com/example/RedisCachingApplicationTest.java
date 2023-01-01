package com.example;

import com.example.caching.redisbasic.controller.BasicCachingController;
import com.example.caching.redismanager.repo.MysqlRedisRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
class RedisCachingApplicationTest {

	@MockBean
	private MysqlRedisRepository mysqlRedisRepository;

	@Autowired
	private BasicCachingController basicCachingController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(basicCachingController);
	}

}