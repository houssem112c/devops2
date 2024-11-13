pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'houssem69/foyerapp:latest'
        DOCKER_REGISTRY = 'docker.io'
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
    }

    stages {
        stage('Setup Git Config') {
            steps {
                sh 'git config --global http.postBuffer 524288000'
            }
        }

        stage('Checkout GIT') {
            steps {
                echo 'Pulling from GitHub'
                git branch: 'benmabroukhoussem_5sim2_g5', url: 'https://github.com/houssem112c/devops-.git'
            }
        }

        stage('Check Project Files After Checkout') {
            steps {
                echo 'Listing project files after Git checkout...'
                sh 'pwd'
                sh 'ls -l'
            }
        }

        stage('Build without Tests') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Check JAR File') {
            steps {
                sh 'ls -l target/'
            }
        }

        stage('Test with JaCoCo') {
            steps {
                script {
                    def result = sh(script: 'mvn test jacoco:report', returnStatus: true)
                    if (result != 0) {
                        error "Tests failed, JaCoCo report not generated."
                    } else {
                        echo 'Tests passed successfully. JaCoCo report generated.'
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'sonarqube', variable: 'SONAR_TOKEN')]) {
                    sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.50.10:9000 -Dsonar.login=$SONAR_TOKEN'
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
                    sh '''
                    mvn deploy -DskipTests \
                    -DaltDeploymentRepository=projetdevops::default::http://192.168.50.10:8081/repository/projetdevops/ \
                    -Dmaven.deploy.username=$NEXUS_USERNAME \
                    -Dmaven.deploy.password=$NEXUS_PASSWORD
                    '''
                }
            }
        }

        stage('Login to Docker Registry') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE}")
                }
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub_credentials') {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Docker Compose') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        failure {
            echo 'Pipeline encountered an error.'
        }
    }
}
