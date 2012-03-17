package org.marktomko.arfftools.polya

object FlankingIndexSource {
  def indexesFor(flankSize: Int): Seq[Int] = indexesFor(-flankSize, flankSize)

  def indexesFor(lowerBound: Int, upperBound: Int) = {
    assert(lowerBound < 1)
    assert(upperBound > 0)
    // skip over index 0
    (lowerBound to -1) ++ (1 to upperBound)
  }
}
