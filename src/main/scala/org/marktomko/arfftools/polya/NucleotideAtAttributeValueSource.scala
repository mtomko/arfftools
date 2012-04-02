package org.marktomko.arfftools.polya

import org.marktomko.arfftools.arff.AttributeValueSource

/**
 * This attribute value source generates the single nucleotide at the
 * specified index as its value.
 * @param i the index
 */
class NucleotideAtAttributeValueSource(i: Int) extends IndexBasedAttributeValueSource(i) {
  import org.marktomko.arfftools.arff.NominalAttribute

  val dnaBases = Set("A", "C", "G", "T")

  override def valueFor(input: String) = {
    assert(index >= 0)
    assert(index < input.length)
    val base = input.charAt(index).toString
    base match {
      case "A"|"C"|"G"|"T" => base
      case _ => "?"
     }
  }

  override def attributeFor() =
    NominalAttribute(
      name = new StringBuilder("n_")
        .append(if (index > 0) "plus_" else "minus_")
        .append(math.abs(index)).toString(),
      range = dnaBases)
}

object NucleotideAtAttributeValueSource {
  def sourcesFor(indexes: Iterable[Int]):Iterable[AttributeValueSource[String]] = (indexes map { new NucleotideAtAttributeValueSource(_)})
}
