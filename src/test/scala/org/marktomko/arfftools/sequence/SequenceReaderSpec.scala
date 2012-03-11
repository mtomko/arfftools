package org.marktomko.arfftools.sequence

import org.scalatest.Spec

class SequenceReaderSpec extends Spec {
  describe("PolyASequence") {
    it("should dissect sequences") {
      val seq = "GTTAACTTGTCCCCCAAACCCTTACTTGTCATGATCTTGCCGTTTTGGGTAATCAAATTTGATCCTGAGTGAAAAAATACCAACTAAATGATGGGCCCGAAATAAAAGATAGTCTCTTCTTTGTGCCGTTAAAAAGAAAAAAGGTTTGTGCTTAGCGTGTGTTGAACTCAGCTAAGGGAAAACCTGCCACCTTCCCTATGAAAGCT"
      assert(206 === seq.length())
      assert(6 === PolyASequence(seq).signal.length)
      assert(100 === PolyASequence(seq).upstream.length)
      assert(100 === PolyASequence(seq).downstream.length)
    }
  }

}
