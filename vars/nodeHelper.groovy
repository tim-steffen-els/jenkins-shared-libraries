
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode

/**
 * This method is used to process multiple dependency updates. This
 * is given as a map in the following format : def map = ['@campuspack/frontend' : '1.0.0','@campuspack/backend' : '1.0.0']
 * @param config - A map where key = dependency and value = version
 * @return
 */
def updateDependencies(Map config) {
    log.info "config : ${config.toMapString()}"
    config.each {
        dependency, version-> updateDependency(dependency, version)
    }
}

private def updateDependency(dependency, version) {
    if (fileExists("package.json")) {
        log.info "Updating dependency ${dependency} to version ${version}"
        updatePackage(dependency, version)
    } else {
        //Should this terminate the pipeline?
        log.error "There is no package.json file to update. Skipping operation."
    }

}

private def updatePackage(String dependency, version) {
    log.info "Updateing the ------------ package"
    log.info "Dep : ${dependency}, Ver: ${version}"
    sh 'ls -lrt'
    File file = new File("/var/jenkins_home/workspace/Frontend-Build/package.json")
    ObjectMapper mapper = new ObjectMapper()
    Object value = mapper.readTree(file)

    log.info "Hello1 ${value}"  //This is the whole json
    log.info "Hello2 ${value.get("name")}"
//    log.info "Hello3 ${value.dependency}"
    log.info "Hello3 ${value.get('dependency').asText()}"


    //def file2 = readJSON file: "package.json"
    //file2['dependencies'][dependency] = version

    writeFile(value)
}

/**
 * This method is used to update the package.json's application version.
 * Example call : updateVersion("1.0.0")
 * @param version - ex 1.3.2 - major.minor.patch
 * @return
 */
def updateVersion(version) {
    def file = readJSON file: "package.json"
    file['version'] = version

    writeFile(file)
}

private writeFile(file) {
    log.info "Writing file to disk with updates."
    writeJSON file: "package.json", json: file, pretty: 4
}
