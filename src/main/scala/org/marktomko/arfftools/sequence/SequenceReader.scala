package org.marktomko.arfftools.sequence

case class PolyASequence(val sequence: String) {
  val upstream = sequence.substring(0, 100)
  val signal = sequence.substring(100, 106)
  val downstream = sequence.substring(106, sequence.length)
}

class SequenceReader() {

}
