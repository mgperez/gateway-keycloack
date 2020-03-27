### Running in dev mode

```
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=dev
# stop/terminate
Ctrl+C

Running Redis Rate Limiter Test
Make sure redis is running on localhost:6379 (using docker).
```

### Running in docker mode

```
# Build
./mvnw clean package spring-boot:repackage
docker-compose build

# Running
docker-compose up -d

# Stopping containers and cleaning
docker-compose down
```

To see what is currently running:

```
% docker-compose ps
State:  Up (healthy)
```

### Testing:

```
$ curl http://localhost:8762/get
$ curl -f http://localhost:8762/actuator/health
$ curl http://localhost:8762/actuator/gateway/routes

$ curl http://localhost:8762/actuator/gateway/routes/baeldung_route
$ http://localhost:8762/api/baeldung/
http://localhost:8762/ms_secure/baeldung/
http://localhost:8762/ibcs-manager/
```

Then run GatewayKeycloackApplicationTests. 