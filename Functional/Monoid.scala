Monoid contains type T
Monoid has associative operation
Monoid has identity element

Example:
1. Int
2. add operation: ((1 + 2) + 3) == (1 + (2 + 3))
3. 0 is identity element


Monoids can be used in parallel computations(chunks)
Monoids can be used with lists and collections

===

m = Monoid
trait M[T] {
    def op(l: T, r: T): T
    def zero: T
}

package object ms {
    val intAddition: M[Int] = new M[Int] {
        val zero = 0

        override def op(l: Int, r: Int): Int = l + r
    }

    val intMultiplication: M[Int] = new M[Int] {
        val zero = 1

        override def op(l: Int, r: Int): Int = l * r
    }

    val strConcatenation: M[String] = new M[String] {
        val zero = ""

        override def op(l: String, r: String): Strin = l + r
    }
}
import ms._
List(1,2,3,4,5,6).foldLeft(intAddition.zero)(intAddition.op)
List("a", "b", "c").foldLeft(strConcatenation.zero)(strConcatenation.op)
List("a", "b", "c").foldRight(strConcatenation.zero)(strConcatenation.op)

in Scala lib

def foldLeft[B](z: B)(f: (B, A) => B): B

object MOperatoins {
    def fold[T](list: List[T], m: Monoid[T]): T = list.foldLeft(m.zero)(m.op)
}


MOperations(List(1,2,3,4,5,6), intAddition)
MOperations(List("a", "b", "c"), strConcatenation)








