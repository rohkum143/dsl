#!groovy

import com.test.devops.scm.*
  
def call(body)
  {
    def config = [:]
      body.resolveStrategy = Closure.DELEGATE_FIRST
      body.delegate = config
      body()
      def mvnHome
      mvnHome = tool 'M2_HOME'
       timestamps {
          try {
               stage("Code Checkout") {
                  echo "checkout"
                  def g = new git()
                   g.CheckOut("${config.GIT_URL}")
               }
                stage("Code Compile") {
                  echo "Code Compile"
                  def g = new git ()
                  g.CodeCompile
                 }
              }
        
          catch (Exception caughtExp) {
             print "pipeline failed, check detailed logs..."
            currentBuild.result="FAILURE"
          }
        }            
     
  }
