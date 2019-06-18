FROM maven:3.6-jdk-8 
WORKDIR /app
COPY --from=0 ./ ./
RUN mvn install

FROM openjdk:8-jre
WORKDIR /app
COPY --from=1 ./target/SuperHeroMissionSG-0.0.1-SNAPSHOT.jar ./
EXPOSE 8085
CMD ["java -jar SuperHeroMissionSG-0.0.1-SNAPSHOT.jar"]




