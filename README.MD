# **Statistic Microservice**
    
### Microservice for collecting and storing statistics of visiting web site
## Features
    - Adding event of visit web site
    - Getting statistics about visiting for required period
Technology stack:
    - Spring Frameworks (Boot, DATA JPA, WEB, ACTUATOR)
    - PostgreSQL
    - Swagger API Documetation  
 ## Getting Started

### Prerequisites
    - Java 8
    - Docker 
### Quickstart
    1. git clone https://github.com/Grom33/statistic-microservice.git 
    2. cd [respository name]
    3.`mvn clean package docker:build`
    4.`docker-compose -f src/main/docker/docker-compose.yml up`
###  quick guide 
    1. Open in browser URL for checking this micro-service : `http://localhost:8099/swagger-ui.html`. 
    Swagger will show API, and give the controls for using REST interface. You can try to use this REST 
    service via other API REST client utilities. 
    2. For adding visit event send POST request to URL `http://localhost:8099/api/event` with JSON, 
    by example `{"user":11111,"url":22222}`where "user" is user ID and "url" is URL id, and you will 
    get answer with JSON Object like this: `{"totalVisitsAmount": 1,"uniqueUsersAmount": 1}`  
    "totalVisitsAmount" - means total count of visits for today; 
    "uniqueUsersAmount" - means count of unique users for today;
    3. For getting especial statistics for required period you can use URL: by sending GET request 
    `http://localhost:8099/api/event/statistics` with params start and end dates in ISO format and 
    you will get JSON object with statistic, like this 
    `{"totalVisitsAmount": 11, "uniqueUsersAmount": 1, "permanentUsersAmount": 0}`. 
    "totalVisitsAmount" - means total count of visits for required period;
    "uniqueUsersAmount" - means count of unique users for required period;
    "permanentUsersAmount" - means count of permanent users for required period, which have visited more 
    than 10 different pages;
 
