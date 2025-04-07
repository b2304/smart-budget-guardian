package controller

import db.ExpenseRepository
import model.{Expense, ExpenseSummary}

import scala.concurrent.{ExecutionContext, Future}

object ExpenseController {
  def addExpense(expense: Expense)(implicit ec: ExecutionContext): Future[Int] =
    ExpenseRepository.insert(expense)

  def getAllExpenses()(implicit ec: ExecutionContext): Future[Seq[Expense]] =
    ExpenseRepository.listAll()

  def getTotalsByCategory()(implicit ec: ExecutionContext): Future[Seq[ExpenseSummary]] =
    ExpenseRepository.totalByCategory()
}