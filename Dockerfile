FROM openjdk:8

ADD build/libs/restapidemo-0.0.1-SNAPSHOT.jar billionairs-api.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080