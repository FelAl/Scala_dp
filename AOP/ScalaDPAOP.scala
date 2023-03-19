D = Data
R = Reader
import System.{currentTimeMillis => cTM}
import scala.Predef.{println => pr}

trait DR {
  def readD(): String
  def readDWrongly(): String
}

class DRImpl extends DR {
  override def readD(): String = File.read("/home/src/path.txt")

  override def readDWrongly(): String = {
    System.sleep(10000)
    readD()
  }
}
// With AOP!
trait LoggingDR extends DR {
  abstract override def readD(): String = {
    val start = cTM()
    val result = super.readD()
    val time = cTM() - start
    pr("execution time readD is:" + time)
    result
  }
  // abstract notifies compiler about stackable modifications
  abstract override def readDWrongly(): String = { 
    val start = cTM()
    val result = super.readDWrongly()
    val time = cTM() - start
    pr("execution time readDWrongly is:" + time)
    result
  }
}
// AOP
val dR = new DRImpl with LoggingDR
dR.readD()
dr.readDWrongly()
AOP can be used for logging, retry logic, rollabck.
Linearization rule.


// Without AOP
val dR = new DRImpl
dR.readD()
dr.readDWrongly()


// Without AOP
class DRImpl extends DR {
  override def readD(): String = {
    val start = cTM()
    val result = File.read("...")
    val time = cTM() - start
    pr("execution time readD is:" + time)
    result
  }

  override def readDWrongly(): String = {
    val start = cTM()
    val result = readD()
    val time = cTM() - start
    pr("execution time readDWrongly is:" + time)
    result
  }
}

