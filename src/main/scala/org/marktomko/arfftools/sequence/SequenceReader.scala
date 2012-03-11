package org.marktomko.arfftools.sequence

case class PutativePolyASite(upstream: String, signal: String, downstream: String)

object PutativePolyASite {
  def from(s: String) =
    PutativePolyASite(s.substring(0, 100), s.substring(100, 106), s.substring(106, s.length()))
}

class SequenceReader() {

}
