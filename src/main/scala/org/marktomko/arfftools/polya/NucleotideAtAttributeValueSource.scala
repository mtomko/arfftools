package org.marktomko.arfftools.polya

/**
 * This attribute value source generates the single nucleotide at the
 * specified index as its value.
 * @param i the index
 */
class NucleotideAtAttributeValueSource(i: Int) extends IndexBasedAttributeValueSource(i) {
  override def valueFor(input: String) = {
    assert(index >= 0)
    assert(index < input.length)
    input.charAt(index).toString
  }
}

object NucleotideAtAttributeValueSource {
  import org.marktomko.arfftools.arff.NominalAttribute

  def sourcesFor(indexes: Iterable[Int]) = (indexes map { new NucleotideAtAttributeValueSource(_)}).toIndexedSeq

  def attributeFor(i: Int) =
    NominalAttribute(
      name = new StringBuilder("n_").append(if (i > 0) "plus_" else "minus_").append(math.abs(i)).toString(),
      range = Set("A", "C", "G", "T"))
}
