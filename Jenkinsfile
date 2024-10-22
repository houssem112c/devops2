pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                // Compile the code
                sh 'mvn clean package' // Example build command
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                // Run unit tests
                sh 'mvn test' // Example test command
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // Deploy to a server
                sh 'mvn deploy' // Example deploy command
            }
        }
        stage('GIT') {
            steps {
                echo 'Getting project from Git'
                // Checkout or pull the latest code
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
