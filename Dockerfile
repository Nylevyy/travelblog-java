# build executable jar
FROM gradle:8-jdk17 AS build
WORKDIR /tmp
COPY . .
RUN gradle bootJar

# production stage
FROM openjdk:17-jdk AS production
WORKDIR /opt/app
ARG JAR_FILE=/tmp/build/libs/travelblog-0.0.1-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
