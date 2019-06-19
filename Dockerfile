FROM maven:3.6-jdk-8 
RUN mkdir -p /app/webapp
WORKDIR /app
COPY  ./ ./
COPY ./webapp ./app/webapp
RUN mvn install

FROM node:12-alpine
WORKDIR /app/webapp
COPY ./webapp/package.json ./
COPY ./webapp/package-lock.json ./
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




