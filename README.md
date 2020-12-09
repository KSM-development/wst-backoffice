# World shop trade(WST)

## Stack of technologies:
* Java 8 (functional interface, lambda, stream api, new collection api, new concurrency api)
* Git
* Maven
* Spring boot
* Spring data jpa (hibernate)
* Spring security
* Spring redis
* Spring kafka
* Spring cloud config
* Spring cloud openfeign
* Spring cloud circuit breaker
* Spring cloud gateway
* Spring batch
* Mysql
* Docker
* Microservice architecture
* 3 tier architecture
* REST best practices
* Unit testing with JUnit and Mockito
* Integration testing with Rest Assured
* Mapstruct
* Lombok

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

* stop the app click CTRL+C

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
* CRUD warehouse
* CRUD item
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
