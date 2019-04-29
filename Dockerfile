FROM openjdk:8

COPY target/personalpage-webapp.jar /personalpage-webapp.jar

EXPOSE 8080

ENV POSTGRES_PASSWORD=4cc0413eb0a3147923e909b71453de08a278420e3184566d11e6ae5c8e53c1c0
ENV POSTGRES_USER=peqksetenpjnog
ENV POSTGRES_DB=dmohrj0k6s4s7
ENV START_DELAY=5

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "personalpage-webapp.jar"]