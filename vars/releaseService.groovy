def processTag(tagVersion, branch) {
    log.info "Processing tag."
    processTag(tagVersion, branch, null)
}

/**
 * This method is used to create a tag from a given branch. If the
 * tag already exist, it will return the existing tag.
 *
 * @param tagVersion - git tag version. do not include the leading 'v'
 * @param branch - git branch that the tag will be created from
 * @return
 * @throws NullPointerException
 */
def processTag(tagVersion, branch, dependency) throws NullPointerException {
    if(utilsHelper.checkIfEmpty(tagVersion)) {
        throw NullPointerException("tagVersion must always be defined when using processTag(tagVersion, branch, dependency)")
    }
    if(github.tagDoesNotExist(tagVersion)) {
        log.info "Creating tag : ${tagVersion} from branch ${branch} with dependencies : ${dependency}"
        github.checkout(branch)
        if(utilsHelper.checkIfNotEmpty(dependency)) {
            log.info "Updating node dependencies."
            nodeHelper.update(dependency)
            nodeHelper.updateVersion(tagVersion)
        }
        github.tag(tagVersion)
        github.push()
    } else {
        log.info "Checking out tag: ${tag} . Branch will be ignored.}"
        github.checkout('v' + tagVersion)
    }
}
