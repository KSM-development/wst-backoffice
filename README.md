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

## Start application with docker
* Dockerfile (this file gives the instructions how to build the docker image).
* Documentation describes the commands you can use in a Dockerfile -> https://docs.docker.com/engine/reference/builder/

* docker-compose.yml (this file we use for deploy containers combining all together )
* Documentation describes the commands you can use in a docker-compose.yml -> https://docs.docker.com/compose/compose-file/

## Commands need to do from root folder our application:
* mvn clean install  	// remove old target folder with files then create new target folder launch IT and create .jar file
* docker-compose up  	// download and create images, containers and run containers   
* docker-compose down	// stopping, removing containers 

## Also we can use:
* docker images // show docker images
* docker ps     // show runing docker containers
* docker ps -a  // show docker containers
* docker ps -aq // show containers id
* docker container stop [container name1] [container name2] [...] // stop container
* docker container start [container name1] [container name2] [...] // start container
* docker container rm [container name1] [container name2] [...] // remove containers
* docker container rm -f $(docker ps -aq) 		// remove all containers
* docker rmi -f [container name1] [container name2] [...] // remove images
* docker rmi -f $(docker images -a -q)  			// remove all images
* docker exec [OPTIONS] CONTAINER COMMAND [ARG...] // run a command in a running container -> https://docs.docker.com/engine/reference/commandline/exec/

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
