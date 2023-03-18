// Lazy initialization pattern
C = Circle
U = Utils
b = basic
import System.out.{println => pr}

object CU {
  val bPi = 3.14

  lazy val precisePi: Double = {
    pr("Read properties for the precise Pi")
    val props = new Properties()
    props.load(getClass.getResourceAsStream("pi.properties"))
    props.getProperty("pi.high").toDouble
  }

  def area(r: Double, isPrecise: Boolean = false): Double = {
    val pi = if(isPrecise) precisePi else bPi
    pi * Math.pow(r, 2)
  }
}

Usage
CU.area(2)
CU.area(3, true)