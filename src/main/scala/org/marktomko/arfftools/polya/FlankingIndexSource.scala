package org.marktomko.arfftools.polya

object FlankingIndexSource {
  def indexesFor(flankSize: Int): Seq[Int] = indexesFor(-flankSize, flankSize)

  def indexesFor(lowerBound: Int, upperBound: Int) = {
    assert(lowerBound < 1)
    assert(upperBound > 0)
    // exclude 0
    (lowerBound to upperBound) filterNot { _ == 0 }
  }
}
