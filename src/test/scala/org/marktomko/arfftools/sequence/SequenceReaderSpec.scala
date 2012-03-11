package org.marktomko.arfftools.sequence

import org.scalatest.Spec

class SequenceReaderSpec extends Spec {
  describe("PutativePolyASite helper") {
    it("should dissect sequences") {
      val seq = "GTTAACTTGTCCCCCAAACCCTTACTTGTCATGATCTTGCCGTTTTGGGTAATCAAATTTGATCCTGAGTGAAAAAATACCAACTAAATGATGGGCCCGAAATAAAAGATAGTCTCTTCTTTGTGCCGTTAAAAAGAAAAAAGGTTTGTGCTTAGCGTGTGTTGAACTCAGCTAAGGGAAAACCTGCCACCTTCCCTATGAAAGCT"
      assert(206 === seq.length())
      assert(6 === PutativePolyASite.from(seq).signal.length)
      assert(100 === PutativePolyASite.from(seq).upstream.length)
      assert(100 === PutativePolyASite.from(seq).downstream.length)
    }
  }
}
