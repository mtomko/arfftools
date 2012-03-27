package org.marktomko.arfftools.polya

import scalaz.Scalaz._
import org.marktomko.arfftools.arff.NominalAttribute

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

object CodonAtAttributeValueSource {
  private val bases = Set('A', 'C', 'G', 'T')

  // scalaz magic from StackOverflow:
  // http://stackoverflow.com/questions/7474709/all-combinations-with-repetition-using-scala
  lazy val codons = bases.replicate[Seq](3).sequence map { _.mkString }

  def sourcesFor(indexes: Iterable[Int]) = (indexes map { new CodonAtAttributeValueSource(_) })

  def attributeFor(i: Int) =
    NominalAttribute(
      name = new StringBuilder("c_")
        .append(if (i > 0) "plus_" else "minus_")
        .append(math.abs(i)).toString(),
      range = codons)
}
