import .com.test.devops.scm
def CheckOut() {
      try {
            checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/shekharshamra/jenkin.git']]])
          }
      catch (Exception caughtExp) {
            print " pipeline failed, check detailed log"
      }
}

