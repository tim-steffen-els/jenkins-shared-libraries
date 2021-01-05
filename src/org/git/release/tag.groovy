

def info(message) {
   echo "INFO: ${message}"
}

def warning(message) {
   echo "WARNING: ${message}"
}

def tag(version, gitbranch, repo){
    git branch: gitbranch, url: repo
}