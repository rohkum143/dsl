#!groovy

import com.test.devops.scm.*

def call() {

    //def archType = deciders.archType
    //String requestJson = readFile("test.json")
    //HashMap configurationFile  = (new HashMap(new groovy.json.JsonSlurperClassic().parseText(requestJson))).asImmutable()
    //println configurationFile
    def a = [ 1,2,3 ]
    println a 
    
    def propJavaOptions = [:]
    def configFile = 'test.yaml'
    def props


    if (! fileExists(configFile)) {
        error "Missing configFile"
    } else {
      props = readYaml file: configFile
        println "${props}"
    }
    
    }
