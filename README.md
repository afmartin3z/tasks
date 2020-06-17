# Tasks app

This is a Spring Boot based Java app wich connects to a Oracle Database 11g instance running inside a Docker container.

Application assumes that a Oracle Database 11g with name tasksdb is deployed on the host tasksdb. Default credentials are below:
- Username: tasksdb
- Password: tasksdb
- Port: 1521
- SID: xe

### Deploy the REST api

As a pre-requisite, `Docker` needs to have been installed: https://docs.docker.com/compose/install/

**1. Clone the application**

```
$ git clone https://github.com/afmartin3z/tasks.git
```
```
$ cd tasks
```

**2. Deploy using docker compose**

You do not have to do anything if you use `docker-compose`. Docker Compose starts all containers in parallel.
Build and start the containers by running.


```
$ docker-compose up -d
```

The app should be available at `http://localhost:8080/api/task` if every thing goes well.


### Usage

Once the application has been deployed, the documentation for the REST services can be accessed using swagger in the url: `http://localhost:8080/swagger-ui.html`


