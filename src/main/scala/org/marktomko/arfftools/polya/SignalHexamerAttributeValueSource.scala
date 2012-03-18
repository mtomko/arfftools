package org.marktomko.arfftools.polya

import org.marktomko.arfftools.arff.AttributeValueSource

/**
 * This attribute value source extracts the PolyA signal hexamer (assuming
 * that the sequence is 206 nucleotides: 100 upstream, 6 nt signal, 100 downstream.
 */
class SignalHexamerAttributeValueSource extends AttributeValueSource[String, String] {
  import PolyA._
  def valueFor(input: String) = input.substring(hexamerStartIndex, hexamerEndIndex)
}
