"""
def transpose(hc : HiveContext , df: DataFrame,compositeId: List[String], key: String, value: String) = {

   val distinctCols =   df.select(key).distinct.map { r => r(0) }.collect().toList

   val rdd = df.map { row =>
      (compositeId.collect { case id => row.getAs(id).asInstanceOf[Any] },
      scala.collection.mutable.Map(row.getAs(key).asInstanceOf[Any] -> row.getAs(value).asInstanceOf[Any]))
   }
   val pairRdd = rdd.reduceByKey(_ ++ _)
   val rowRdd = pairRdd.map(r => dynamicRow(r, distinctCols))
   hc.createDataFrame(rowRdd, getSchema(df.schema, compositeId, (key, distinctCols)))

}

private def dynamicRow(r: (List[Any], scala.collection.mutable.Map[Any, Any]), colNames: List[Any]) = {
   val cols = colNames.collect { case col => r._2.getOrElse(col.toString(), null) }
   val array = r._1 ++ cols
   Row(array: _*)
}

private  def getSchema(srcSchema: StructType, idCols: List[String], distinctCols: (String, List[Any])): StructType = {
   val idSchema = idCols.map { idCol => srcSchema.apply(idCol) }
   val colSchema = srcSchema.apply(distinctCols._1)
   val colsSchema = distinctCols._2.map { 
        col => StructField(col.asInstanceOf[String], colSchema.dataType, colSchema.nullable) 
   }
   StructType(idSchema ++ colsSchema)
}
"""
