#!groovy
def call(body)
  {
#    def config = [:]
#    body.resolveStrategy = Closure.DELEGATE_FIRST
#    body.delegate = config
#    body()
    timestamps {
     try {
        stage("Code Compile") { 
        print "checkout"
        git 'https://github.com/shekharshamra/jenkin.git'
        }
       }
       catch (Exception caughtExp) { 
       print "\u001B[41m[ERROR]: check detailed logs..." 
     }
 } 
}
