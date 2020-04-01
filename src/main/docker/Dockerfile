####
# https://examples.javacodegeeks.com/deploying-a-single-spring-boot-application-on-docker/
# This Dockerfile is used in order to build a container that runs the Quarkus application in JVM mode
#
# Before building the docker image run:
#
# mvn clean install
#
# Then, build the image with:
#
# docker build -f src/main/docker/Dockerfile.jvm -t jcgassignment .
#
# Then run the container using:
#
# docker run -d -p 9099:9099 jcgassignment
#
###

# Pull the centos image from DockerHub repository to build our application as a base
# The centos image will act as a base image
FROM centos
 
# Installing java to our image
RUN yum install -y java
 
# Creating a temporary directory
VOLUME /tmp
 
# Steps to run the spring boot application
# Copy the application’s jar from target directory and name it
ADD ./target/SpringbootOnDocker.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]