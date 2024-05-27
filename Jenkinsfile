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
        stage('Compiling') {
          steps {           
            withMaven(globalMavenSettingsConfig: 'maven-setting', jdk: 'JDK22', maven: 'Maven.3.9.6', mavenSettingsConfig: '', traceability: true) {
              sh 'mvn clean compile'
            }
          }
        }
        stage('Run Test') {
          steps {           
            withMaven(globalMavenSettingsConfig: 'maven-setting', jdk: 'JDK22', maven: 'Maven.3.9.6', mavenSettingsConfig: '', traceability: true) {
              sh 'mvn test'
            }
          }
        }
        stage('Packaging') {
          steps {           
            withMaven(globalMavenSettingsConfig: 'maven-setting', jdk: 'JDK22', maven: 'Maven.3.9.6', mavenSettingsConfig: '', traceability: true) {
              sh 'mvn package'
            }
          }
        }
        stage('Build image') {
          steps {           
            script{                
                withDockerRegistry(credentialsId: 'jenkins_docker_token', toolName: 'docker', url: '') {
                    //dockerImage = docker.build(registry + ":latest" , "src/main/resources")
                    dockerImage = docker.build registry + ":latest"
                }
            }
          }
        }
        stage('Trivy Scanning') {
           steps {
              sh 'curl -sfL https://raw.githubusercontent.com/aquasecurity/trivy/main/contrib/install.sh |sh -s -- -b /usr/local/bin v0.51.2'
              sh 'trivy image --debug --scanners vuln --timeout 30m --no-progress -o trivy.txt ${registry}:latest'
           }
        }
        stage('SonarQube Analysis') {
          steps {           
            withMaven(globalMavenSettingsConfig: 'maven-setting', jdk: 'JDK22', maven: 'Maven.3.9.6', mavenSettingsConfig: '', traceability: true) {
              sh 'mvn verify sonar:sonar -Dsonar.qualitygate.wait=true'
            }
          }
        }
        stage('Push image') {
          steps {           
            script{                
                withDockerRegistry(credentialsId: 'jenkins_docker_token', toolName: 'docker', url: '') {                    
                    dockerImage.push()
                }
            }
          }
        }    
        stage('Publish Artifact') {
          steps {           
            withMaven(globalMavenSettingsConfig: 'maven-setting', jdk: 'JDK22', maven: 'Maven.3.9.6', mavenSettingsConfig: '', traceability: true) {
              sh 'mvn deploy'
            }
          }
        }
        stage('Deploying image to k8s') {
          steps {
              withKubeConfig(credentialsId: 'kubernetes_token', clusterName: 'aks-mylife-uat-az1-poc1', contextName: 'aks-mylife-uat-az1-poc1', namespace: '', restrictKubeConfigAccess: false, serverUrl: 'https://aks-mylife-uat-az1-poc1-dns-ror8tpjt.hcp.southeastasia.azmk8s.io:443') {
                 sh 'curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"'
                 sh 'chmod u+x ./kubectl'
                 sh './kubectl apply -f deployment.yaml -n default'
              }
          }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'trivy.txt', fingerprint: true
            publishHTML(
                [allowMissing: false, 
                alwaysLinkToLastBuild: false, 
                keepAll: false, 
                reportDir: '.', 
                reportFiles: 'trivy.txt', 
                reportName: 'TrivyReport', 
                reportTitles: '', 
                useWrapperFileDirectly: true]
            )
        }
    }
}