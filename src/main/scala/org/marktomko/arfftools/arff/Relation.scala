package org.marktomko.arfftools.arff

case class Relation[A](val name: String, val attributes: Seq[Attribute], val instances: Seq[Instance[A]]) {
  override def toString = {
    val buf = new StringBuilder

    // define the relation
    buf.append("@relation ").append(name).append('\n').append('\n')

    // define the attributes
    for (attribute <- attributes) {
      buf.append(attribute.toString).append('\n')
    }

    // emit the instance data
    buf.append('\n')
    for (instance <- instances) {
      buf.append(instance.toString).append('\n')
    }

    buf.toString
  }
}
