pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                // Add build steps here
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                // Add test steps here
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // Add deploy steps here
            }
        }
          stage('GIT') {
            steps {
                echo "Getting project form git";
                // Add deploy steps here
            }
          }
           stage('MVN CLEAN') {
            steps {
                sh '...........' 
            }
          }
          stage('MVN COMPILE') {
            steps {
            }
          }
          stage('MVN SONARQUBE') {
            steps {
            }
          }
    }
}
