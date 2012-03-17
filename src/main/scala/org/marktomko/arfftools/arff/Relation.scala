package org.marktomko.arfftools.arff

/**
 * A simple representation of an ARFF relation, including a name,
 * a description of ARFF attributes, and a list of problem instances.
 * @param name the relation name
 * @param attributes the attributes that make up the relation
 * @param instances the problem instances
 */
case class Relation(val name: String, val attributes: Seq[Attribute], val instances: Seq[Instance]) {
  override def toString = {
    val buf = new StringBuilder

    // define the relation
    buf.append("@relation ").append(name).append('\n').append('\n')

    // define the attributes
    for (attribute <- attributes) {
      buf.append(attribute.toString).append('\n')
    }

    // emit the instance data
    buf.append("\n@data\n")
    for (instance <- instances) {
      buf.append(instance.toString).append('\n')
    }

    buf.toString
  }
}
