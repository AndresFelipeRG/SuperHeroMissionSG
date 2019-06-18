FROM maven:3.6-jdk-8 
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY . ./
RUN mvn install




