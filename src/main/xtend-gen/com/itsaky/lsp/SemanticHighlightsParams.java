package com.itsaky.lsp;

import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.eclipse.xtend.lib.Property;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class SemanticHighlightsParams {
  @Property
  private TextDocumentIdentifier _textDocument;
  
  @Pure
  public TextDocumentIdentifier getTextDocument() {
    return this._textDocument;
  }
  
  public void setTextDocument(final TextDocumentIdentifier textDocument) {
    this._textDocument = textDocument;
  }
}
