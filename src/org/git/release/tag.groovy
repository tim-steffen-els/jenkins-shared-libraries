

def call() {
    node {
        //git url: "https://github.com/werne2j/sample-nodejs"

        stage("Install") {
           // sh "npm install"
           echo "hello world"
        }

        stage("Test") {
            echo "hello world"
        }

        stage("Deploy") {
            echo "hello world"
        }

    }
}