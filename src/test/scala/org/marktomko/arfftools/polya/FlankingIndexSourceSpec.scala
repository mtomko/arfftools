package org.marktomko.arfftools.polya

import org.scalatest.Spec

class FlankingIndexSourceSpec extends Spec {
  describe ("indexesFor") {
    it ("should generate symmetric ranges from negative to positive, excluding 0") {
      assert(Seq(-1, 1) === FlankingIndexSource.indexesFor(1).toIndexedSeq)
    }

    it ("should generate asymmetric ranges, excluding 0") {
      assert(Seq(-5, -4, -3, -2, -1, 1) === FlankingIndexSource.indexesFor(-5, 1))
    }

    it ("should reject ranges not surrounding 0") {
      intercept[AssertionError] {
        FlankingIndexSource.indexesFor(1, -1)
      }
    }
  }
}
