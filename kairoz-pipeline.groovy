<?xml version='1.1' encoding='UTF-8'?>
<flow-definition plugin="workflow-job">
  <description>Jenkins CI/CD for Kairoz</description>
  <keepDependencies>false</keepDependencies>
  <properties/>
  <definition class="org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition" plugin="workflow-cps">
    <script>
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
              stage('Load to Minikube') {
                  steps {
                      sh 'minikube image load kairoz-web'
                      sh 'minikube image load kairoz-worker'
                  }
              }
              stage('Deploy to Kubernetes') {
                  steps {
                      sh 'kubectl apply -f ./k8s'
                  }
              }
          }
      }
    </script>
    <sandbox>true</sandbox>
  </definition>
  <triggers/>
  <disabled>false</disabled>
</flow-definition>
