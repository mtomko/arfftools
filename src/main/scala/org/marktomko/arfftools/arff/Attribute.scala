package org.marktomko.arfftools.arff

sealed abstract class Attribute(val n: String, val attributeType: String) {
  override def toString = new StringBuilder("@attribute ").append(n).append(" ").append(attributeType).toString()
}

case class BinaryAttribute(name: String) extends Attribute(name, "binary")

case class NumericAttribute(name: String) extends Attribute(name, "numeric")

case class StringAttribute(name: String) extends Attribute(name, "string")

case class NominalAttribute(name: String, range: Set[String]) extends Attribute(name, "nominal") {
  override def toString =
    new StringBuilder("@attribute ")
      .append(n)
      .append(" { ")
      .append(range.mkString(",")).append(" }")
      .toString()
}

case class BooleanAttribute(name: String) extends Attribute(name, "nominal") {
  override def toString = new StringBuilder("@attribute ").append(n).append(" { true, false }").toString()
}