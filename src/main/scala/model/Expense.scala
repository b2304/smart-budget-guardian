package model

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class Expense(id: Option[Int], category: String, amount: Double, date: String)

case class ExpenseSummary(category: String, total: Double)

object ExpenseHelper {
  def tupled: ((Option[Int], String, Double, String)) => Expense = (Expense.apply _).tupled
}

object ExpenseJsonProtocol extends DefaultJsonProtocol {
  implicit val expenseFormat: RootJsonFormat[Expense] = jsonFormat4(Expense) // or Expense.apply if object name clashes
  implicit val summaryFormat: RootJsonFormat[ExpenseSummary] = jsonFormat2(ExpenseSummary)
}