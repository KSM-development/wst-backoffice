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

## Start the application using docker
* download the app
$ git clone https://github.com/KSM-development/wst-backoffice.git

* go to main app directory
$ cd wst-backoffice

* create a jar file
$ mvn clean package

* run the app in the docker
$ docker-compose up

* stop the app in the docker
$ docker-compose down

* test the app is up and running
$ curl -X GET http://localhost:8081/addresses
$ curl -X GET http://localhost:8081/countries

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
