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
def processTag(tagVersion, branch, dependency) throws NullPointerException {
    if(utilsHelper.checkIfEmpty(tagVersion)){
        throw NullPointerException('tagVersion must always be defined when using processTag(tagVersion, branch)')
    }
    if(gitHub.tagDoesNotExist(tagVersion)){
        log.info "Creating tag : ${tagVersion} from branch ${branch} with dependencies : ${dependency}"
        gitHub.checkout(branch)
        if(utilsHelper.checkIfNotEmpty(dependency)){
            wrapInStage("Update node depenedcies. ", dependency, tagVersion)
           // log.info 'Updating node dependencies.'
           // nodeHelper.update(dependency)
           // nodeHelper.updateVersion(tagVersion)
        }
        gitHub.tag(tagVersion)
        gitHub.push()
    } else {
        log.info 'Checking out tag: ${tag} . Branch will be ignored.}'
        gitHub.checkout('v' + tagVersion)
    }
}

def wrapInStage(name, dependency,tagVersion){
    stage(name){
        steps {
            script {
                log.info 'Updating node dependencies.'
                nodeHelper.update(dependency)
                nodeHelper.updateVersion(tagVersion)
            }
        }
    }
}