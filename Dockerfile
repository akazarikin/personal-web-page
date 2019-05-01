FROM openjdk:8
#FROM heroku/heroku:18

COPY target/personalpage-webapp.jar /personalpage-webapp.jar

ENV POSTGRES_PASSWORD=4cc0413eb0a3147923e909b71453de08a278420e3184566d11e6ae5c8e53c1c0
ENV POSTGRES_USER=peqksetenpjnog
ENV POSTGRES_DB=dmohrj0k6s4s7
ENV START_DELAY=5
ENV JAVA_OPTS="-Xss512k -Dspring.profiles.active=docker"

STOPSIGNAL SIGTERM
EXPOSE 8080
ENTRYPOINT ["java", "$JAVA_OPTS", "-jar", "personalpage-webapp.jar"]


#JAVA_OPTS=-Xss512k
#CMD ["java", "-Dserver.port=$PORT $JAVA_OPTS","-jar","target/personalpage-webapp.jar"]
CMD ["java", "--bind 8080:$PORT $JAVA_OPTS", "-jar", "target/personalpage-webapp.jar"]
#CMD java --bind 8080:$PORT -Dspring.profiles.active=docker target/personalpage-webapp.jar
#CMD ["java", "--bind 8080:$PORT -Dspring.profiles.active=docker", "-jar", "target/personalpage-webapp.jar"]

