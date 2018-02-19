
def call(body)
  {
    def config = [:]
      body.resolveStrategy = Closure.DELEGATE_FIRST
      body.delegate = config
      body()
       timestamps {
          try {
               stage("Code Compile") {
                  echo "checkout"
                  //git 'https://github.com/shekharshamra/jenkin.git'
                 checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '584a0859-1779-452d-9d39-4b71bb076333', url: 'https://rkumar164@stash.lblw.ca/scm/sdm/emerald.git']]])

                }
            }
        
          catch (Exception caughtExp) {
             print "[ERROR]: Cargill Brazill CFData pipeline failed, check detailed logs..."
          }
        }            
     
  }
