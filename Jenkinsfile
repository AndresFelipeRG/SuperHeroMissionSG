pipeline {
  
    agent any
    tools {
        maven 'Maven 3.3.9'
        jdk 'jdk8'
    }
    stages{
      stage('Environment Info') {
        steps{
          sh 'git --version'
          echo "Branch: ${env.BRANCH_NAME}"
          sh 'docker -v'
          sh 'printenv'
        }
      }
      stage('Maven: add pom dependencies and run tests'){
        steps{
         sh 'mvn install'
        }
      }
      stage('Docker: Build and Deploy'){
        steps{
          sh 'docker build --tag superheromission --no-cache .'
          sh 'docker tag superheromission localhost:5000/superheromission'
          sh 'docker push localhost:5000/superheromission'
          sh 'docker rmi -f superheromission localhost:5000/superheromission'
        }
      }
      
   }
}
