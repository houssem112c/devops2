pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'bouchrif/universite-achrefmaddouri'
        DOCKER_IMAGE_VERSION = 'latest'
        DOCKER_REGISTRY = 'docker.io'
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
    }

    stages {
        stage('Git') {
            steps {
                echo 'Pulling from GitHub'
                git branch: 'moduleUniversite-achrefmaddouri', url: 'https://github.com/houssem112c/devops-.git'
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
            sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.login=$SONAR_TOKEN'
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
                        sh "docker build -t bouchrif/universite-achrefmaddouri:latest ."
                    sh "docker push bouchrif/universite-achrefmaddouri:latest"
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

      stage('Check JaCoCo Report') {
            steps {
                sh 'ls -alh target/site/jacoco/'  // Check if the JaCoCo report is generated
                echo 'Checked JaCoCo report generation'
            }
        }

        stage('Collect JaCoCo Coverage') {
            steps {
                jacoco(execPattern: 'target/jacoco.exec')  // Collect the JaCoCo coverage
                echo 'JaCoCo Coverage collected successfully'
            }
        }

        stage('Docker compose') {
            steps {
                sh "docker-compose up -d"
                sh 'sleep 60' // Adjust the sleep time based on your application's startup time
            }
        }
}  
    post {
        failure {
            echo 'Pipeline encountered an error.'
        }
    }
}
