FROM openjdk:17-alpine
WORKDIR /opt
ENV PORT 9296
EXPOSE 9296
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar

#FROM maven:3.9.5-amazoncorretto-17
#WORKDIR /app
#COPY . /app/
#RUN mvn clean package -DskipTests
#EXPOSE 8761
#CMD ["mvn", "spring-boot:run"]