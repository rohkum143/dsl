#!groovy

import com.test.devops.scm.*

def call() {
    
    def propJavaOptions = [:]
    def configFile = 'test.yaml'
    println configFile
    def props


    if (! fileExists(configFile)) {
      error "Missing configFile"
    } else {
     props = readYaml file: configFile
       println props
    }
    if (props?.services) {
      echo " required configuration"
  }
  }
