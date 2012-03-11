package org.marktomko.arfftools.arff

abstract case class Attribute(val name: String, val attributeType: String) {
  override def toString = new StringBuilder("@attribute ").append(name).append(" ").append(attributeType).toString()
}

case class BinaryAttribute(n: String) extends Attribute(n, "binary")

case class NumericAttribute(n: String) extends Attribute(n, "numeric")

case class NominalAttribute(n: String, range: Set[String]) extends Attribute(n, "nominal")