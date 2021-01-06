def call(Map config) {
    if(fileExists('package.json')){
        log.info "Updating dependency ${config.dependency} to version ${config.version}"
        updatePackage(config.dependency, config.version)
    } else {
        log.ERROR "There is no package.json file to update. Skipping operation."
    }


}

def updatePackage(String dependency, version){

    def file = readJSON file: fileName
    file['dependencies'][dependency] = version

    writeJSON file: fileName, json: file, pretty: 4

}

def updateVersion(version){

    def file = readJSON file: fileName
    file['version'] = version

    writeJSON file: fileName, json: file, pretty: 4

}


//def updatePackage(String versionUpdates, fileName, version){
//    script{
//        def updated = false
//        def file = readJSON file: fileName
//        file['version'] = version
//        //Note 1 is returned when versionUdates is an empty string
//        if(versionUpdates.size() > 3) {
//            def listUpdates = versionUpdates.split(",")
//            listUpdates.each { key ->
//                def propKey = key.split(":")[0].trim()
//                def value   = key.split(":")[1].trim()
//                file['dependencies'][propKey] = value
//            }
//            writeJSON file: fileName, json: file, pretty: 4
//            sh 'git add package.json'
//            sh "git commit -m 'updated depenedies'"
//        }
//    }
//}



//      //  stage("Update dependencies") {
//      //      when {
//      //          expression {
//      //              //This is used to differenciate between a frontend and backend app.
//      //              return fileExists('package.json')
//      //          }
//      //      }
//      //      steps{
//      //          //This needs the pipeline utility plugin
//      //          updatePackage(VersionUpdates, 'package.json', VERSION)
//      //      }
//      //  }