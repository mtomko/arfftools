package org.marktomko.arfftools.polya

import org.marktomko.arfftools.arff.AttributeValueSource

/**
 * This attribute value source generates the single nucleotide at the
 * specified index as its value.
 * @param relativeIndex the index relative to the point of interest
 * @param absoluteIndex the absolute index of the attribute value in the string
 */
class NucleotideAtAttributeValueSource(relativeIndex: Int, absoluteIndex: Int) extends AttributeValueSource[String] {
  import org.marktomko.arfftools.arff.NominalAttribute

  val dnaBases = Set("A", "C", "G", "T")

  override def valueFor(input: String) = {
    assert(absoluteIndex >= 0)
    assert(absoluteIndex < input.length)
    val base = input.charAt(absoluteIndex).toString
    base match {
      case "A"|"C"|"G"|"T" => base
      case _ => "?"
     }
  }

  override def attributeFor() =
    NominalAttribute(
      name = new StringBuilder("n_")
        .append(if (relativeIndex > 0) "plus_" else "minus_")
        .append(math.abs(relativeIndex)).toString(),
      range = dnaBases)
}

object NucleotideAtAttributeValueSource {
  def sourcesFor(indexes: Iterable[(Int,Int)]):Iterable[AttributeValueSource[String]] =
    indexes map { indexes: (Int, Int) =>
      new NucleotideAtAttributeValueSource(indexes._1, indexes._2) }
}
