package db

import model.{Expense, ExpenseSummary}
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class ExpenseTable(tag: Tag) extends Table[Expense](tag, "expenses") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def category = column[String]("category")
  def amount = column[Double]("amount")
  def date = column[String]("date")

  def * = (id.?, category, amount, date) <> (Expense.tupled, Expense.unapply)
}

object ExpenseRepository {
  val db = DBConfig.db
  val expenses = TableQuery[ExpenseTable]

  def createSchemaIfNotExists()(implicit ec: ExecutionContext): Future[Unit] = {
    db.run(expenses.schema.createIfNotExists)
      .map(_ => println("[DB] Table `expenses` ensured"))
  }

  def insert(expense: Expense)(implicit ec: ExecutionContext): Future[Int] =
    db.run(expenses += expense)

  def listAll()(implicit ec: ExecutionContext): Future[Seq[Expense]] =
    db.run(expenses.result)

  def totalByCategory()(implicit ec: ExecutionContext): Future[Seq[ExpenseSummary]] = {
    val query = expenses.groupBy(_.category).map {
      case (cat, group) => (cat, group.map(_.amount).sum.getOrElse(0.0))
    }
    db.run(query.result).map(_.map { case (cat, total) => ExpenseSummary(cat, total) })
  }
}