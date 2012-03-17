package org.marktomko.arfftools.arff

/**
 * Describes an object capable of producing values for an attribute
 * @tparam A the input type
 * @tparam B the attribute value type
 */
trait AttributeValueSource[A, B] {
  def valueFor(input: A): B
}
