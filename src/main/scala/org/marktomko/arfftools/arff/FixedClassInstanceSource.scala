package org.marktomko.arfftools.arff

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