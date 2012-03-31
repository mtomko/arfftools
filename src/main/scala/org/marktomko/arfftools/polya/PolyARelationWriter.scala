package org.marktomko.arfftools.polya

/**
 * Main application for reading a file of DNA sequences and printing an ARFF
 * relation representing the individual sequences.
 * @author Mark Tomko <mjt0229@gmail.com>
 */
object PolyARelationWriter {
  import PolyA._

  // generate relative index offsets
  def nucleotideRelativeIndexes = FlankingIndexSource.indexesFor(64)
  def nTripleRelativeIndexes = FlankingIndexSource.indexesFor(33)

  // generate absolute index offsets
  def nucleotideAbsoluteIndexes = nucleotideRelativeIndexes map { (i: Int) =>
    if (i > 0) hexamerEndIndex + i - 1 else hexamerStartIndex + i }

  def nTripleAbsoluteIndexes = nTripleRelativeIndexes map { (i: Int) =>
    if (i > 0) hexamerEndIndex + (i - 1) * 3 else (hexamerStartIndex + i * 3) }

  /**
   * PolyARelationWriter main() method
   * @param args
   */
  def main(args: Array[String]) {
    import scala.io.Source
    import java.io.File
    import scalax.io.{Codec, Resource}
    import org.marktomko.arfftools.arff.{BooleanAttribute, Instance, Relation, StringAttribute}

    // parse args
    val (fileName, relationName, classification, outputFileName) = handleArgs(args)

    // generate all attributes that we will consider
    val nAtAttributes = nucleotideRelativeIndexes map { NucleotideAtAttributeValueSource.attributeFor(_) }
    val nTripleAtAttributes = nTripleRelativeIndexes map { NTripleAtAttributeValueSource.attributeFor(_) }
    val enrichmentAttributes = Seq(
      NucleotideEnrichmentAttributeValueSource.attributeFor('A', true),
      NucleotideEnrichmentAttributeValueSource.attributeFor('C', true),
      NucleotideEnrichmentAttributeValueSource.attributeFor('G', true),
      NucleotideEnrichmentAttributeValueSource.attributeFor('T', true),
      NucleotideEnrichmentAttributeValueSource.attributeFor('A', false),
      NucleotideEnrichmentAttributeValueSource.attributeFor('C', false),
      NucleotideEnrichmentAttributeValueSource.attributeFor('G', false),
      NucleotideEnrichmentAttributeValueSource.attributeFor('T', false)
    )

    // assemble the list of all attributes
    val attributes =
      StringAttribute("signal") +:
        (nAtAttributes ++ nTripleAtAttributes ++ enrichmentAttributes) :+
        BooleanAttribute("isPolyA")

    // construct attribute value sources
    val nAtAttributeValueSources = NucleotideAtAttributeValueSource.sourcesFor(nucleotideAbsoluteIndexes)
    val nTripleAtAttributeValueSources = NTripleAtAttributeValueSource.sourcesFor(nTripleAbsoluteIndexes)
    val enrichmentAttributeValueSources = NucleotideEnrichmentAttributeValueSource.sources()
    val attributeValueSources =
      new SignalHexamerAttributeValueSource() +:
        (nAtAttributeValueSources ++ nTripleAtAttributeValueSources ++ enrichmentAttributeValueSources).toIndexedSeq

    // map over all the lines
    val instances = Source.fromFile(new File(fileName)).getLines() map { (line :String) =>
      import org.marktomko.arfftools.sequence.NucleicAcidSequenceUtils
      Instance(attributeValueSources map { _.valueFor(NucleicAcidSequenceUtils.validate(line)) }, classification)
    }

    // construct the relation
    val relation = Relation(relationName, attributes, instances.toIndexedSeq)

    // a side-effect!
    val output = Resource.fromFile(outputFileName)
    output.write(relation.toString)(Codec.UTF8)
  }

  private[this] def handleArgs(args: Array[String]): (String, String, String, String) = {
    // make sure we got enough arguments
    if (args.length != 4) {
      throw new IllegalArgumentException("Required filename, relation name, and classification")
    }
    val fileName = args(0)
    val relationName = args(1)
    val classification = args(2)
    val outputFileName = args(3)
    (fileName, relationName, classification, outputFileName)
  }
}
