### Running in dev mode

```
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=dev -DKEYCLOAK_HOST=localhost:9090
# stop/terminate
Ctrl+C

Running Redis Rate Limiter Test
Make sure redis is running on localhost:6379 (using docker).
```

### Running in docker mode

```
# Build
./mvnw clean package spring-boot:repackage -DskipTests
docker-compose build
docker-compose config
docker-compose config --volume

# Running
docker-compose up -d

# Stopping containers and cleaning
docker-compose down
```

To see what is currently running:

```
% docker-compose ps
State:  Up (healthy)
% docker-compose logs -f gateway-keycloak
```

### Networking in Compose

https://docs.docker.com/compose/networking/

https://runnable.com/docker/docker-compose-networking

https://linuxize.com/post/how-to-remove-docker-images-containers-volumes-and-networks/

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