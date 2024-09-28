FROM gradle:7.6.4-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src
RUN chmod 777 *
RUN ./gradlew build --no-daemon --info

RUN apt-get update && apt-get upgrade -y && apt-get dist-upgrade -y && apt-get autoremove -y && rm -rf /var/lib/apt/lists/*

FROM amazoncorretto:17-alpine-jdk

EXPOSE 8000

RUN mkdir /app

COPY --from=build /home/gradle/src/example-ha-sdk17-infra/build/libs/*SNAPSHOT.jar /app/spring-boot-application.jar
COPY --from=build /home/gradle/src/example-ha-sdk17-infra/build/resources/main/keyClient.jks /app/keyClient.jks

RUN apk update && apk upgrade --available && apk add --no-cache

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar","--spring.profiles.active=sandbox"]
