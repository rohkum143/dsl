import com.test.devops.scm.*
  
def call(body)
  {
    def config = [:]
      body.resolveStrategy = Closure.DELEGATE_FIRST
      body.delegate = config
      body()
      def mvnHome
      mvnHome = tool 'M2_HOME'
       // timestamps {
          try {
               stage("Code Compile") {
                  echo "Code Compile"
                  if (isUnix()) {
                   sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean install"
                    } else {
                 bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean install/)
                       }
               }
            }
        
          catch (Exception caughtExp) {
             print "pipeline failed, check detailed logs..."
            currentBuild.result="FAILURE"
          }
       // }            
     
  }
