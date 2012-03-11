package org.marktomko.arfftools.sequence

import org.scalatest.Spec

class NucleicAcidSequenceUtilsSpec extends Spec {
  describe("Sequence Utilities") {
    it("should complement individual bases") {
      assert('A' === NucleicAcidSequenceUtils.complement('T'))
      assert('T' === NucleicAcidSequenceUtils.complement('A'))
      assert('C' === NucleicAcidSequenceUtils.complement('G'))
      assert('G' === NucleicAcidSequenceUtils.complement('C'))
    }

    it("should complement DNA sequences") {
      val seq = "CGTGGATGAGTA"
      assert("GCACCTACTCAT" === NucleicAcidSequenceUtils.complement(seq))
    }

    it("should reverse complement DNA sequences") {
      val seq = "CGTGGATGAGTA"
      assert("TACTCATCCACG" === NucleicAcidSequenceUtils.reverseComplement(seq))
    }
  }
}
