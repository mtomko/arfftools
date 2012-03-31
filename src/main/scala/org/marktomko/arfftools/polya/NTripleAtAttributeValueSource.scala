package org.marktomko.arfftools.polya

import org.marktomko.arfftools.arff.NominalAttribute

/**
 * This attribute value source generates the codon (3 nucleotides)
 * at the specified codon index as its value. This assumes that the
 * input sequence begins at a codon-codon boundary;
 * @param i the index
 */
class NTripleAtAttributeValueSource(i: Int) extends IndexBasedAttributeValueSource(i) {
  def valueFor(input: String) = {
    assert(index >= 0)
    assert(index < input.length)
    val triple = input.substring(index, index + 3)
    if (triple.matches("[AGCT]{3}")) {
      triple
    }
    else {
      "?"
    }
  }
}

object NTripleAtAttributeValueSource {
  import PolyA.bases

  // scalaz magic from StackOverflow:
  // http://stackoverflow.com/questions/7474709/all-combinations-with-repetition-using-scala
  //import scalaz.Scalaz._
  //lazy val nTriples = bases.replicate[Seq](3).sequence map { _.mkString }
  lazy val nTriples = for(b1 <- bases;  b2 <- bases; b3 <- bases) yield Seq(b1, b2, b3).mkString

  def sourcesFor(indexes: Iterable[Int]) = (indexes map { new NTripleAtAttributeValueSource(_) })

  def attributeFor(i: Int) =
    NominalAttribute(
      name = new StringBuilder("c_")
        .append(if (i > 0) "plus_" else "minus_")
        .append(math.abs(i)).toString(),
      range = nTriples)
}
