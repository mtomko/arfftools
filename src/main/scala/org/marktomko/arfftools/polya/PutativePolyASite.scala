package org.marktomko.arfftools.polya

class PutativePolyASite(val upstream: String, val signal: String, val downstream: String)

object PutativePolyASite {
  def from(s: String) =
    new PutativePolyASite(s.substring(0, 100), s.substring(100, 106), s.substring(106, s.length()))
}

