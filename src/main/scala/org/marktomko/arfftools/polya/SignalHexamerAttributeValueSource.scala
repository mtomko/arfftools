package org.marktomko.arfftools.polya

import org.marktomko.arfftools.arff.AttributeValueSource

/**
 * This attribute value source extracts the PolyA signal hexamer (assuming
 * that the sequence is 206 nucleotides: 100 upstream, 6 nt signal, 100 downstream.
 */
class SignalHexamerAttributeValueSource extends AttributeValueSource[String] {
  import PolyA._
  import org.marktomko.arfftools.arff.NumericAttribute

  override def valueFor(input: String) = hexamerToInt(input.substring(hexamerStartIndex, hexamerEndIndex))

  override def attributeFor = NumericAttribute("signal")

  def hexamerToInt(input: String): Int = {
    assert(input.length == 6)
    (input map { _ match {
        case 'A' => 0
        case 'C' => 1
        case 'G' => 2
        case 'T' => 3
      }
    }) reduce(_ * 4 + _ )
  }
}
