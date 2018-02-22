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
                  echo "checkout"
                  def g = new git()
                 g.CheckOut("${config.GIT_URL},${config.TARGET_DIR}")
               }
                stage("Code Compile") {
                  echo "Code Compile"
                  def g = new git ()
                  g.CodeCompile("${config.mvnHome}")
                 }
              }
        
          catch (Exception caughtExp) {
             print "pipeline failed, check detailed logs..."
            currentBuild.result="FAILURE"
          }
        }            
     
  }
