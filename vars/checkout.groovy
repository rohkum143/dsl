#!groovy

import com.test.devops.scm.*
  
def call(body)
  {
    def config = [:]
      body.resolveStrategy = Closure.DELEGATE_FIRST
      body.delegate = config
      body()
       // timestamps {
          try {
               stage("Code Compile") {
                  echo "checkout"
                  //git 'https://github.com/shekharshamra/jenkin.git'
                  // checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/shekharshamra/jenkin.git']]])
                def g = new git()
                 g.CheckOut("${config.GIT_URL}")
               }
            }
        
          catch (Exception caughtExp) {
             print "pipeline failed, check detailed logs..."
            currentBuild.result="FAILURE"
          }
       // }            
     
  }
