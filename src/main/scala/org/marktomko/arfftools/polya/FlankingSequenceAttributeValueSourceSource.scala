package org.marktomko.arfftools.polya

/**
 * This is an AttributeValueSource source that generates attribute value sources
 * for the nucleotides flanking the signal hexamer by specified numbers of nucleotides.
 */
object FlankingSequenceAttributeValueSourceSource {
  def sourcesFor(lowerBound: Int, upperBound: Int) = {
    import org.marktomko.arfftools.arff.AttributeValueSource
    assert(lowerBound < 1)
    assert(upperBound > 0)
    val upstream: Seq[AttributeValueSource[String, String]] =
      (lowerBound to -1) map  { (idx: Int) => new NucleotideAtAttributeValueSource(idx + 100) }
    val downstream: Seq[AttributeValueSource[String, String]] =
      (1 to upperBound) map { (idx: Int) => new NucleotideAtAttributeValueSource(idx + 106) }
    upstream ++ downstream
  }
}