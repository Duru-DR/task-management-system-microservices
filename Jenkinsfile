pipeline {
    agent {
        docker {
            image 'fraqioui247/my-jenkins-agent:latest'
            args '-v /var/run/docker.sock:/var/run/docker.sock -u root'
        }
    }

    environment {
        IMAGE_TAG = "${env.GIT_COMMIT.take(7)}"
        REGISTRY = "docker.io/fraqioui247"
        REGISTRY_CREDENTIALS = "dockerhub-creds"
        PROFILE_DB_NAME = 'profile_db'
        PROFILE_DB_USER = 'profile_user'
        PROJECT_DB_NAME = 'project_db'
        PROJECT_DB_USER = 'project_user'
        TASK_DB_NAME = 'task_db'
        TASK_DB_USER = 'task_user'
        NOTIFICATION_DB_NAME = 'notification_db'
        NOTIFICATION_DB_USER = 'notification_user'
        PROFILE_DB_PASSWORD = credentials('profile-db-pass')
        PROJECT_DB_PASSWORD = credentials('project-db-pass')
        TASK_DB_PASSWORD = credentials('task-db-pass')
        NOTIFICATION_DB_PASSWORD = credentials('notification-db-pass')
        GITHUB_USERNAME = 'duru-DR'
        GITHUB_TOKEN = credentials('github-token')
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 60, unit: 'MINUTES')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            parallel {
                stage('Profile Service') {
                    steps { dir('profile-service') { sh 'mvn clean install -B -DskipTests' } }
                }
                stage('Project Service') {
                    steps { dir('project-service') { sh 'mvn clean install -B -DskipTests' } }
                }
                stage('Task Service') {
                    steps { dir('task-service') { sh 'mvn clean install -B -DskipTests' } }
                }
                stage('Notification Service') {
                    steps { dir('notification-service') { sh 'mvn clean install -B -DskipTests' } }
                }
                stage('Config Server') {
                    steps { dir('config-server') { sh 'mvn clean install -B -DskipTests' } }
                }
                stage('Eureka Server') {
                    steps { dir('eureka-server') { sh 'mvn clean install -B -DskipTests' } }
                }
                stage('API Gateway') {
                    steps { dir('spring-cloud-gateway') { sh 'mvn clean install -B -DskipTests' } }
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    docker.withRegistry("https://index.docker.io/v1/", REGISTRY_CREDENTIALS) {
//                         def services = ["profile-service", "project-service", "task-service", "notification-service", "config-server", "spring-cloud-gateway", "eureka-server"]
                        def services = ["profile-service", "config-server"]

                        for (srv in services) {
                            echo "Building Docker image for ${srv}"
                            def image = docker.build("${REGISTRY}/${srv}:${IMAGE_TAG}", "./${srv}")
                            image.push()
                        }
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                sh """
                    export IMAGE_TAG=${IMAGE_TAG}
                    export PROFILE_DB_NAME=${PROFILE_DB_NAME}
                    export PROFILE_DB_USER=${PROFILE_DB_USER}
                    export PROJECT_DB_NAME=${PROJECT_DB_NAME}
                    export PROJECT_DB_USER=${PROJECT_DB_USER}
                    export TASK_DB_NAME=${TASK_DB_NAME}
                    export TASK_DB_USER=${TASK_DB_USER}
                    export NOTIFICATION_DB_NAME=${NOTIFICATION_DB_NAME}
                    export NOTIFICATION_DB_USER=${NOTIFICATION_DB_USER}
                    export PROFILE_DB_PASSWORD=${PROFILE_DB_PASSWORD}
                    export PROJECT_DB_PASSWORD=${PROJECT_DB_PASSWORD}
                    export TASK_DB_PASSWORD=${TASK_DB_PASSWORD}
                    export NOTIFICATION_DB_PASSWORD=${NOTIFICATION_DB_PASSWORD}
                    export GITHUB_USERNAME=${GITHUB_USERNAME}
                    export GITHUB_TOKEN=${GITHUB_TOKEN}

                    docker-compose -f docker-compose-dep.yml down -v
                    docker-compose -f docker-compose-dep.yml pull
                    docker-compose -f docker-compose-dep.yml up -d
                """
            }
        }
    }

    post {
        success {
            echo "Build, Docker images, and deployment succeeded for commit ${IMAGE_TAG}"
        }
        failure {
            echo "Build failed for commit ${IMAGE_TAG}"
        }
    }
}
