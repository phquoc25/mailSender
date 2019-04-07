
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
                    // GIT submodule recursive checkout
                    checkout scm: [
                            $class: 'GitSCM',
                            branches: scm.branches,
                            doGenerateSubmoduleConfigurations: false,
                            extensions: [[$class: 'SubmoduleOption',
                                          disableSubmodules: false,
                                          parentCredentials: false,
                                          recursiveSubmodules: true,
                                          reference: '',
                                          trackingSubmodules: false]],
                            submoduleCfg: [],
                            userRemoteConfigs: scm.userRemoteConfigs
                    ]

                    git branch: "${params.BRANCH_NAME}"
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