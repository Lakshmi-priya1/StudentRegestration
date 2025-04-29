pipeline {
    agent any

    environment {
        // Set any environment variables you need here
        GIT_HOME = 'C:/Program Files/Git'
    }

    stages {
        // Stage to checkout the code from GitHub
        stage('Checkout') {
            steps {
                checkout scm: [
                    $class: 'GitSCM', 
                    branches: [[name: '*/main']], // Adjust the branch name as per your requirement
                    userRemoteConfigs: [[url: 'https://github.com/Lakshmi-priya1/StudentRegestration.git']]
                ]
            }
        }

        // Stage to build the project (add your build commands here)
        stage('Build') {
            steps {
                echo 'Building the project...'
                // Add your build steps here, for example, using Maven or Gradle
                // sh 'mvn clean install' or any other build tool you use
            }
        }

        // Stage to build a Docker image if required
        stage('Build Docker Image') {
            steps {
                echo 'Building Docker Image...'
                // Example of building Docker image using Dockerfile
                // sh 'docker build -t studentregistration-image .'
            }
        }

        // Stage to start the application with Docker Compose (if needed)
        stage('Start with Docker Compose') {
            steps {
                echo 'Starting application with Docker Compose...'
                // Example command to start with Docker Compose
                // sh 'docker-compose up -d'
            }
        }

        // Stage to run tests (you can add your testing steps here)
        stage('Test') {
            steps {
                echo 'Running tests...'
                // Add your testing steps here, for example:
                // sh 'mvn test' or any other testing tool
            }
        }

        // Stage to stop Docker containers (if needed)
        stage('Stop Containers') {
            steps {
                echo 'Stopping Docker containers...'
                // Example command to stop containers
                // sh 'docker-compose down'
            }
        }
    }

    // Post actions (cleaning up or notifying after pipeline run)
    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs() // Cleans up the workspace after the pipeline run
        }

        success {
            echo 'Pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}



