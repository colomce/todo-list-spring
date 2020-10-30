pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh "chmod +x gradlew"
                withGradle {
                    sh './gradlew build'
                }
            }
        }
        stage('Unit Test') {
            steps {
                echo 'Running Unit Tests..'
                withGradle {
                    sh './gradlew test --tests com.oocl.todoapp.unit*'
                }
            }
        }
        stage('Integration Test') {
            steps {
                echo 'Running Integration Tests..'
                withGradle {
                    sh './gradlew test --tests com.oocl.todoapp.Integration*'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}