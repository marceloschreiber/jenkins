def buildStages

node('master') {
  stage('Initialise') {
    // Set up List<Map<String,Closure>> describing the builds
    buildStages = prepareBuildStages()
    println("Checkout")
  }
  stage('Build') {
      println("Build")
  }

  for (builds in buildStages) {
    parallel(builds)
  }

  stage('Print extensions lists') {
    println('Extensions list')
  }
}

// Create List of build stages to suit
def prepareBuildStages() {
  def buildStagesList = []

  for (i=1; i<=5; i++) {
    def buildParallelMap = [:]
    for (name in [ 'Extension 1', 'Extension 2', 'Extension 3', 'Extension 4', 'Extension 5', 'Extension 6', 'Extension 7', 'Extension 8' ] ) {
      def n
      if(i == 1) n = "Fortify ${name}"
      else if(i == 2) n = "Checkmarx ${name}"
      else if(i == 3) n = "Sonar ${name}"
      else if(i == 4) n = "Zip validator ${name}"
      else if(i == 5) n = "Upload to nexus ${name}"
      
      buildParallelMap.put(n, prepareOneBuildStage(n, i))
      buildParallelMap.put("failFast", true)
    }
    buildStagesList.add(buildParallelMap)
  }

  return buildStagesList
}

def prepareOneBuildStage(String name, int i) {
  return {
    stage("Build stage:${name}") {
        println("Building ${name}")
        sh(script:'sleep $(shuf -i 0-4 -n 1)', returnStatus:false)
    }
  }
}