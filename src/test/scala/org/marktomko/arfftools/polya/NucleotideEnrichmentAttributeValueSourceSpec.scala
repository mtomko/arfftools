package org.marktomko.arfftools.polya

import org.scalatest.Spec

class NucleotideEnrichmentAttributeValueSourceSpec extends Spec {
  describe("valueFor") {
    it ("should count enrichment") {
      val seq = "AAGCGTTCGATACGT?"
      assert((4.0F / 15.0F - 0.25F) === new NucleotideEnrichmentAttributeValueSource('A', 0, seq.length).valueFor(seq))
    }
  }
}
