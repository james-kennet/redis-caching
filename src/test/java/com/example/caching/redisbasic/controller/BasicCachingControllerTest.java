package com.example.caching.redisbasic.controller;

import com.example.caching.redisbasic.constants.Constants;
import com.example.caching.redisbasic.request.RedisJson;
import com.example.caching.redisbasic.request.RedisString;
import com.example.caching.redisbasic.request.RedisStringList;
import com.example.caching.redisbasic.service.BusinessService;
import com.example.caching.redisbasic.utility.Utility;
import com.example.caching.redismanager.repo.MysqlRedisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.concurrent.TimeUnit.MINUTES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
class BasicCachingControllerTest {

  @MockBean
  private MysqlRedisRepository mysqlRedisRepository;

  @Autowired private MockMvc mvc;

  @MockBean private BusinessService businessService;

  private RedisString redisString;
  private RedisJson redisJson;
  private RedisStringList redisStringList;

  @BeforeEach
  public void setup() {
    redisString = RedisString.builder().key("key_string")
      .hashKey("helloworld")
      .value("Hello World")
      .ttl(60)
      .timeUnit(MINUTES).build();
  }

  @Test
  void getString() throws Exception {
    given(businessService.get(redisString.getKey(), redisString.getHashKey()))
        .willReturn(new ResponseEntity<>(redisString.getValue(), HttpStatus.OK));

    MockHttpServletResponse response =
        mvc.perform(
                get("/redis-caching/string/key_string/helloworld")
                    .accept(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("Hello World");
  }

  @Test
  void postString() throws Exception {
    given(businessService.saveString(redisString))
        .willReturn(new ResponseEntity<>(Constants.RESPONSE_SUCCESS, HttpStatus.CREATED));

    MockHttpServletResponse response =
        mvc.perform(
                post("/redis-caching/string")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Utility.objectToJSONAndPrettyPrint(redisString)))
            .andReturn()
            .getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    assertThat(response.getContentAsString()).isEqualTo(Constants.RESPONSE_SUCCESS);
  }

  @Test
  void postJson() {}

  @Test
  void getStringList() {}

  @Test
  void postStringList() {}

  @Test
  void testGetStringList() {}
}
