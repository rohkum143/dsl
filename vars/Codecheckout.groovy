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
               stage("Code Checkout") {
                 dir ("${env.Target_DIR}") {
                     echo "checkout"
                     def g = new git()
                      g.CheckOut("${config.GIT_URL}")
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
                
          catch (Exception caughtExp) {
             print "pipeline failed, check detailed logs..."
            currentBuild.result="FAILURE"
          }
        }            
     
  }
