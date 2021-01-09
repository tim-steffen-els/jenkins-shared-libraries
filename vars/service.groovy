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
    if(utils.checkIfEmpty(tagVersion)){
        throw NullPointerException('tagVersion must always be defined when using processTag(tagVersion, branch)')
    }
    if(tagDoesNotExist(tagVersion)){
        log.info 'Creating tag : ${tag} from branch ${branch}'
        gitHub.checkout(branch)
        if(utilsHelpeer.checkIfNotEmpty(dependency)){
            log.info 'Updating node dependencies.'
            nodeHelper.update(dependency)
            nodeHelper.updateVersion(tagVersion)
        }
        gitHub.tag(tagVersion)
        gitHub.push()
    } else {
        log.info 'Checking out tag: ${tag} . Branch will be ignored.}'
        gitHub.checkout('v' + tagVersion)
    }
}