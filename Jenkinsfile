pipeline{
    agent {
        node {
            label 'java_jenkins'
        }
    }
    tools {
         dockerTool 'docker'
         maven 'Maven.3.9.6' 
         jdk 'JDK22'
         git 'git'
    }
    environment {
        registry = "riko20xx/springboot"
        dockerImage = ""
        DOCKER_HOST = "tcp://3.107.48.153:8443"
        DOCKER_CREDS = credentials('jenkins_docker_token')
        SCANNER_HOME = tool 'sonar-scanner'
    }    

     stages{
        stage('Hello') {
          steps {           
            echo "hello"
          }
        }
     }
}
