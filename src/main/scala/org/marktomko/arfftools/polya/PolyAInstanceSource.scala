package org.marktomko.arfftools.polya

import org.marktomko.arfftools.arff.AttributeValueSource

sealed abstract class IndexBasedAttributeValueSource(val index: Int) extends AttributeValueSource[String, String]

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

/**
 * This attribute value source generates the codon (3 nucleotides)
 * at the specified codon index as its value. This assumes that the
 * input sequence begins at a codon-codon boundary;
 * @param i
 */
class CodonAtAttributeValueSource(i: Int) extends IndexBasedAttributeValueSource(i) {
  def valueFor(input: String) = {
    assert(index >= 0)
    assert(index < input.length / 3)
    input.substring(index * 3, (index + 1) * 3)
  }
}

/**
 * This attribute value source extracts the PolyA signal hexamer (assuming
 * that the sequence is 206 nucleotides: 100 upstream, 6 nt signal, 100 downstream.
 */
class SignalHexamerAttributeValueSource() extends AttributeValueSource[String, String] {
  def valueFor(input: String) = input.substring(100, 106)
}

/**
 * This is an AttributeValueSource source that generates attribute value sources
 * for the nucleotides flanking the signal hexamer by specified numbers of nucleotides.
 */
object FlankingSequenceAttributeValueSourceSource {
  def sourcesFor(lowerBound: Int, upperBound: Int) = {
    assert(lowerBound < 1)
    assert(upperBound > 0)
    val upstream: Seq[AttributeValueSource[String, String]] =
      (lowerBound to -1) map  { (idx: Int) => new NucleotideAtAttributeValueSource(idx + 100) }
    val downstream: Seq[AttributeValueSource[String, String]] =
      (1 to upperBound) map { (idx: Int) => new NucleotideAtAttributeValueSource(idx + 106) }
    upstream ++ downstream
  }
}