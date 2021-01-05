

def info(message) {
   echo "INFO: ${message}"
}

def warning(message) {
   echo "WARNING: ${message}"
}

def tagRepo(version, gitbranch, repo) {
    git branch: gitbranch, url: repo
}