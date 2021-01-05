def call(Map config) {
    node {
        echo "url ${config.url}"
        git branch: config.branch url: config.url 
        sh 'git tag v${config.version}'
        sh 'git push origin v${config.version}'   
    }
}

//def call(repo, branch, version) {
//  //pipeline {
//   // agent any
//    stages {
//        stage("Cleaan Workspace"){
//            steps {
//                cleanWs()
//            }
//        }
//        stage("Set Discription"){
//          steps {
//            buildDescription 'V: $version, B: $branch, R: $repo'
//          }
//        }
//        stage('Git checkout') {
//            steps {
//                //We should not be checking out tags since this process is creating them.
//                git branch: branch, url: repo
//            }
//        }
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
//        stage('Git create tag') {
//            steps {
//                //If this step fails, the tag already exist. 
//                sh 'git tag -l'
//                sh 'git tag v${VERSION}'
//            }
//        }
//        stage('Create tag in Github') {
//            steps {
//                //If this step fails, it is likely something to do with credentials 
//                sh 'git push origin v${VERSION}'
//            }
//        }
//    }
////}
//}
