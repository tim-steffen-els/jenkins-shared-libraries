final JENKINS_COMMITTER_NAME = 'Ol_Trusty_Riff_Raff'

/**
 * This method provides abstraction for the git tagging operation.
 *  - Given a branch and tag, this will create the tag if it does not already exist.
 *  - Given a tag, it will checkout that tag if it exist.
 *  - Given a branch, it will checkout thata branch if it exist.
 *
 * @param tagVersion
 * @param branch
 * @return
 */
//magic
def processLocators(tagVersion, branch){
    if(utilsHelper.checkIfNotEmpty(tagVersion)){

        log.info 'Processing Tag: ${tag} with branch: ${branch}.'
        processTag(tagVersion, branch)
    } else {
        log.info 'Checking out branch : ${branch}'
        checkout(branch)
    }
}

/**
 * This method is used to create a tag from a given branch. If the
 * tag already exist, it will return the existing tag.
 *
 * @param tagVersion
 * @param branch
 * @return
 * @throws NullPointerException
 */
//returnTag
def processTag(tagVersion, branch) throws NullPointerException {
    if(utils.checkIfEmpty(tagVersion)){
        throw NullPointerException('tagVersion must always be defined when using processTag(tagVersion, branch)')
    }
    if(tagDoesNotExist(tagVersion)){
        log.info 'Creating tag : ${tag} from branch ${branch}'
        checkout(branch)
        tag(tagVersion)
        push()
    } else {
        log.info 'Checking out tag: ${tag} . Branch will be ignored.}'
        checkout('v' + tagVersion)
    }
}

/**
 * This method wraps the git command for checking out a branch and provides
 * consistent logging for this operation.
 * @param branch - The Github branch should already exist
 * @return
 */
def checkout(branch){
    log.info 'Checkout branch from github: ${branch}'
    sh "git checkout ${branch}"
}

/**
 * This is a wrapper around the Github tagging operation. This method provides consistent
 * messaging and logging for tag creation.
 * @param tag - Tag should not exist already.
 * @return
 */
def tag(tag){
    log.info 'Creating tag from current branch: ${tag}'
   // sh "git config user.name ${JENKINS_COMMITTER_NAME}"
    //sh "git tag -d ${tag} || true" //This actually allows to auto-issue tags during a build - this will come in handy when we FINALLY have tags on master branch
    sh "git tag -a v${tag} -m \"Tagged automatically by Ol_Trusty_Riff_Raff as part of building process.\""

}

/**
 * This method wraps the logic for pushing a tag. It provides consistent logging.
 * @return
 */
def push(){
    log.info 'Pushing tag back to GitHub.'
    //sh "git push origin :refs/tags/v${tag}" //see comment above
    sh "git push --tags" //Git permissions for this user need to be elevated OR use different user
}


Boolean tagDoesNotExist(tag){
    return !tagExist(tag)
}

Boolean tagExist(tag){
    String allTagsString = sh label: '', returnStdout: true, script: 'git tag -l'
    List tagList = allTagsString.split ('\n').collect{it as String}
    Boolean tagExist = false

    if(tagList.contains('v' + tag)){
        log.info "Tag already exist."
        tagExist = true
    } else {
        log.info 'Tag does not exist.'
    }
    return tagExist
}
