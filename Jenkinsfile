pipeline {
    agent any

    tools {
        git 'git' // This specifies the Git installation you added in the Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Lakshmi-priya1/StudentRegestration.git'
            }
        }

        stage('Build Jar') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Start with Docker Compose') {
            steps {
                sh 'docker-compose down' // Clean any previous containers
                sh 'docker-compose up -d --build'
            }
        }

        stage('Wait and Test') {
            steps {
                echo 'Waiting for services to be ready...'
                sh 'sleep 20'
                sh 'curl -f http://localhost:8089 || echo "App not responding!"'
            }
        }

        stage('Stop Containers') {
            steps {
                sh 'docker-compose down'
            }
        }
    }

    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs()
        }
    }
}


