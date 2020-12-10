# World shop trade (WST) back-office
Used to manage reference information (addresses, shops, warehouses, item, clients, discounts etc)

## Stack of technologies plan:
- [x] Java 8 (
    - [ ] functional interface
    - [x] lambda
    - [x] stream api
    - [ ] Optional
    - [x] new collection api
    - [ ] new concurrency api
- [x] Git
- [x] Maven
- [x] Spring framework
    - [x] Spring boot
    - [x] Spring data jpa (hibernate)
    - [ ] Spring batch
    - [ ] Spring security
    - [ ] Spring redis
    - [ ] Spring kafka
    - [ ] Spring cloud
        - [ ] Spring cloud config
        - [ ] Spring cloud netflix Eureka
        - [ ] Spring cloud openfeign
        - [ ] Spring cloud circuit breaker
        - [ ] Spring cloud gateway
- [x] PostgreSql
- [ ] ElasticSearch
- [ ] Redis
- [x] Docker and docker-compose
- [ ] Kubernetes
- [ ] Jenkins
- [ ] AWS or GCP
- [x] Microservice architecture
    - [x] 12 factor app
        - [x] codebase
        - [x] dependencies
        - [ ] config
        - [x] backing services
        - [ ] build, release, run
        - [x] stateless processes
        - [x] port binding
        - [x] concurrency
        - [ ] disposability
        - [ ] dev/prod parity
        - [ ] logs
        - [ ] admin Processes or operational Tasks
    - [x] cloud native design patterns
        - [ ] event-driven
        - [ ] CQRS
        - [ ] saga patterns
        - [x] multiple service instances
        - [ ] canary deployments
        - [x] stateless services
- [x] 3 tier architecture (DAO, Service, Controller layers)
- [x] REST best practices (Richardson Maturity Model)
- [x] Unit testing with JUnit and Mockito
- [x] Integration testing with Rest Assured
- [ ] Integration testing use of docker for backing services
- [x] Mapstruct
- [x] Lombok

## Start the application using an in-memory H2 database
* download the app
```
git clone https://github.com/KSM-development/wst-backoffice.git
```
* go to main app directory
```
cd wst-backoffice
```

* create a jar file
```
mvn clean package -Dmaven.test.skip=true
```

* run the app
```
mvn spring-boot:run -Dspring-boot.run.arguments=--EXEC_ENVIRONMENT=h2
```

* check it works - in your browser http://localhost:8081/countries

* stop the app by pressing CTRL+C

## Start the application using docker
* download the app
```
git clone https://github.com/KSM-development/wst-backoffice.git
```
* go to the main app directory
```
cd wst-backoffice
```

* add .env file
```
vim .env
```
with the content
```
POSTGRES_DB=test_db_wst
POSTGRES_USERNAME=test_username
POSTGRES_PASSWORD=test_password
```

* add src/main/resources/application-credentials.yml file
```
vim src/main/resources/application-credentials.yml
```
with the content
```
db:
  db_wst:
    url: jdbc:postgresql://postgresqldb:5432/test_db_wst
    username: test_username
    password: test_password
```

* create a jar file
```
mvn clean package -Dmaven.test.skip=true
```

* run the app in the docker with the flag to remove container after termination
```
docker-compose up --build -d
```

* test the app is up and running
```
curl -X GET -i http://localhost:8081/countries
```

* stop the app in the docker
```
docker-compose down
```

### Troubleshooting and Tips
* #### Problems with volumes
    - find the volume your application uses and remove it
    ```
    docker volume ls // make sure wst_backoffice volume exists
    docker volume rm wst_backoffice // remove it. The db will be removed and recreated
    ```
    - if this does not work, remove all volumes :). All not external stopped containers data will be lost
    ```
    docker volume prune
    ```
    
* #### Where to get .env and application-credentials.yml files
    - ask your admin. The file is placed in the secured private wst-artifactory repo

* #### Failed DB migration
    - remove from the table flyway_schema_history the row(s) with the last migration(s) that failed <br>
    - rerun the app

## Business requirements:
* Address management
* Warehouse management
* Item management
* Client management
* User management

## Developers
* Sergiy Krokhmalniy [github](https://github.com/SerjiKSM)
* Oleg Pinta [github](https://github.com/Sabfir)
* Andriy Plytka [github](https://github.com/AndreyPlytka)
* Alexandra Plytka  [github](https://github.com/AlexandraPlytka)

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
