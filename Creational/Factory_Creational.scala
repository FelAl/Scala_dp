Factory DP(Using inheritance)
sim == simple
conn = connecton
trait SimConn {
    def getName(): String
    def executeQuery(q: String): Unit
}

class SimMysqlConn extends SimConn {
    override def getName(): String = "mysql"
    override def executeQuery(q: String) = ...
}

class SimPgConn extends SimConn {
    override def getName(): String = "pg"
    override def executeQuery(q: String) = ...
}

absctract class DBClient {
    def executeQuery(q: String): Unit = {
        val connection = connect()
        connection.executeQuery(q)
    }

    protected def connect(): SimConn
}

class MysqlClient extends DBClient {
    override protected def connect(): SimConn = new SimMysqlConn
}

class PgClient extends DBClient {
    override protected def connect(): SimConn = new SimPgConn
}

val my = new MysqlClient
val pg = new PgClient

