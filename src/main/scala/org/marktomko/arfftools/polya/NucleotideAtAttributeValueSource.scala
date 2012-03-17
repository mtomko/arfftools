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
