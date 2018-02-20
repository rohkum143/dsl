package.com.test
   def CheckOut() {
                  try {
                    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/shekharshamra/jenkin.git']]])
                      }
                      catch (Exception caughtExp) {
                      print "[ERROR]: Cargill Brazill CFData pipeline failed, check detailed logs..."
                                                   }
     
                     }
