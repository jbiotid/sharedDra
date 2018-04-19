package org.foo
class Utilities implements Serializable {
  def steps
  Utilities(steps) {this.steps = steps}
  def mvn(args) {
    println "Bar Hello world!"
    steps.sh "${steps.tool 'Maven'}/bin/mvn -o ${args}"
  }

  static builder(script) {

      String filename = "/tmp/nastytest.txt"  
// creates a new file test.txt  
      boolean success = new File(filename).createNewFile() 
        println "static Bar Hello world!"
    }
}