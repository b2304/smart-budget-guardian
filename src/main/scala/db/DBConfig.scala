package db

import slick.jdbc.MySQLProfile.api._

object DBConfig {
  val db = Database.forConfig("mysql")
}