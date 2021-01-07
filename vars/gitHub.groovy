final JENKINS_COMMITTER_NAME = 'Ol_Trusty_Riff_Raff'

def returnTag(version, branch){
    if(tagDoesNotExist(version)){

        checkout(branch)
        tag(version)
        push()
    } else {
        checkout(version)
    }
}

def checkout(branch){
    log.info 'Checkout branch from github: ${branch}'
    sh "git checkout ${branch}"
}

def tag(tag){
    log.info 'Creating tag from current branch: ${tag}'
   // sh "git config user.name ${JENKINS_COMMITTER_NAME}"
    //sh "git tag -d ${tag} || true" //This actually allows to auto-issue tags during a build - this will come in handy when we FINALLY have tags on master branch
    sh "git tag -a v${tag} -m \"Tagged automatically by ${JENKINS_COMMITTER_NAME} as part of building process.\""

}

def push(){
    log.info 'Pushing tag back to GitHub.'
    //sh "git push origin :refs/tags/v${tag}" //see comment above
    sh "git push --tags" //Git permissions for this user need to be elevated OR use different user
}

Boolean tagDoesNotExist(tag){
    return !tagExist(tag)
}

Boolean tagExist(tag){
    tags = sh label: '', returnStdout: true, script: 'git tag -l'
    tags.split ('\n').collect{it as String}
    tagExist = false
    if(tags.contains(tag)){
        log.info "Tag already exist."
        tagExist = true
    } else {
        log.info 'Tag does not exist.'
    }
    return tagExist
}
