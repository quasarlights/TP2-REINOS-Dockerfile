FROM amazoncorretto:11-alpine-jdk
MAINTAINER gastonestebancampagno@gmail.com
COPY target/turnosrotativos-0.0.1-SNAPSHOT.jar  api-app.jar
ENTRYPOINT ["java","-jar","/api-app.jar"]


