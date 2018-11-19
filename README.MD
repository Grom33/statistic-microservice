# **Statistic Micro-service**
    
### Micro-service for collecting and storing statistics of visiting web site

Technology stack:
    
    - Spring Frameworks (Boot, DATA JPA, WEB, ACTUATOR)
    - PostgreSQL
    - Swagger API Documetation  
 

 1. For building the micro-service execute the command:
   
    `mvn clean package docker:build`
    
 2. For running the micro-service use Docker-composer, execute the command:
    
    `sudo docker-compose -f src/main/docker/docker-compose.yml up`
   
 3. Open in browser URL for checking this micro-service : `http://localhost:8099/swagger-ui.html`.  
   Swagger will show API, and give the controls for using REST interface. You can try to use this REST service via other API REST client utilities. For adding visit event send POST request to URL `http://localhost:8099/api/event` with JSON, by example `{"user":444444,"url":6733778}`, and you will get answer with JSON Object like this: `{"totalVisitsAmount": 1,"uniqueUsersAmount": 1}`  "totalVisitsAmount" - means total count of visits for today; "uniqueUsersAmount" - means count of unique users for today;
   For getting especial statistics for required period you can use URL: by sending GET request `http://localhost:8099/api/event/statistics` and you will get JSON object with statistic, like this ` {"totalVisitsAmount": 11, "uniqueUsersAmount": 1, "permanentUsersAmount": 0}`. 
    "totalVisitsAmount" - means total count of visits for required period;
    "uniqueUsersAmount" - means count of unique users for required period;
    "permanentUsersAmount" - means count of permanent users for required period, which have visited more than 10 different pages;
 