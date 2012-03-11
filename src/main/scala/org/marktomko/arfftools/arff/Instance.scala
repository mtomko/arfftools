package org.marktomko.arfftools.arff

/**
 * Simple class representing a single problem instance.
 * @param values the collection of values comprising the instance
 * @param classification the class value
 */
case class Instance(val values: Seq[Any], val classification: String) {
  override def toString = (values :+ classification) mkString(",")
}
