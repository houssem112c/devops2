pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                // Add build steps here, e.g., compiling code
                sh 'mvn clean package' // Example build command
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                // Add test steps here, e.g., running unit tests
                sh 'mvn test' // Example test command
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // Add deploy steps here, e.g., deploying to a server
                sh 'mvn deploy' // Example deploy command
            }
        }
        stage('GIT') {
            steps {
                echo "Getting project from Git"
                // Add commands to checkout or pull the latest code
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
        stage('MVN SONARQUBE') {
            steps {
                echo 'Running SonarQube analysis...'
                sh 'mvn sonar:sonar -Dsonar.projectKey=<your_project_key> -Dsonar.host.url=http://<sonarqube_host>:9000 -Dsonar.login=<your_sonarqube_token>' // Update with actual values
            }
        }
    }
}
