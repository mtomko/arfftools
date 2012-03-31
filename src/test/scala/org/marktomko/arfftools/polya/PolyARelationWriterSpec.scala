package org.marktomko.arfftools.polya

import org.scalatest.Spec

class PolyARelationWriterSpec extends Spec {
  describe ("nucleotideAbsoluteIndexes") {
    it ("should contain values [36, 99] union [106, 169]") {
      assert((36 to 99) ++ (106 to 169) === PolyARelationWriter.nucleotideAbsoluteIndexes)
    }
    it ("should contain 128 values") {
      assert(128 === PolyARelationWriter.nucleotideAbsoluteIndexes.size)
    }
  }

  describe ("nTripleAbsoluteIndexes") {
    it ("should contain 66 values") {
      assert(66 === PolyARelationWriter.nTripleAbsoluteIndexes.size)
    }
    it ("should contain every third value over the range [[1, 97] union [106, 203]]") {
      val expectedLower = (1 to 97) filter { (i: Int) => (i - 1) % 3 == 0}
      val expectedUpper = (106 to 203) filter { (i: Int) => (i - 106) % 3 == 0 }
      val expected = expectedLower ++ expectedUpper
      assert(expected === PolyARelationWriter.nTripleAbsoluteIndexes)
    }
  }
}
