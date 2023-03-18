// Static Factory
A == Animal
import system.out.{println => pr}

trait A
class Bird extends A
class Fish extends A
class Mammal extedns A

object A {
  def apply(a: String): A = a.toLowerCase match {
    case "bird" => pr("bird")
    case "fish" => pr("fish")
    case "mammal" => pr("mammal")
    case x: String => throw new RuntimeException(s"unknown a: ${x}")
  }
}

Usage

A("BiRd")

SimpleFactory -> AbstractFactory