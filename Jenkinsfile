pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
//         stage('test') {
//             agent { label "jdk11"}
//             steps {
//                 echo "test stage"
//                 sh 'java --version'
//             }
//         }
//         stage('deploy') {
//             agent { label "jdk8"}
//             steps {
//                 echo "deploy stage"
//                 sh 'java --version'
//             }
//         }
//     }
//     post {
//         always {
//             mail to: 'peng_zhou@epam.com',
//                  subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
//                  body: "Something is wrong with ${env.BUILD_URL}"
//         }
//     }
}