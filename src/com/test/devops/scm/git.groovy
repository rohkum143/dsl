package com.test.devops.scm

def CheckOut(String GIT_URL) {
      try {
            checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: "${GIT_URL}"]]]
              }
      catch (Exception caughtExp) {
            print " pipeline failed, check detailed log" + caughtExp.getMessage()
            currentBuild.result="FAILURE"
      }
} 
def CodeCompile(String M2) {
      try {
            if (isUnix()) {
                  echo "'${M2}/bin/mvn' -Dmaven.test.failure.ignore clean install -f ${env.Target_DIR}/pom.xml"
             sh "'${M2}/bin/mvn' -Dmaven.test.failure.ignore clean install -f ${env.Target_DIR}/pom.xml"
             } else {
             bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean install/)
             }
      }
       catch (Exception caughtExp) {
        print " codecomplie fail, check detailed log" + caughtExp.getMessage()
         currentBuild.result="FAILURE"
       }
      
} 

