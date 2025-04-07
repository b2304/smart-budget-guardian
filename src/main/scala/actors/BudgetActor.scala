package actors

import akka.actor.Actor
import model.Expense

case class AnalyzeExpenses(expenses: Seq[Expense])

class BudgetActor extends Actor {
  override def receive: Receive = {
    case AnalyzeExpenses(expenses) =>
      val total = expenses.map(_.amount).sum
      if (total > 1000) {
        println(s"[ALERT] Monthly spending is high: $$${total}")
      } else {
        println(s"[INFO] Total spent: $$${total}")
      }
  }
}