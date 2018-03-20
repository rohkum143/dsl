#!groovy

import com.test.devops.scm.*
  
def call(body)
  {
    def config = [:]
      body.resolveStrategy = Closure.DELEGATE_FIRST
      body.delegate = config
      body()
      timestamps {
          try {
            wrap([$class: 'AnsiColorBuildWrapper']) {
              currentBuild.result = "SUCCESS"
               stage("Code Checkout") {
                 dir ("${env.Target_DIR}") {
                     deleteDir()
                     echo "checkout"
                     def userInput = ""
                     try {
                     timeout(time: 10, unit: 'SECONDS') {
                   userInput = input(id: 'User Inputs Required', message: 'User Inputs Required', parameters: [[$class: 'BooleanParameterDefinition', defaultValue: false, description: 'Do you want to raise pull request?', name: 'Raise_PR']])
                  }
                 }
            catch(Exception caughtError) {
              println "Handles the use case of timeout...."
            }
                     def g = new git()
                      g.CheckOut("${config.GIT_URL}")
                      // echo "${config.MY_COMMAND}"
                      }
                }
                stage("Code Compile") {
                  dir ("${env.Target_DIR}") {
                  echo "Code Compile"
                  def g = new git ()
                  g.CodeCompile()
                 }
                }
             stage ("Junit Test") {
                 dir ("${env.Target_DIR}") {
                 echo "Junit Testing "
                 def g = new git ()
                 g.Junit()
                 }
             }
              
          }
          }     
          catch (Exception caughtExp) {
             print "pipeline failed, check detailed logs..."
            currentBuild.result="FAILURE"
          }
        }            
     
  }
  
