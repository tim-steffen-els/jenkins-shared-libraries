
/**
 * This method is used to process multiple dependency updates. This
 * is given as a map in the following format : def map = ['@campuspack/frontend' : '1.0.0']
 * @param config - A map where key = dependency and value = version
 * @return
 */
def updateDependencies(Map config){
    config.each {
        dependency, version-> updateDependency(dependency, version)
    }
}

private def updateDependency(dependency, version) {
    if (fileExists('package.json')) {
        log.info "Updating dependency ${dependency} to version ${version}"
        updatePackage(dependency, version, "package.json")
    } else {
        //Should this terminate the pipeline?
        log.error "There is no package.json file to update. Skipping operation."
    }

}

private def updatePackage(String dependency, version, fileName) {

    def file = readJSON file: fileName
    file['dependencies'][dependency] = version

    writeJSON file: fileName, json: file, pretty: 4
}

/**
 * This method is used to update the package.json's application version.
 * Example call : updateVersion file: "filename", version: "1.0.0"
 * @param config
 * @return
 */
def updateVersion(Map config) {

    def file = readJSON file: config.fileName
    file['version'] = config.version

    writeJSON file: config.fileName, json: file, pretty: 4
}

