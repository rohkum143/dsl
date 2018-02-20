package com.test.devops.scm

def CheckOut() {
      try {
            //checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/shekharshamra/jenkin.git']]]
           git 'https://github.com/shekharshamra/jenkin.git'
            //checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/rohkum143/dsl.git']]])
           }
      catch (Exception caughtExp) {
            print " pipeline failed, check detailed log" + caughtExp.getMessage()
            currentBuild.result="FAILURE"
      }
}

