// Singleton

object StringUtils {
  def someStrManipulation(text: String): String = text + "_updated"
}

// StringUtils - is a singleton object
// someStrManipulation - is a static method
// usefull for creating utility classes with no state
// singleton are thread-safe in Scala out of the box
// Singleton are offten anti-pattern

object AppRegistry {
  private val users: Map[String, String] = TrieMap.empty

  def addUser(id: String, name: String): Unit = {
    users.put(id, name)
  }

  def removeUser(id: String): Unit = {
    users.remove(id)
  }
}