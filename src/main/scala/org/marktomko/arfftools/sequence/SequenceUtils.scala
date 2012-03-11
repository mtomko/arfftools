package org.marktomko.arfftools.sequence

object SequenceUtils {
  def normalize(s: String) = s.toUpperCase

  def complement(c: Char) =
    c match {
      case 'A' => 'T'
      case 'T' => 'A'
      case 'G' => 'C'
      case 'C' => 'G'
      case _ => c
    }

  def dna2rna(c: Char) =
    c match {
      case 'T' => 'U'
      case _ => c
    }

  def complement(s: String):String = s map { complement(_) }

  def reverseComplement(s: String) = complement(s.reverse)

  def dna2Rna(s: String): String = s map dna2rna
}
