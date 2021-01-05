

def info(message) {
   echo "INFO: ${message}"
}

def warning(message) {
   echo "WARNING: ${message}"
}

def error(message) {
	echo "ERROR: ${message}"
	git branch: master, url: "git@github.com:tim-steffen-els/campuspack-backend.git"
}

def message(Map config=[:]) {
    echo "branch: ${config.gitbranch}, url: ${config.repo}"
}

