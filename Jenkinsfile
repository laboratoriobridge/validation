pipeline {
    agent any

    tools {
        maven "maven-3"
    }

    stages {
        stage("Checkout") {
            steps {
                checkout scm
            }
        }
        stage("Build and Test") {
            steps {
                sh "mvn clean install"
            }
        }
        stage("Sonar Master Scan") {
            when { branch "master" }
            steps {
                 withCredentials([
                    string(credentialsId: 'SONARQUBE_TOKEN', variable: 'SONARQUBE_TOKEN'),
                ]) {
                    sh "mvn sonar:sonar \
                        -Dsonar.host.url=${SONARQUBE_HOST} \
                        -Dsonar.login=${SONARQUBE_TOKEN}"
                }
            }
        }
        stage("Record Master Coverage") {
            when { branch "master" }
            steps {
                script { currentBuild.result = 'SUCCESS' }
                step([$class: 'MasterCoverageAction', scmVars: [GIT_URL: env.GIT_URL]])
            }
        }
        stage("PR Sonar Scan and Coverage") {
            when {
                allOf {
                    not { branch "master" }
                    expression { env.CHANGE_ID != null }
                }
            }
            steps {
                withCredentials([
                    string(credentialsId: 'SONARQUBE_TOKEN', variable: 'SONARQUBE_TOKEN'),
                    string(credentialsId: 'GITHUB_SECRET', variable: 'GITHUB_SECRET'),
                ]) {
                    sh "mvn sonar:sonar \
                        -Dsonar.host.url=${SONARQUBE_HOST} \
                        -Dsonar.login=${SONARQUBE_TOKEN} \
                        -Dsonar.analysis.mode=preview \
                        -Dsonar.github.repository=laboratoriobridge/pec \
                        -Dsonar.github.pullRequest=${env.CHANGE_ID} \
                        -Dsonar.github.oauth=${GITHUB_SECRET}"

                    script { currentBuild.result = 'SUCCESS' }
                    step([$class: 'CompareCoverageAction', scmVars: [GIT_URL: env.GIT_URL]])
                }
            }
        }
    }
    post {
        always {
            junit "target/surefire-reports/*.xml"
        }
    }
}