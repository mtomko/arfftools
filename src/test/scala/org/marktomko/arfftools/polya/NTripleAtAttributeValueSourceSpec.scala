package org.marktomko.arfftools.polya

import org.scalatest.Spec

class NTripleAtAttributeValueSourceSpec extends Spec {
  describe("triples") {
    it ("should contain 64 nucleotide triples") {
      // toSet makes this test robust to changes in the type
      assert(64 === NTripleAtAttributeValueSource.nTriples.toSet.size)
    }
    it ("should contain only values like [ACGT]{3}") {
      assert(((NTripleAtAttributeValueSource.nTriples) map { _.matches("[ACGT]{3}") }).reduce({ _ && _ }))
    }
  }
  describe("valueFor") {
    it ("should generate values correctly for absolute indexes") {
      val relative = Seq(-2, -1, 1, 2)
      val hexamerStartIndex = 6
      val hexamerEndIndex = hexamerStartIndex + 6
      val absolute = relative map { (i: Int) => if (i > 0) hexamerEndIndex + (i - 1) * 3 else hexamerStartIndex + i*3}
      assert(Seq(0, 3, 12, 15) === absolute )
    }
  }
}
