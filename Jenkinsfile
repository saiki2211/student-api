pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = '36349eee-b43e-4dcb-beac-0ce0d9d3d905'
        DOCKER_IMAGE = 'saiki22/student-api'
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
                    bat(script: 'git rev-parse --short HEAD > sha.txt', returnStdout: false)
                    def sha = readFile('sha.txt').trim()
                    env.COMMIT_SHA = sha
                    bat "docker build -t ${env.DOCKER_IMAGE}:${sha} ."
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: env.DOCKER_HUB_CREDENTIALS, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        bat """
                            echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                            docker push ${env.DOCKER_IMAGE}:${env.COMMIT_SHA}
                            docker logout
                        """
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
