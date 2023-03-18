d == data
pr = println
Pers = Persister
Mem = Memory

trait Pers[T] {
  def persist(d: T)
}

trait DB[T] {
  def save(d: T)
}

trait MemDB[T] extends DB[T] {
  val db = MutableList.empy
  override def save(d: T): Unit = 
    db = db :+ d
}

trait FileDB[T] extends DB[T] {
  override def save(d: T): Unit = pr("Writing to file")
}

How do we pass our DB to Pers?
1. Extend DB in Pers(Functionality leaking, bad option)
2. Have a variable of DB in Pers
3. Self type

trait Pers[T] {
  this: DB[T] =>
  def persist(d: T): Unit = save(d)
}
//this: DB[T] ~ self: DB[T]

class FilePers[T] extends Pers[T] with FileDB[T]
class MemPers[T] extends Pers[T] with MemDB[T]

trait Pers[T] {
  this: DB[T] with History[T] =>
  override def persist(d: T): Unit = {
    save(d)
    addToHistory()
  }
}




// conflicting self types
trait History {
  def add(): Unit = pr("Add to history")
}

trait Mystery {
  def add(): Unit = pr("Add to mystery")
}

trait Pers[T] {
  this: DB[T] with History[T] with Mystery[T] =>
  def persist(d: T): Unit = {
    save(d)
    add()
  }
}

class FilePers[T] extends Pers[T] with FileDB[T] with History with Mystery {
  override def add(): Unit = 
    super[History].add

}
class MemPers[T] extends Pers[T] with MemDB[T] with History with Mystery {
  override def add(): Unit =
    super[Mystery].add
}