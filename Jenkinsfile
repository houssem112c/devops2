pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'houssem69/foyerapp'
        DOCKER_IMAGE_VERSION = 'latest'
        DOCKER_REGISTRY = 'docker.io'
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
    }

    stages {
        stage('Git') {
            steps {
                echo 'Pulling from GitHub'
                git branch: 'user', url: 'https://github.com/houssem112c/devops-.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true clean package'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true test'
            }
        }

        stage('Run Sonar') {
            steps {
                withCredentials([string(credentialsId: 'sonarqube', variable: 'SONAR_TOKEN')]) {
                    sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.56.101:9000 -Dsonar.login=$SONAR_TOKEN'
                }
            }
        }

        stage('Maven Deploy') {
            steps {
                sh 'mvn deploy'
            }
        }

        stage('Login to Docker Registry') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    dir("${WORKSPACE}") {
                        sh "docker build -t houssem69/foyerapp:latest -f Dockerfile ."
                        sh "docker push houssem69/foyerapp:latest"
                    }
                }
            }
        }

        stage('JUNIT TEST with JaCoCo') {
            steps {
                sh 'mvn test jacoco:report'
                echo 'Test stage done'
            }
        }

        stage('Collect JaCoCo Coverage') {
            steps {
                jacoco(execPattern: '**/target/jacoco.exec')
            }
        }

        stage('Docker compose') {
            steps {
                sh "docker-compose up -d"
                sh 'sleep 60' // Adjust the sleep time based on your application's startup time
            }
        }

    }
}
