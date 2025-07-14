pipeline {
    agent any

    environment {
        // Docker Hub credentials ID stored in Jenkins
        DOCKER_HUB_CREDENTIALS = '36349eee-b43e-4dcb-beac-0ce0d9d3d905'
        // Docker Hub repository (replace with your Docker Hub username/repo)
        DOCKER_IMAGE = 'saiki22/student-api'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from the current branch
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                // Clean and build the project
                sh 'mvn clean package -DskipTests=false'
            }
        }

        stage('Run JUnit Tests') {
            steps {
                // Run tests separately for clarity
                sh 'mvn test'
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
                    // Get the current commit SHA
                    COMMIT_SHA = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                    // Build Docker image with commit SHA tag
                    sh "docker build -t $DOCKER_IMAGE:$COMMIT_SHA ."
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    // Get the current commit SHA again (if needed)
                    COMMIT_SHA = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                    // Login and push image
                    withCredentials([usernamePassword(credentialsId: env.DOCKER_HUB_CREDENTIALS, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                        sh "docker push $DOCKER_IMAGE:$COMMIT_SHA"
                        sh 'docker logout'
                    }
                }
            }
        }
    }

    post {
        always {
            // Clean up workspace after build
            cleanWs()
        }
    }
}

