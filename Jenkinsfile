node {
	
	stage("checkout repository") {
		git branch: 'master',
		credentialsId: '4a4badd9-db94-4d75-8c3c-266370c515ba',
		url: 'git@github.com:vitaliiSmokov/microservices-demo-test.git'
	}

	stage("build") {
		sh "./gradlew clean api-test:assemble"
	}

	stage("run api tests") {
		sh "./gradlew api-test:test"
	}

	allure([
	    includeProperties: false,
	    jdk: '',
	    properties: [],
	    reportBuildPolicy: 'ALWAYS',
	    results: [
	        [path: 'api-test/build/allure-results']
	    ]
	])
}