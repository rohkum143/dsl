#!groovy
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
                  git 'https://github.com/shekharshamra/jenkin.git'
                }
            }
        
          catch (Exception caughtExp) {
             print "[ERROR]: Cargill Brazill CFData pipeline failed, check detailed logs..."
          }
        }            
     
  }
