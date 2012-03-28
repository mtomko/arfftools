package org.marktomko.arfftools.polya

import org.scalatest.Spec

class CodonAtAttributeValueSourceSpec extends Spec {
  describe("codons") {
    it ("should contain 64 codons") {
      // toSet makes this test robust to changes in the type
      assert(64 === CodonAtAttributeValueSource.codons.toSet.size)
    }
    it ("should contain only values like [ACGT]{3}") {
      assert(((CodonAtAttributeValueSource.codons) map { _.matches("[ACGT]{3}") }).reduce({ _ && _ }))
    }
  }
}
