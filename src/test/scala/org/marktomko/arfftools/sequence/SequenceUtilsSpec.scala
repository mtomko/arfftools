package org.marktomko.arfftools.sequence

import org.scalatest.Spec

class SequenceUtilsSpec extends Spec {
  describe("Sequence utilities") {
    it("should complement individual bases") {
      assert('A' === SequenceUtils.complement('T'))
      assert('T' === SequenceUtils.complement('A'))
      assert('C' === SequenceUtils.complement('G'))
      assert('G' === SequenceUtils.complement('C'))
    }

    it("should complement DNA sequences") {
      val seq = "CGTGGATGAGTA"
      assert("GCACCTACTCAT" === SequenceUtils.complement(seq))
    }

    it("should reverse complement DNA sequences") {
      val seq = "CGTGGATGAGTA"
      assert("TACTCATCCACG" === SequenceUtils.reverseComplement(seq))
    }
  }
}
