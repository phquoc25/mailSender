
pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /Users/quocphan/.m2:/root/.m2'
        }
    }

    parameters {
            string(name: 'BRANCH_NAME', defaultValue: 'master', description: 'How should I greet the world?')
    }

    stages {
        stage('Checkout') {
                steps {
                    echo "Checking out code"
                    checkout([$class: 'GitSCM',
                                branches: [[name: '*/${params.BRANCH_NAME}']],
                                doGenerateSubmoduleConfigurations: false,
                                extensions: [],
                                submoduleCfg: [],
                                userRemoteConfigs: [[url: '/Users/quocphan/SrcCodes/mailSender']]
                                ])
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