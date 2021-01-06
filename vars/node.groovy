def call(){
    log.info "WHY WHY WHY"
}

def updateDependency(Map config) {
    if (fileExists('package.json')) {
        log.info "Updating dependency ${config.dependency} to version ${config.version}"
        updatePackage(config.dependency, config.version, "package.json")
    } else {
        log.error "There is no package.json file to update. Skipping operation."
    }

}
private def updatePackage(String dependency, version, fileName) {

    def file = readJSON file: fileName
    file['dependencies'][dependency] = version

    writeJSON file: fileName, json: file, pretty: 4

}

def updateVersion(Map config) {

    def file = readJSON file: config.fileName
    file['version'] = config.version

    writeJSON file: config.fileName, json: file, pretty: 4

}

