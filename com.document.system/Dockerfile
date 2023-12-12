FROM openjdk:17-oracle
EXPOSE 8050
RUN mkdir /var/storage
RUN chmod -R 777 /var/storage
ARG JAR_FILE=target/com.document.system-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]