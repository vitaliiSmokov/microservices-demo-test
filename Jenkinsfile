node('jenkins_slave') {

	stage("checkout repository") {
		git branch: 'master',
		credentialsId: '4a4badd9-db94-4d75-8c3c-266370c515ba',
		url: 'git@github.com:vitaliiSmokov/microservices-demo-test.git'
	}

	stage("build") {
	    sh "echo ${ssh_key} > /home/ubuntu/workspace/.../api-test/src/test/resources/ssh_key.pem"
		sh "./gradlew clean api-test:assemble"
	}

	try {
	    notifySlack()
	    stage("run api tests") {
	    sh "./gradlew api-test:test"
		}

    } catch (e) {

        currentBuild.result = 'FAILURE'
        throw e

    } finally {

        stage('reports') {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'api-test/build/allure-results']]
                ])
                }
                notifySlack(currentBuild.result)
            }
}

def notifySlack(String buildStatus = 'STARTED') {

    // Build status of null means success.
    buildStatus = buildStatus ?: 'SUCCESS'
    def color
    if (buildStatus == 'STARTED') {
        color = '#D4DADF'
    } else if (buildStatus == 'SUCCESS') {
        color = '#BDFFC3'
    } else if (buildStatus == 'UNSTABLE') {
        color = '#FFFE89'
    } else {
        color = '#FF9FA1'
    }
    def msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"
    slackSend(color: color, message: msg, channel: '#jenkins-channel')
}