package org.marktomko.arfftools.polya

/**
 * Main application for reading a file of DNA sequences and printing an ARFF
 * relation representing the individual sequences.
 * @author Mark Tomko <mjt0229@gmail.com>
 */
object PolyARelationWriter {

  def main(args: Array[String]) {
    import io.Source
    import java.io.File
    import PolyA._
    import org.marktomko.arfftools.arff.{BooleanAttribute, Instance, Relation, StringAttribute}

    val (fileName, relationName, classification) = handleArgs(args)

    // generate index offsets and real indexes
    val relativeIndexes = FlankingIndexSource.indexesFor(64)
    val absoluteIndexes = relativeIndexes map { (i: Int) =>
      if (i > 0) hexamerEndIndex + i else hexamerStartIndex + i }

    // build the descriptions of attributes we will generate
    val flankingSeqAttributes = relativeIndexes map { NucleotideAtAttributeValueSource.attributeFor(_) }
    val attributes = StringAttribute("signal") +: flankingSeqAttributes.toIndexedSeq :+ BooleanAttribute("isPolyA")

    // construct attribute value sources
    val nAtAttributeValueSources = NucleotideAtAttributeValueSource.sourcesFor(absoluteIndexes)
    val attributeValueSources = new SignalHexamerAttributeValueSource() +: nAtAttributeValueSources.toIndexedSeq

    // map over all the lines
    val instances = Source.fromFile(new File(fileName)).getLines() map { (line :String) =>
      import org.marktomko.arfftools.sequence.NucleicAcidSequenceUtils
      Instance(attributeValueSources map { _.valueFor(NucleicAcidSequenceUtils.validate(line)) }, classification)
    }

    // construct the relation
    val relation = Relation(relationName, attributes, instances.toIndexedSeq)

    // a side-effect!
    System.out.println(relation.toString)
  }

  private[this] def handleArgs(args: Array[String]): (String, String, String) = {
    // make sure we got enough arguments
    if (args.length != 3) {
      throw new IllegalArgumentException("Required filename, relation name, and classification")
    }
    val fileName = args(0)
    val relationName = args(1)
    val classification = args(2)
    (fileName, relationName, classification)
  }
}
