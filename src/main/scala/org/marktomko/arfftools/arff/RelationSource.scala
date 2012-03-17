package org.marktomko.arfftools.arff

/**
 * This class generates a relation using the provided name, attributes, instance source, and inputs.
 * @tparam A the input type
 */
class RelationSource[A] {
  def relationFrom(name: String, attributes: Seq[Attribute], instanceSource: InstanceSource[A], inputs: Seq[A]): Relation =
    Relation(name, attributes, inputs map  { instanceSource.instanceFrom(_)})
}