package top.dataframe.spark

import org.apache.spark.sql.{Dataset, Row}
import top.dataframe.DataFrame
import top.dataframe.reader.ListReader
import top.dataframe.writer.DataFrameWriter

import scala.collection.JavaConverters._

object SparkDfWriter {
  def write(df: Dataset[Row], writer: DataFrameWriter): Unit = {
    df.foreachPartition((partition: Iterator[Row]) => {
      val list = partition.toList
      val rows = list.map(row => {
        val map: java.util.Map[String, Object] = new java.util.HashMap[String, Object]()
        row.schema.fieldNames.foreach(name => {
          map.put(name, row.getAs[Object](name))
        })
        map
      })

      def df = DataFrame.read(new ListReader(rows.asJava))

      if (df != null) {
        df.write(writer)
      }
    })
  }
}
