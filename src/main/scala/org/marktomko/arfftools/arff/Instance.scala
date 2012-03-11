package org.marktomko.arfftools.arff

case class Instance[A](val values: Seq[Any], val classification: A) {
  override def toString = values.mkString(",")
}
