package spark

import org.apache.spark.sql.SparkSession

object BudgetAnalyzer {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("BudgetAnalyzer")
      .master("local[*]")
      .getOrCreate()

    val df = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/smart_budget")
      .option("dbtable", "expenses")
      .option("user", "root")
      .option("password", "password")
      .load()

    df.groupBy("category")
      .sum("amount")
      .show()

    spark.stop()
  }
}