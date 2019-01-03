#!groovy

import com.test.devops.scm.*

def call(Map Declare) {

    def archType = deciders.archType
    String requestJson = readFile("test.json")
    HashMap configurationFile  = (new HashMap(new groovy.json.JsonSlurperClassic().parseText(requestJson))).asImmutable()
    println configurationFile
    
    }
