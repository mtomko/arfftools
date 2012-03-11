package org.marktomko.arfftools.arff

/**
 * Describes an object capable of producing values for an attribute
 * @tparam A the input type
 * @tparam B the attribute value type
 */
trait AttributeValueSource[A,B] {
  def valueFor(input: A): B
}

/**
 * Describes an object capable of producing an instance from an input
 * @tparam A the input type
 */
trait InstanceSource[A] {
  def instanceFrom(input: A): Instance
}

/**
 * A simple InstanceSource that uses a(n ordered) sequence of
 * AttributeValueSources to generate an instance with a provided
 * classification.
 * @param attributeValueSources
 * @param classification
 * @tparam A the input type
 */
class FixedClassInstanceSource[A](
  val attributeValueSources: Seq[AttributeValueSource[A, _]],
  val classification: String) extends InstanceSource[A] {

  override def instanceFrom(input: A) = {
    val values = attributeValueSources map { _.valueFor(input) }
    Instance(values, classification)
  }

}

/**
 * This class generates a relation using the provided name, attributes, instance source, and inputs.
 * @tparam A the input type
 */
class RelationSource[A] {
  def relationFrom(name: String, attributes: Seq[Attribute], instanceSource: InstanceSource[A], inputs: Seq[A]): Relation =
    Relation(name, attributes, inputs map  { instanceSource.instanceFrom(_)})
}