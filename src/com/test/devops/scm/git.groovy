package com.test.devops.scm

def CheckOut(String GIT_URL) {
      try {
            checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: "${GIT_URL}"]]]
            GIT_COMMIT = getGitCommitHash()
            echo " ${GIT_COMMIT} "
            }
      catch (Exception caughtExp) {
            print " pipeline failed, check detailed log" + caughtExp.getMessage()
            currentBuild.result="FAILURE"
      }
} 
def CodeCompile() {
      try {
           sh "'${env.mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean install"
          }
       catch (Exception caughtExp) {
         print " codecomplie fail, check detailed log" + caughtExp.getMessage()
            currentBuild.result="FAILURE"
       }
  }
def Junit() {
      try {
            stage("Junittest") {  
               parallel(
                   "First-Test" : {
                      junit 'in28minutes-core/target/surefire-reports/*.xml'
                      },
                     "2nd-Test" : {
                      echo " This test for parallel stage"
                     },
                 failFast: true
                )
            }
         }
      catch (Exception caughtExp) {
             print " codecomplie fail, check detailed log" + caughtExp.getMessage()
             currentBuild.result="FAILURE"
       }
}     
           

