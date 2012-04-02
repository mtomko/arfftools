package org.marktomko.arfftools.polya

import org.scalatest.Spec

class SignalHexamerAttributeValueSourceTest extends Spec {
  describe("hexamerToInt") {
    it ("should map hexamers over the range [0, 4096)") {
      val avs = new SignalHexamerAttributeValueSource()
      assert(0 === avs.hexamerToInt("AAAAAA"))
      assert(1 === avs.hexamerToInt("AAAAAC"))
      assert(2 === avs.hexamerToInt("AAAAAG"))
      assert(3 === avs.hexamerToInt("AAAAAT"))
      assert(4 === avs.hexamerToInt("AAAACA"))
      assert(10 === avs.hexamerToInt("AAAAGG"))
      assert(4095 === avs.hexamerToInt("TTTTTT"))
    }
  }
}
