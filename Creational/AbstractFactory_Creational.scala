Abstract Factory(object composition)
db = database
cotor = connector
Factory = f
my = MySqlF
pg = PgSqlF

trait DBCorF {
	def connect(): SimConn
}

class MyF extends DBCotorF {
	override def connect(): SimConn = new SimMyConn
}

class PgF extends DBCotorF {
	override def connect(): SimConn = new PgMyConn
}


class DBClient(cotorF: DBCorF) {
	def executeQuery(q: String): Unit = {
		val conn = cotorF.connect()
		conn.executeQuery(q)
	}
}

val my = new DBClient(new MyF)
val pg = new DBClient(new PgF)