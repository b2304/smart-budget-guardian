package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import controller.ExpenseController
import model.Expense

import scala.concurrent.ExecutionContext.Implicits.global

object ExpenseRoutes {
  import model.ExpenseJsonProtocol._

  val routes: Route =
    pathPrefix("expenses") {
      concat(
        pathEnd {
          get {
            onSuccess(ExpenseController.getAllExpenses()) { expenses =>
              complete(expenses)
            }
          } ~
            post {
              entity(as[Expense]) { expense =>
                onSuccess(ExpenseController.addExpense(expense)) { _ =>
                  complete(StatusCodes.Created)
                }
              }
            }
        },
        path("summary") {
          get {
            onSuccess(ExpenseController.getTotalsByCategory()) { summaries =>
              complete(summaries)
            }
          }
        }
      )
    }
}
