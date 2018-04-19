package org.TestUpload

def checkOutFrom(repo, bmApiKey, componentName, gitUrl) {
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