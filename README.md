# Tasks app

![Java CI with Maven](https://github.com/afmartin3z/tasks/workflows/Java%20CI%20with%20Maven/badge.svg)

This is a Spring Boot based Java app wich connects to a Oracle Database 11g instance running inside a Docker container.

Application assumes that a Oracle Database 11g with name tasksdb is deployed on the host tasksdb. Default credentials are below:
- Username: tasksdb
- Password: tasksdb
- Port: 1521
- SID: xe

### Run with docker-compose

You do not have to do anything if you use `docker-compose`. Docker Compose starts all containers in parallel.
Build and start the containers by running

```
$ docker-compose up -d
```

The app should be available at `http://localhost:8080/api/task` if every thing goes well.


### Usage

Once the application has been starteded, the documentation for the rest services can be accessed using swagger in the url: `http://localhost:8080/swagger-ui.html`


