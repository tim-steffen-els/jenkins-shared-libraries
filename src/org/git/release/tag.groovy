

def info(message) {
   echo "INFO: ${message}"
}

def warning(message) {
   echo "WARNING: ${message}"
}

def error(message) {
	echo "ERROR: ${message}"
	sh 'git clone git@github.com:tim-steffen-els/jenkins-shared-libraries.git'
	echo "ERROR: ${message}"
	git branch: 'main', url: "git@github.com:tim-steffen-els/jenkins-shared-libraries.git"
	echo "ERROR: ${message}"
}

def message(Map config=[:]) {
    echo "branch: ${config.gitbranch}, url: ${config.repo}"
}

