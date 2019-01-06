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
    dockerprperties = props.docker
    def javaOptionString = ''
    javaOptionString = '-Ddocker.projectVersion='+env.BUILD_NUMBER+' '
    for (def entry: dockerprperties){
    javaOptionString = javaOptionString + "${entry} "
    println javaOptionString
  }
    
  }
  }
