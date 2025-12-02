pipeline {
    agent any

    environment {
        // À adapter avec TON pseudo Docker Hub
        REGISTRY = 'trabelsinour123/devops'            // Docker Hub
        // Si tu préfères GitHub Container Registry :
        // REGISTRY = 'ghcr.io/trabelsinour123/devops'
        DOCKER_CREDENTIALS = 'dockerhub-trabelsi'      // ID des credentials dans Jenkins
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
            cleanWs()                        // Très important pour la validation
        }
        success {
            echo "Pipeline terminé avec succès ! Image disponible : ${REGISTRY}:${IMAGE_TAG}"
        }
        failure {
            echo "Échec de la pipeline"
        }
    }
}
