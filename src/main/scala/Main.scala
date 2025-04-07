import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer
import db.ExpenseRepository
import routes.ExpenseRoutes

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("SmartBudgetSystem")
  implicit val materializer: Materializer = Materializer(system)
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  ExpenseRepository.createSchemaIfNotExists().onComplete {
    case Success(_) =>
      println("[DB] Schema setup complete.")
    case Failure(ex) =>
      println(s"[DB] Failed to create schema: ${ex.getMessage}")
  }

  val routes = ExpenseRoutes.routes

  Http().newServerAt("0.0.0.0", 8080).bind(routes).onComplete {
    case Success(binding) =>
      println(s"Server started at http://${binding.localAddress.getHostName}:${binding.localAddress.getPort}/")
    case Failure(ex) =>
      println(s"Failed to bind HTTP server: ${ex.getMessage}")
      system.terminate()
  }
}
