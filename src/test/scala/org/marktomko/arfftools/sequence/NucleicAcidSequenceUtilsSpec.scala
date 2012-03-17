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

    it("should validate legal DNA sequences") {
      val seq = "CGTGGATGAGTA"
      assert(seq === NucleicAcidSequenceUtils.validate(seq))
    }

    it("should validate uncertain DNA sequences") {
      val seq = "CGTGGA??AGTA"
      assert(seq === NucleicAcidSequenceUtils.validate(seq))
    }

    it("should reject illegal sequences") {
      val seq = "The quick brown fox jumped over the lazy dogs"
      intercept[MatchError] {
        NucleicAcidSequenceUtils.validate(seq)
      }
    }
  }
}
