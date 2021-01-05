

def info(message) {
   echo "INFO: ${message}"
}

def warning(message) {
   echo "WARNING: ${message}"
}

def message(Map config=[:]) {
    echo "branch: ${config.gitbranch}, url: ${config.repo}"
}

def something() {
	echo "why are you doing this"
}