//def call(Map config) {
//    sh label: 'debugging', script: 'ls -lrt'
//    if(fileExists('package.json')){
//        log.info "Updating dependency ${config.dependency} to version ${config.version}"
//        updatePackage(config.dependency, config.version, "package.json")
//    } else {
//        log.error "There is no package.json file to update. Skipping operation."
//    }
//
//
//}
//def update(Map config) {
//    sh label: 'debugging', script: 'ls -lrt'
//    if (fileExists('package.json')) {
//        log.info "Updating dependency ${config.dependency} to version ${config.version}"
//        updatePackage(config.dependency, config.version, "package.json")
//    } else {
//        log.error "There is no package.json file to update. Skipping operation."
//    }
//
//}
//def updatePackage(String dependency, version, fileName){
//
//    def file = readJSON file: fileName
//    file['dependencies'][dependency] = version
//
//    writeJSON file: fileName, json: file, pretty: 4
//
//}
//
//def updateVersion(Map config){
//
//    def file = readJSON file: config.fileName
//    file['version'] = config.version
//
//    writeJSON file: config.fileName, json: file, pretty: 4
//
//}


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