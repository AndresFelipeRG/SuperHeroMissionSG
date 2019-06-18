FROM maven:3.6-jdk-8 
WORKDIR /app
COPY  ./ ./
RUN mvn install

FROM node:8-alpine
WORKDIR /app/webapp
RUN npm install
RUN npm install -g @angular/cli
EXPOSE 4200
RUN npm run build

FROM openjdk:8-jre
WORKDIR /app
COPY ./target/SuperHeroMissionSG-0.0.1-SNAPSHOT.jar ./
EXPOSE 8085
CMD ["java -jar SuperHeroMissionSG-0.0.1-SNAPSHOT.jar"]
CMD ["ls"]




