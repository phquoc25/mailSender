
pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /Users/quocphan/.m2:/root/.m2'
        }
    }

    parameters {
            string(name: 'BRANCH_NAME', defaultValue: 'master', description: 'Which branch name would you like to build?')
    }

    stages {
        stage('Checkout') {
                steps {
                    echo "=========Checking out code============="

                    git branch: "${params.BRANCH_NAME}", url: "https://github.com/phquoc25/mailSender.git"
                }
            }

        stage('Build') {
            steps {
                echo "Building ${params.BRANCH_NAME}"
                sh 'mvn -B -DskipTests clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}