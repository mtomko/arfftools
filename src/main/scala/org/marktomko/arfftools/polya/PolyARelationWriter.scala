package org.marktomko.arfftools.polya

object PolyARelationWriter {

  def main(args: Array[String]) {
    import io.Source
    import java.io.File
    import org.marktomko.arfftools.arff.{BooleanAttribute, Instance, Relation, StringAttribute}

    // check the arguments
    if (args.length != 3) {
      throw new IllegalArgumentException("Required filename, relation name, and classification")
    }

    val fileName = args(0)
    val relationName = args(1)
    val classification = args(2)

    // generate index offsets and real indexes
    val relativeIndexes = FlankingIndexSource.indexesFor(64)
    val absoluteIndexes = (relativeIndexes map {(i: Int) => if (i > 0) 106 + i else 100 + i})

    val nAtAttributeValueSources = NucleotideAtAttributeValueSource.sourcesFor(absoluteIndexes)
    val attributeValueSources = new SignalHexamerAttributeValueSource() +: nAtAttributeValueSources

    val flankingSeqAttributes = relativeIndexes map { NucleotideAtAttributeValueSource.attributeFor(_) }
    val attributes =
      StringAttribute("signal") +: flankingSeqAttributes.toIndexedSeq :+ BooleanAttribute("isPolyA")

    // map over all the lines
    val instances = Source.fromFile(new File(fileName)).getLines() map { (line :String) =>
      import org.marktomko.arfftools.sequence.NucleicAcidSequenceUtils
      Instance(attributeValueSources map { _.valueFor(NucleicAcidSequenceUtils.validate(line)) }, classification)
    }

    val relation = Relation(relationName, attributes, instances.toIndexedSeq)

    // a side-effect!
    System.out.println(relation.toString)
  }

}
