

def info(message) {
   echo "INFO: ${message}"
}

def warning(message) {
   echo "WARNING: ${message}"
}

def error(message) {
	echo "ERROR: ${message}"
	git branch: 'main', url: "https://github.com/tim-steffen-els/jenkins-shared-libraries.git"
}

def message(Map config=[:]) {
    echo "branch: ${config.gitbranch}, url: ${config.repo}"
}

