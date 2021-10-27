# :whale: Just a simple Java DB connection project :coffee:
Maven based project with the simplest database connector class in java ``DriverManager``.


### :information_source: Database
- It is implemented from a docker container (mysql 8.0) ( at root directory ``metflix.Dockerfile``)
### :information_source: App
- [Data]     It uses DAO's for persisting with repository for simplifying it. 
- [Connection] For connection there's a builder class for adding settings as properties for the connection.
- [Tests]      Test are implemented with Junit4 and mockito (for mocking classes)



## :bulb: How to deploy

There are two ways

1. Using the ``docker-compose up -d`` in the root directory
2. Executing the `bash.sh`



In case you get some problems with database connection, notice this right after you deploy it:
> You will have to wait some seconds after the container is running. A databases takes some time to
> create its schemas and set it all. 

>There is not implemented yet a handler for this (Allow start the app untill database is set)


## :bulb:  How to test it

 Just using the ``mvn test`` but in case you have not installed as ``cli`` you'd better need an ide which is automatically by clicks.
