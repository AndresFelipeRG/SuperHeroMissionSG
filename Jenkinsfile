pipeline {
  
    agent any
    tools {
        maven 'Maven 3.3.9'
        jdk 'jdk8'
    }
    stage('Environment') {
      sh 'git --version'
      echo "Branch: ${env.BRANCH_NAME}"
      sh 'docker -v'
      sh 'printenv'
    }
    stage('Maven'){
      sh 'mvn install'
    }
}
