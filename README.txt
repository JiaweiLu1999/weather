Spring Cloud Homework
-------------------------------------------------------------------------------
1. add your 2 rest applications into this project
    - in module named [school]

2. use config service to load configuration
    - modify [gateway-dev.properties] in [config-repo]
    - add [school-dev.properties] to [config-repo]

3. in search service
    - use CompletableFuture + restTemplate
            => fetch student info from student service + fetch 3rd party api from demo service
    - endpoint: /weather/demo

4. configure Hystrix (circuit) in one service
    - add Hystrix dependencies
    - add @HystrixCommand annotation to search service and provide default method

5. configure swagger documentation in search service
    - add Swagger dependencies
    - add @EnableSwagger2 and @Import(SpringDataRestConfiguration.class) to SwaggerConfig class

6. configure student service in gateway
    - add [school-dev.properties] to [config-repo]


How to run this application
1. java 8 / java 11
2. generate key pair + key store, save / configure in config service
3. start config service first