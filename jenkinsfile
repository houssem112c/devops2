pipeline {
    agent any
    stages {
        stage('Check Java Version') {
            steps {
                echo 'Checking Java version...'
                sh 'java -version' // Check the Java version
            }
        }
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'mvn clean package' // Example build command
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                sh 'mvn test' // Example test command
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                sh 'mvn deploy' // Example deploy command
            }
        }
        stage('GIT') {
            steps {
                echo 'Getting project from Git'
                sh 'git checkout main' // Adjust branch name as needed
                sh 'git pull origin main' // Pull latest changes
            }
        }
        stage('MVN CLEAN') {
            steps {
                echo 'Cleaning project...'
                sh 'mvn clean' // Command to clean the project
            }
        }
        stage('MVN COMPILE') {
            steps {
                echo 'Compiling project...'
                sh 'mvn compile' // Command to compile the project
            }
        }
        stage('SCM') {
            steps {
                echo 'Checking out SCM...'
                checkout scm
            }
        }
        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                script {
                    def mvn = tool 'Default Maven' // Ensure 'Default Maven' is configured in Jenkins
                    withSonarQubeEnv() {
                        sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=foyer -Dsonar.projectName='foyer'"
                    }
                }
            }
        }
    }
}
