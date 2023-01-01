FROM openjdk:8
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY target/*.jar redis-app.jar

#Adding wait
ENV WAIT_VERSION 2.7.2
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait

CMD ["java", "-jar", "redis-app.jar"]
EXPOSE 8081