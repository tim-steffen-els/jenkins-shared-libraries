import hudson.FilePath

def info(message) {
    echo "INFO: ${message}"
}

def warning(message) {
    echo "WARNING: ${message}"
}

def checkout(message){
	echo "CHECKING OUT: ${message}"
	sh label: '', script: 'git clone https://github.com/tim-steffen-els/jenkins-shared-libraries.git'
}

def error(message) {
    pipeline {
      agent any
      stages {
        stage('Even Stage') {
          steps {
            echo "The build number is even"
          }
        }
      }
    }
  } 

def checkouttest(message) {
	echo "ERROR: ${message}"
	sh label: '', script: 'git clone https://github.com/tim-steffen-els/jenkins-shared-libraries.git'
	echo "ERROR: ${message}"
	git branch: 'main', url: "git@github.com:tim-steffen-els/jenkins-shared-libraries.git"
	echo "ERROR: ${message}"
}

