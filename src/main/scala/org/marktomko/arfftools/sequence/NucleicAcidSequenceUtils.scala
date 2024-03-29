package org.marktomko.arfftools.sequence

object NucleicAcidSequenceUtils {
  def normalize(s: String) = s.toUpperCase

  def validate(s: String): String = {
    val ns = normalize(s)
    ns map { c: Char =>
      c match {
        case 'A' => c
        case 'T' => c
        case 'U' => c // T in DNA, U in RNA
        case 'G' => c
        case 'C' => c
        case 'S' => c // G or C
        case 'W' => c // A or T
        case 'N' => c
        case 'R' => c //???
        case 'Y' => c //???
        case 'K' => c //???
        case 'D' => c //?
        case 'M' => c //?
        case '?' => c
        case '-' => c
      }
    }
  }

  def complement(c: Char) =
    c match {
      case 'A' => 'T'
      case 'T' => 'A'
      case 'G' => 'C'
      case 'C' => 'G'
      case '?' => c
      case '-' => c
      case 'N' => c
    }

  def dna2rna(c: Char) =
    c match {
      case 'T' => 'U'
      case _ => c
    }

  def rna2dna(c: Char) =
    c match {
      case 'U' => 'T'
      case _ => c
    }

  def complement(s: String):String = s map { complement(_) }

  def reverseComplement(s: String) = complement(s.reverse)

  def dna2Rna(s: String): String = s map dna2rna
}
