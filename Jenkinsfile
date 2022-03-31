pipeline {
    agent any
    stages {
        stage('test') {
            steps {
                sh 'mvn test -DtestngXmlFile=src/main/resources/mobile_android.xml'
            }
        }
        stage('post test') {
            steps {
                echo "post test"
                sh 'java -version'
                sh 'mvn -version'
            }
        }
//         stage('deploy') {
//             agent { label "jdk8"}
//             steps {
//                 echo "deploy stage"
//                 sh 'java --version'
//             }
//         }
    }
//     post {
//         always {
//             mail to: 'peng_zhou@epam.com',
//                  subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
//                  body: "Something is wrong with ${env.BUILD_URL}"
//         }
//     }
}