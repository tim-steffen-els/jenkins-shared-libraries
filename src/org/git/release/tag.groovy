

def info(message) {
   echo "INFO: ${message}"
}

def warning(message) {
   echo "WARNING: ${message}"
}

def call(Map config=[:]) {
    echo "branch: ${config.gitbranch}, url: ${config.repo}"
}