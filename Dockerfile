FROM maven:3.6-jdk-8 
WORKDIR /app
COPY  ./ ./
RUN mvn install

FROM openjdk:8-jre
WORKDIR /app
COPY ./target/SuperHeroMissionSG-0.0.1-SNAPSHOT.jar ./
EXPOSE 8085
CMD ["java -jar SuperHeroMissionSG-0.0.1-SNAPSHOT.jar"]




