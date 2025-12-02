pipeline {
    agent any

    environment {
        REGISTRY = 'trabelsinour123/devops'
        DOCKER_CREDENTIALS = 'dockerhub-trabelsi'
        IMAGE_TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Récupération du code') {
            steps {
                echo "Checkout du dépôt GitHub..."
                checkout scm
            }
        }

        stage('Nettoyage et compilation') {
            steps {
                echo "Nettoyage et compilation Maven..."
                sh 'chmod +x ./mvnw'                    // ← Correction du bug
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Construction de l\'image Docker') {
            steps {
                script {
                    dockerImage = docker.build("${REGISTRY}:${IMAGE_TAG}")
                    dockerImageLatest = docker.build("${REGISTRY}:latest")
                }
            }
        }

        stage('Push de l\'image sur Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS) {
                        dockerImage.push()
                        dockerImageLatest.push()
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo "Pipeline réussie ! Image : ${REGISTRY}:${IMAGE_TAG}"
        }
        failure {
            echo "Échec de la pipeline"
        }
    }
}
