pipeline {
  agent any

  stages {
    stage('Build Web Image') {
      steps {
        sh 'docker build -t kairoz-web ./web'
      }
    }

    stage('Build Worker Image') {
      steps {
        sh 'docker build -t kairoz-worker ./worker'
      }
    }

    stage('Load Images to Minikube') {
      steps {
        sh 'minikube image load kairoz-web'
        sh 'minikube image load kairoz-worker'
      }
    }

    stage('Deploy to Kubernetes') {
      steps {
        sh 'kubectl apply -f k8s/'
      }
    }
  }
}
