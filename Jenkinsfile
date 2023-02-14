pipeline {  
    agent any  
    parameters {
        booleanParam(defaultValue: true, description: '', name: 'Build')
    }
    stages {
        /*
        stage ('Checkout') { 
            steps {  
                checkout scmGit(branches: [[name: '**']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/chiradip94/cicd-demo.git']]) 
            }
        }
        */
        stage ('Build') {  
            when { expression { return params.Build }} 
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    sh "mvn site"
                    sh "docker build -t ${user}/heloapp:${currentBuild.number} ."
                    sh "docker tag ${user}/heloapp:${currentBuild.number} ${user}/heloapp:latest"
                }
            }
            post {
                always {
                    archiveArtifacts artifacts: "targer/site/**/*.*"
                }
            }
        }
        stage ('Push to registry') {
            when { expression { return params.Build }} 
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    sh "docker login -u ${user} -p ${pass}"
                    sh "docker push ${user}/heloapp:${currentBuild.number}"
                    sh "docker push ${user}/heloapp:latest"
                }
            }
        }
        stage ('Deploy') {  
            steps {
                sh "docker stop helloapp || true && docker rm helloapp || true"
                withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    sh "docker run -d -p 8085:8085  --name helloapp ${user}/heloapp:latest"
                }
            }
        }
    }
} 