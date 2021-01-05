

def info(message) {
   echo "INFO: ${message}"
}

def warning(message) {
   echo "WARNING: ${message}"
}

def call(Map config=[:]) {
    git branch: config.gitbranch, url: config.repo
}