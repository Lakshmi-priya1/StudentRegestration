pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'student-registration-app'
        DOCKER_TAG = 'latest'
        DOCKER_REGISTRY = 'docker.io'  // Change to your Docker registry if necessary
        REGISTRY_CREDENTIALS = 'docker-hub-credentials' // Jenkins credentials for Docker registry (if applicable)
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your Git repository
                git 'https://github.com/Lakshmi-priya1/StudentRegestration.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Build the application using Maven
                    // Change to './gradlew build' if you're using Gradle
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image using the Dockerfile
                    sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                }
            }
        }

        stage('Test Docker Image') {
            steps {
                script {
                    // Optionally, test the Docker image by running it
                    sh "docker run -d -p 8089:8089 ${DOCKER_IMAGE}:${DOCKER_TAG}"

                    // Add additional tests to verify the container is running (e.g., health checks, etc.)

                    // Stop and remove the container after testing
                    sh "docker ps -q --filter 'ancestor=${DOCKER_IMAGE}:${DOCKER_TAG}' | xargs docker stop"
                    sh "docker ps -a -q --filter 'ancestor=${DOCKER_IMAGE}:${DOCKER_TAG}' | xargs docker rm"
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // If you're using Docker Hub, login with credentials
                    // If you're pushing to a different registry, modify this accordingly
                    docker.withRegistry('https://${DOCKER_REGISTRY}', REGISTRY_CREDENTIALS) {
                        // Push the Docker image to the registry
                        sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                    }
                }
            }
        }

        stage('Cleanup') {
            steps {
                script {
                    // Optionally, clean up the local Docker image
                    sh "docker rmi ${DOCKER_IMAGE}:${DOCKER_TAG}"
                }
            }
        }
    }

    post {
        always {
            // Clean up the workspace after the build
            cleanWs()
        }
    }
}
