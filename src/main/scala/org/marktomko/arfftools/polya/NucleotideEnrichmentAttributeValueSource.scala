package org.marktomko.arfftools.polya

import org.marktomko.arfftools.arff.{NumericAttribute, AttributeValueSource}


/**
 * Nucleotide enrichment is the difference between the percent of bases
 * present over a particular sequence and the percent that would be
 * expected given an even distribution of the four bases. It is over the
 * range [-0.25, 0.75].
 * @param base the base we are calculating enrichment for
 */
class NucleotideEnrichmentAttributeValueSource(val base: Char, val lower: Int, val upper: Int)
  extends AttributeValueSource[String] {

  override def valueFor(input: String) = {
    val subSeq = input.substring(lower, upper)
    val baseCount = (subSeq filter { _ == base }).size.toFloat
    val definiteSubSeqCount = (subSeq filter { Seq('A', 'C', 'G', 'T') contains _ }).size
    (baseCount / definiteSubSeqCount) - 0.25F
  }

  override def attributeFor() = {
    val upstream = upper <= PolyA.hexamerStartIndex
    NumericAttribute(
      name = new StringBuilder()
        .append(base)
        .append("_enrich_")
        .append(if (upstream) "upstream" else "downstream").toString())
  }

  override def toString =
    new StringBuilder("NucleotideEnrichmentAttributeValueSource(")
      .append(base)
      .append(',')
      .append(lower)
      .append(',')
      .append(upper)
      .append(')').toString()
}

object NucleotideEnrichmentAttributeValueSource {

  def sources():Iterable[NucleotideEnrichmentAttributeValueSource] = {
    import PolyA._
    Seq(
      new NucleotideEnrichmentAttributeValueSource('A', 0, hexamerStartIndex),
      new NucleotideEnrichmentAttributeValueSource('C', 0, hexamerStartIndex),
      new NucleotideEnrichmentAttributeValueSource('G', 0, hexamerStartIndex),
      new NucleotideEnrichmentAttributeValueSource('T', 0, hexamerStartIndex),
      new NucleotideEnrichmentAttributeValueSource('A', hexamerEndIndex, sequenceLength),
      new NucleotideEnrichmentAttributeValueSource('C', hexamerEndIndex, sequenceLength),
      new NucleotideEnrichmentAttributeValueSource('G', hexamerEndIndex, sequenceLength),
      new NucleotideEnrichmentAttributeValueSource('T', hexamerEndIndex, sequenceLength)
    )
  }
}
