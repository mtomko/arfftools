package org.marktomko.arfftools.polya

import org.marktomko.arfftools.arff.AttributeValueSource

/**
 * This attribute value source generates the codon (3 nucleotides)
 * at the specified codon index as its value. This assumes that the
 * input sequence begins at a codon-codon boundary;
 * @param relativeIndex the index relative to the point of interest
 * @param absoluteIndex the absolute index of the attribute value in the string
 */
class NTripleAtAttributeValueSource(relativeIndex: Int, absoluteIndex: Int) extends AttributeValueSource[String] {
  import org.marktomko.arfftools.arff.NominalAttribute

  override def valueFor(input: String) = {
    assert(absoluteIndex >= 0)
    assert(absoluteIndex < input.length)
    val triple = input.substring(absoluteIndex, absoluteIndex + 3)
    if (triple.matches("[AGCT]{3}")) {
      triple
    }
    else {
      "?"
    }
  }
  override def attributeFor() =
    NominalAttribute(
      name = new StringBuilder("c_")
        .append(if (relativeIndex > 0) "plus_" else "minus_")
        .append(math.abs(relativeIndex)).toString(),
      range = NTripleAtAttributeValueSource.nTriples)
}

object NTripleAtAttributeValueSource {
  import PolyA.bases

  // scalaz magic from StackOverflow:
  // http://stackoverflow.com/questions/7474709/all-combinations-with-repetition-using-scala
  //import scalaz.Scalaz._
  //lazy val nTriples = bases.replicate[Seq](3).sequence map { _.mkString }
  lazy val nTriples = for(b1 <- bases;  b2 <- bases; b3 <- bases) yield Seq(b1, b2, b3).mkString

  def sourcesFor(indexes: Iterable[(Int,Int)]):Iterable[AttributeValueSource[String]] =
    indexes map { indexes: (Int, Int) =>
      new NTripleAtAttributeValueSource(indexes._1, indexes._2) }
}
