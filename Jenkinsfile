pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = '36349eee-b43e-4dcb-beac-0ce0d9d3d905'
        DOCKER_IMAGE = 'saiki22/student-api' // Replace with your Docker Hub username
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean package -DskipTests=false'
            }
        }

        stage('Run JUnit Tests') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    COMMIT_SHA = bat(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                    bat "docker build -t %DOCKER_IMAGE%:%COMMIT_SHA% ."
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    COMMIT_SHA = bat(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                    withCredentials([usernamePassword(credentialsId: env.DOCKER_HUB_CREDENTIALS, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        bat 'echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin'
                        bat "docker push %DOCKER_IMAGE%:%COMMIT_SHA%"
                        bat 'docker logout'
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
