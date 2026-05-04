// Task 4: Spring Boot Maven build + failure email (<Project>-<Your name> pattern).
// Prereqs: Email Extension Plugin; Manage Jenkins → Configure System → Extended E-mail Notification (SMTP tested).
// Optional job env vars: TASK4_NOTIFY_EMAIL (inbox); TASK4_MAIL_SUBJECT_PREFIX override.

pipeline {
    agent { label 'spring' }

    // Default subject tag matches DevOpsMidterm26; set job env TASK4_MAIL_SUBJECT_PREFIX for other repos (e.g. MIdtermDevOps-Hokleng).

    options {
        timestamps()
    }

    environment {
        TASK4_MAIL_SUBJECT_PREFIX = 'DevOpsMidterm26-Hokleng'
        DB_HOST = 'localhost'
        DB_PORT = '15432'
        DB_NAME = 'springtest'
        DB_USER = 'spring'
        DB_PASSWORD = 'spring'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Spring Boot') {
            steps {
                script {
                    if (fileExists('task3/pom.xml')) {
                        dir('task3') {
                            sh 'mvn -B -ntp clean package -DskipTests'
                        }
                    } else if (fileExists('pom.xml')) {
                        sh 'mvn -B -ntp clean package -DskipTests'
                    } else {
                        error('No pom.xml at repo root or task3/')
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true, fingerprint: true
        }

        success {
            emailext(
                subject: "${env.TASK4_MAIL_SUBJECT_PREFIX} — SUCCESS #${env.BUILD_NUMBER}",
                body: """Task 4: build SUCCEEDED.

Job: ${env.JOB_NAME}
Tag: ${env.TASK4_MAIL_SUBJECT_PREFIX}
Build number: ${env.BUILD_NUMBER}
Build URL: ${env.BUILD_URL}
Console: ${env.BUILD_URL}console

Check build Artifacts for the packaged JAR.
""",
                to: env.TASK4_NOTIFY_EMAIL ?: '$DEFAULT_RECIPIENTS',
            )
        }

        failure {
            emailext(
                subject: "${env.TASK4_MAIL_SUBJECT_PREFIX} — FAILED #${env.BUILD_NUMBER}",
                body: """Task 4: build FAILED.

Job: ${env.JOB_NAME}
Tag: ${env.TASK4_MAIL_SUBJECT_PREFIX}
Build number: ${env.BUILD_NUMBER}
Build URL: ${env.BUILD_URL}
Console: ${env.BUILD_URL}console

\${BUILD_LOG, maxLines=400}
""",
                to: env.TASK4_NOTIFY_EMAIL ?: '$DEFAULT_RECIPIENTS',
                attachLog: true
            )
        }
    }
}
