

def info(message) {
   echo "INFO: ${message}"
}

def warning(message) {
   echo "WARNING: ${message}"
}

def error(message) {
	echo "ERROR: ${message}"
}

def message(Map config=[:]) {
    echo "branch: ${config.gitbranch}, url: ${config.repo}"
}

