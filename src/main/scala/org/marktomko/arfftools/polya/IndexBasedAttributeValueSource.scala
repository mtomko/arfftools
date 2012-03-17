package org.marktomko.arfftools.polya

import org.marktomko.arfftools.arff.AttributeValueSource

abstract class IndexBasedAttributeValueSource(val index: Int) extends AttributeValueSource[String, String]
