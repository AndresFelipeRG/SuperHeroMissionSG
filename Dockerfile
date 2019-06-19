FROM maven:3.6-jdk-8 
RUN mkdir -p /app/webapp
WORKDIR /app
COPY  ./ ./
COPY ./webapp ./webapp
RUN mvn install

FROM openjdk:8-jre
WORKDIR /app
COPY ./target/SuperHeroMissionSG-0.0.1-SNAPSHOT.jar ./
EXPOSE 8085
CMD ["java -jar SuperHeroMissionSG-0.0.1-SNAPSHOT.jar"]
CMD ["ls"]




