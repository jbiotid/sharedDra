package org.foo

// class Utilities implements Serializable {
//   def steps
//   Utilities(steps) {this.steps = steps}
//   def mvn(args) {
//     println "Bar Hello world!"
//     steps.sh "${steps.tool 'Maven'}/bin/mvn -o ${args}"
//   }

//   static builder(script) {

//       String filename = "/tmp/nastytest.txt"  
// // creates a new file test.txt  
//       boolean success = new File(filename).createNewFile() 
//         println "static Bar Hello world!"
//     }
// }

def checkOutFrom(repo) {
  //git url: "git@github.com:jbiotid/${repo}"
   withCredentials([string(credentialsId: 'BM_API_KEY', variable: 'bmApiKey')]) {
      env.IBM_CLOUD_DEVOPS_API_KEY = "${bmApiKey}"
      env.IBM_CLOUD_DEVOPS_ORG = 'UCCloud'
      env.IBM_CLOUD_DEVOPS_APP_NAME = "${componentName}"
      env.IBM_CLOUD_DEVOPS_TOOLCHAIN_ID = 'f63cd10b-2639-46a2-829c-89bc7188eb30'

      publishBuildRecord gitBranch: "${gitBranch}", gitCommit: "${gitResult.GIT_COMMIT}", gitRepo: "${gitUrl}", result:"SUCCESS"

      publishTestResult type:'unittest', fileLocation: 'test-results/unit/results.xml'
      publishTestResult type:'fvt', fileLocation: 'test-results/func/results.xml', environment: 'DEV'

      evaluateGate policy: 'Service Tests', forceDecision: 'false'
    }
}

return this