package org.marktomko.arfftools.arff

/**
 * Describes an object capable of producing an instance from an input
 * @tparam A the input type
 */
trait InstanceSource[A] {
  def instanceFrom(input: A): Instance
}
