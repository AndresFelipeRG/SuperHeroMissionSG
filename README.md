## How to run Jenkins locally and upload app to Docker image inside Jenkins

- Install docker
- Get Docker image jenkins/jenkins: docker pull jenkins/jenkins
- Run the followring command inside the jenkins folder of the project: docker run -p 8080:8080 -v $PWD/jenkins:/var/jenkins_home jenkins
- Run the following command: docker-compose up -d
- Access localhost:8080. Enter the password, get the password by running docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
- Customize Jenkins by selecting the option Select plugins to install, and install all the plugins
- Add user info
- In Manage Jenkins, add Node JS installation(name: installation), maven(name: Maven 3.3.9), jdk(name: jdk8). For jdk you will have to create an oracle account and add the credentials.
- Create a multi-branch pipeline and select git as the SCM, also add this repository's url to the source url.
- Make sure the multi-branch pipeline scans the project for changes every minute.
