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
    - [x] Spring cloud
        - [x] Spring cloud config
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
        - [x] config
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

## Documentation. Swagger
* swagger-ui
```
http://localhost:8081/api/swagger-ui/index.html
```
* swagger api-doc
```
http://localhost:8081/api/v3/api-docs
```
* swagger version and url
```
http://localhost:8081/api/swagger-resources
```

## Start the application using docker
* prerequisites
    - docker (tested on 19.03.13)
    - docker-compose (not lower than 1.27.4)
    - java 11
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
DOCKER_PG_VOLUME_NAME=test_db_wst_volume
```

* add src/main/resources/application-credentials.yml file
```
vim src/main/resources/application-credentials.yml
```
with the content
```
db:
  db_wst:
    username: test_username
    password: test_password
```

* create a jar file
```
mvn clean package -Dmaven.test.skip=true
```

* run the app in the docker with the flag to remove container after termination
```
docker-compose -f docker-compose-for-test.yml up --build -d
```

* test the app is up and running
```
curl -X GET -i http://localhost:8081/api/countries
```

* stop the app in the docker
```
docker-compose -f docker-compose-for-test.yml down
```

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
java -jar ./target/*.jar --spring.profiles.active=h2 --spring.cloud.config.enabled=false
```

* check it works - in your browser http://localhost:8081/api/countries

* stop the app by pressing CTRL+C

## Run the application locally from the Intellij Idea
* download the app
```
git clone https://github.com/KSM-development/wst-backoffice.git
```
* import the app to the Intellij

* check Intellij settings
    * Project Structure: check java 11 is selected
    * Settings: Lombok plugin should be installed; enable annotation processing;

* add application-credentials.yml. Ask your admin for the file. It is in the artifactory repo <br>
It should use the same DB, username and password specified in the `./build-helper/docker-compose-postgres.yml`

* clean package the app

* run the postgres in the docker. From the project root run the command
```
docker-compose -f ./build-helper/docker-compose-postgres.yml -p backoffice-project up -d
```

* run wst-config-server

* run wst-auth-server (check the wst-auth-server README or documentation how to run it)

* configure the run/debug configuration with the VM options `-Dspring.profiles.active=local,credentials`

* run the app

* check it works
    * get auth token (check wst-auth-server documentation/README how to do it)
    * get countries with the retrieved token
    ```
    curl --location --request GET 'localhost:8081/api/countries' \
    --header 'Authorization: Bearer <token>'
    ```
    * some URIs are public and are not protected by security. You request them without token, e.g. all swagger URIs (see request above in this README.md)

* shutdown
    * stop the app
    * stop wst-auth-server
    * stop wst-config-server
    * stop auth db postgres in the docker (read wst-auth server how to do it)
    * stop backoffice db postgres in the docker
    ```
    docker-compose -f ./build-helper/docker-compose-postgres.yml -p backoffice-project down
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
    - ask your admin. The file placed in the secured private wst-artifactory repo

* #### Failed DB migration
    - remove from the table flyway_schema_history the row(s) with the last migration(s) that failed <br>
    - rerun the app

## Business requirements:
* Address management
* Warehouse management
* Goods management
* Supplier management
* Customer management

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
