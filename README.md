## redis-caching service
redis-caching is a SpringBoot 2.X api service that provides basic examples of redis caching mechanism.
Below packages are:
- com.example.caching.redisbasic - Basic redis operation
- com.example.caching.redismanager - Caching with Redis as caching manager

### Prerequisite on running this service:
- At least Java 8
- Install Docker Desktop.

### Tech stack and services.
- Redis
- Docker
- MySql

### Steps in running redis-caching in local.
- Install/package redis-caching service.
```bash
mvn clean install
```
- Build and run mysql and redis databases and redis-caching service
```bash
docker-compose up -d
```
## Basic Redis Operations
### 1.a Create a String entry in redis.
```bash
curl --location --request POST 'http://localhost:8081/redis-caching/string' \
--header 'Content-Type: application/json' \
--data-raw '{
    "key": "key_string",
    "hashKey": "helloworld",
    "value": "Hello World",
    "ttl": 60,
    "timeUnit": "MINUTES"
}'
Response:
Success
```

### 1.b Retrieve the String.
```bash
curl --location --request GET 'http://localhost:8081/redis-caching/string/key_string/helloworld'
Response:
"Hello World"
```

### 2.a Create a JSON entry in redis.
```bash
curl --location --request POST 'http://localhost:8081/redis-caching/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "key": "person_json",
    "hashKey": "name",
    "person": {
        "id": 1,
        "name": "Michael",
        "gender": "Male",
        "addresses": [
            {
                "id": 1,
                "street": "Marina",
                "city": "Singapore",
                "country": "Singapore"
            },
            {
                "id": 2,
                "street": "285 Fulton St,",
                "city": "new York",
                "country": "United States"
            }
        ]
    },
    "ttl": 60,
    "timeUnit": "MINUTES"
}'
Response:
Success
```

### 2.b Retrieve the JSON.
```bash
curl --location --request GET 'http://localhost:8081/redis-caching/string/person_json/name'
Response:
{
  "id" : 1,
  "name" : "Michael",
  "gender" : "Male",
  "addresses" : [ {
    "id" : 1,
    "street" : "Marina",
    "city" : "Singapore",
    "country" : "Singapore"
  }, {
    "id" : 2,
    "street" : "285 Fulton St,",
    "city" : "new York",
    "country" : "United States"
  } ]
}
```

### 3.a Create an array of String entry in redis.
```bash
curl --location --request POST 'http://localhost:8081/redis-caching/stringlist' \
--header 'Content-Type: application/json' \
--data-raw '{
    "key": "person_list",
    "names": [
        "Rick",
        "Rock",
        "Richard",
        "Rose",
        "Ruby",
        "Rachel"
    ],
    "ttl": 60,
    "timeUnit": "MINUTES"
}'
Response:
Success
```

### 3.b Retrieve the JSON.
```bash
curl --location --request GET 'http://localhost:8081/redis-caching/stringlist/person_list'
Response:
[ "Rick", "Rock", "Richard", "Rose", "Ruby", "Rachel" ]
```
## Caching with Redis as caching manager - To Do
### 1. Test verifications on 