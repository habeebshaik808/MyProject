pipeline {
    agent any

    environment {
        MAVEN_HOME = '/opt/maven' // Adjust path as per your setup
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-repo.git'
            }
        }

        stage('Code Quality Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "${MAVEN_HOME}/bin/mvn clean verify sonar:sonar"
                }
            }
        }

        stage('Build') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn clean package"
            }
        }

        stage('Publish to Nexus') {
            steps {
                nexusArtifactUploader(
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    nexusUrl: 'your-nexus-url',
                    repository: 'maven-releases',
                    credentialsId: 'nexus-credentials',
                    groupId: 'com.yourcompany',
                    artifactId: 'your-app',
                    version: '1.0.0',
                    packaging: 'war',
                    file: 'target/your-app.war'
                )
            }
        }

        stage('Upload to S3') {
            steps {
                sh 'aws s3 cp target/your-app.war s3://your-bucket-name/ --acl public-read'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                sshagent(['tomcat-credentials']) {
                    sh 'scp target/your-app.war user@tomcat-server:/opt/tomcat/webapps/'
                }
            }
        }
    }

    post {
        success {
            echo "Deployment successful!"
        }
        failure {
            echo "Pipeline failed!"
        }
    }
}
