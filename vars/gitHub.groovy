def tagAndPush(version){
    tag(version)
    push(version)
}

def tag(version){

    log.info "Creating Tag ${version}"
    sh "git tag v${config.version}"
}

def push(version){

    log.info "Pushing Tag to git"
    sh "git push origin v${version}"
}

Boolean tagExist(tag){
    tags = sh label: '', returnStdout: true, script: 'git tag -l'
    tags.split ('\n').collect{it as String}
    return tags.contains(tag)
}