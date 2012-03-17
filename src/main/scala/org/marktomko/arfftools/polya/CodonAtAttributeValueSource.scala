package org.marktomko.arfftools.polya

/**
 * This attribute value source generates the codon (3 nucleotides)
 * at the specified codon index as its value. This assumes that the
 * input sequence begins at a codon-codon boundary;
 * @param i the index
 */
class CodonAtAttributeValueSource(i: Int) extends IndexBasedAttributeValueSource(i) {
  def valueFor(input: String) = {
    assert(index >= 0)
    assert(index < input.length / 3)
    input.substring(index * 3, (index + 1) * 3)
  }
}
