package org.marktomko.arfftools.polya

import org.marktomko.arfftools.arff.AttributeValueSource

/**
 * Abstract base class for attribute value sources that inspect a sequence and
 * return some property at a particular location within the sequence. This
 * was intended to represent both nucleotide- and codon-based views of a
 * sequence; in practice, the abstraction may not provide much in terms of
 * semantic or syntactic value, in which case it might need to be simply
 * removed for clarity's sake.
 * @author Mark Tomko <mjt0229@gmail.com>
 * @param index the index to inspect
 */
abstract class IndexBasedAttributeValueSource(val index: Int) extends AttributeValueSource[String, String]
