pipeline {  
    agent any  
    parameters {
        booleanParam(defaultValue: true, description: '', name: 'Build')
    }
    stages {
        /*stage ('Checkout') { 
            steps {  
                checkout scmGit(branches: [[name: '**']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/chiradip94/cicd-demo.git']]) 
            }
        }*/
        stage ('Build') {  
            when { expression { return params.Build }} 
            steps {
                sh "docker build -t heloapp:${currentBuild.number} ."
                sh "docker tag heloapp:${currentBuild.number} heloapp:latest"
            }
        }
    stage ('Deploy') {  
            steps {
                sh "docker stop helloapp || true && docker rm helloapp || true"
                sh "docker run -d -p 8085:8085  --name helloapp heloapp:latest"  
            }
        }
    }  
} 