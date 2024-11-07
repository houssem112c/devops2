pipeline docker 
pipeline {
    agent any
    
    environment {
        GIT_HTTP_BUFFER_SIZE = '524288000'
        DOCKER_IMAGE = "houssem69/foyerapp"
        DOCKER_TAG = "${BUILD_NUMBER}"
        SONAR_TOKEN = credentials('sonarqubes')  // Assure que le token est stocké comme une crédential Jenkins

    }
    
    stages {
        stage('Récupération du code') {
            steps {
                git branch: 'user', url: 'https://github.com/houssem112c/devops-.git'
            }
        }
        
        stage('MVN clean') {
            steps {
                echo 'Running Maven clean...'
                sh 'mvn clean'
            }
        }
                stage('MVN package') {
            steps {
                echo 'Running Maven package...'
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('MVN build') {
            steps {
                echo 'Running Maven install...'
                sh 'mvn install -DskipTests'
            }
        }

        
        stage('Construction') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Construction Image Docker') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }
        
        stage('Push Image Docker') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockercredentials') {
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push("latest")
                    }
                }
            }
        }
        
        stage('Déploiement') {
            steps {
                echo "Déploiement de l'image ${DOCKER_IMAGE}:${DOCKER_TAG}"
            }
        }
     stage('MVN SONARQUBE') {
      environment {
                MAVEN_OPTS = "-Dsonar.login=${SONAR_TOKEN}"  // Corrected concatenation
            }
            steps {
                echo 'Exécution de l\'analyse SonarQube...'
                withSonarQubeEnv("${SONARQUBE_SERVER}") {
                    sh 'mvn sonar:sonar'
                }
            }
}

        stage('Upload Artifacts to Nexus') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
    echo 'Deploying artifacts to Nexus...'
    sh '''
        mvn deploy \
        -DskipTests \
        -DaltDeploymentRepository=nexuslogin::default::http://<nexus-server>/repository/maven-releases/ \
        -Dnexus.username="$NEXUS_USERNAME" \
        -Dnexus.password="$NEXUS_PASSWORD"
    '''
}

                }
            }
        }
    }
    
    post {
             success {
            echo 'Pipeline terminé avec succès.'
        }
        failure {
            echo 'Échec du pipeline.'
        }
    }
}