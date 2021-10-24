package org.javacs.semantics;

import java.util.List;
import java.util.logging.Logger;

import com.itsaky.lsp.JavadocHighlights;
import com.sun.source.doctree.AuthorTree;
import com.sun.source.doctree.DeprecatedTree;
import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocRootTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.HiddenTree;
import com.sun.source.doctree.IndexTree;
import com.sun.source.doctree.InheritDocTree;
import com.sun.source.doctree.InlineTagTree;
import com.sun.source.doctree.LinkTree;
import com.sun.source.doctree.LiteralTree;
import com.sun.source.doctree.ParamTree;
import com.sun.source.doctree.ProvidesTree;
import com.sun.source.doctree.ReturnTree;
import com.sun.source.doctree.SeeTree;
import com.sun.source.doctree.SerialDataTree;
import com.sun.source.doctree.SinceTree;
import com.sun.source.doctree.SummaryTree;
import com.sun.source.doctree.ThrowsTree;
import com.sun.source.doctree.UnknownBlockTagTree;
import com.sun.source.doctree.UnknownInlineTagTree;
import com.sun.source.doctree.UsesTree;
import com.sun.source.doctree.ValueTree;
import com.sun.source.doctree.VersionTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.LineMap;
import com.sun.source.util.DocSourcePositions;
import com.sun.source.util.DocTreeScanner;
import com.sun.source.util.DocTrees;

import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.javacs.CompileTask;

public class DocHighlighter extends DocTreeScanner <Void, JavadocHighlights> {
	
	private final CompilationUnitTree root;
	private final DocCommentTree doc;
	private final DocTrees trees;
	private final DocSourcePositions positions;
	private final LineMap lines;
	private final CancelChecker checker;
	
	public DocHighlighter (CompileTask task, CompilationUnitTree root, DocCommentTree doc, CancelChecker checker) {
		this.root = root;
		this.doc = doc;
		this.checker = checker;
		this.lines = root.getLineMap();
		this.trees = DocTrees.instance(task.task);
		this.positions = trees.getSourcePositions();
	}
	
	private void addTrees (List<? extends DocTree> trees, List<Range> ranges) {
		if(trees != null && !trees.isEmpty()) {
			for (DocTree tree : trees) {
				addTree (tree, ranges);
			}
		}
	}
	
	private void addTree (DocTree tree, List<Range> ranges) {
		checker.checkCanceled();
		final var start = positions.getStartPosition(root, doc, tree);
		final var end = positions.getEndPosition(root, doc, tree);
		final var startLine = lines.getLineNumber(start) - 1;
		final var startCol = lines.getColumnNumber(start) - 1;
		final var endLine = lines.getLineNumber(end) - 1;
		final var endCol = lines.getColumnNumber(end) - 1;
		
		checker.checkCanceled();
		
		final var range = new Range();
		range.setStart(new Position( (int) startLine, (int) startCol));
		range.setEnd(new Position( (int) endLine, (int) endCol));
		
		ranges.add(range);
	}
	
	private void addTagName (DocTree tree, String name, List<Range> ranges) {
		checker.checkCanceled();
		final var start = positions.getStartPosition(root, doc, tree);
		
		final var startLine = lines.getLineNumber(start) - 1;
		final var startCol = lines.getColumnNumber(start) - 1;
		
		// Tag names are always on the same line
		var endLine = startLine;
		var endCol = startCol + name.length() + 1; // +1. Because startCol includes '@'
		
		if(tree instanceof InlineTagTree) {
			endCol++; // InlineTagTree contains starting '{' in its range
		}
		
		checker.checkCanceled();
		
		final var range = new Range();
		range.setStart(new Position( (int) startLine, (int) startCol));
		range.setEnd(new Position( (int) endLine, (int) endCol));
		
		ranges.add(range);
		
		if(tree instanceof InlineTagTree) {
			// Highlight '}' of inline tag tree too
			final var end = positions.getEndPosition(root, doc, tree);
			
			endLine = lines.getLineNumber(end) - 1;
			endCol = lines.getColumnNumber(end) - 1;
			
			final var closing = new Range();
			closing.setStart(new Position((int) endLine, (int) endCol));
			closing.setEnd(new Position((int) endLine, (int) endCol + 1));
			ranges.add(closing);
		}
	}
	
	@Override
	public Void visitAuthor (AuthorTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.authorTags);
		addTrees (tree.getName(), highlights.authorNames);
		
		return super.visitAuthor (tree, highlights);
	}
	
	@Override
	public Void visitDeprecated (DeprecatedTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.deprecatedTags);
		addTrees (tree.getBody(), highlights.deprecatedMessages);
		
		return super.visitDeprecated (tree, highlights);
	}
	
	@Override
	public Void visitDocRoot (DocRootTree tree, JavadocHighlights highlights) {
		addTree (tree, highlights.docrootTags);
		return super.visitDocRoot (tree, highlights);
	}
	
	@Override
	public Void visitHidden (HiddenTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.hiddenTags);
		addTrees (tree.getBody(), highlights.hiddenMessages);
		return super.visitHidden (tree, highlights);
	}
	
	@Override
	public Void visitIndex (IndexTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.indexTags);
		addTrees (tree.getDescription(), highlights.indexDescriptions);
		addTree (tree.getSearchTerm(), highlights.indexSearchTerms);
		
		return super.visitIndex (tree, highlights);
	}
	
	@Override
	public Void visitInheritDoc (InheritDocTree tree, JavadocHighlights highlights) {
		addTagName (tree, tree.getTagName(), highlights.inheritDocTags);
		return super.visitInheritDoc (tree, highlights);
	}
	
	@Override
	public Void visitLink (LinkTree tree, JavadocHighlights highlights) {
		addTrees (tree.getLabel(), highlights.linkLabels);
		addTree (tree.getReference(), highlights.linkReferences);
		addTagName(tree, tree.getTagName(), highlights.linkTags);
		
		return super.visitLink (tree, highlights);
	}
	
	@Override
	public Void visitLiteral (LiteralTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.literalTags);
		addTree (tree.getBody(), highlights.literalTexts);
		return super.visitLiteral (tree, highlights);
	}
	
	@Override
	public Void visitParam (ParamTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.paramTags);
		addTrees (tree.getDescription(), highlights.paramDescriptions);
		addTree(tree.getName(), highlights.paramNames);
		
		return super.visitParam (tree, highlights);
	}
	
	@Override
	public Void visitProvides (ProvidesTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.providesTags);
		addTrees(tree.getDescription(), highlights.providesDescriptions);
		addTree (tree.getServiceType(), highlights.providesServiceTypes);
		return super.visitProvides (tree, highlights);
	}
	
	@Override
	public Void visitReturn (ReturnTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.returnTags);
		addTrees (tree.getDescription(), highlights.returnDescriptions);
		return super.visitReturn (tree, highlights);
	}
	
	@Override
	public Void visitSee (SeeTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.seeTags);
		addTrees (tree.getReference(), highlights.seeReferences);
		return super.visitSee (tree, highlights);
	}
	
	@Override
	public Void visitSerialData (SerialDataTree tree, JavadocHighlights highlights) {
		addTagName (tree, tree.getTagName(), highlights.serialDataTags);
		addTrees (tree.getDescription(), highlights.serialDataDescriptions);
		return super.visitSerialData (tree, highlights);
	}
	
	@Override
	public Void visitSince (SinceTree tree, JavadocHighlights highlights) {
		addTagName (tree, tree.getTagName(), highlights.sinceTags);
		addTrees (tree.getBody(), highlights.sinceBodies);
		return super.visitSince (tree, highlights);
	}
	
	@Override
	public Void visitSummary (SummaryTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.summaryTags);
		addTrees (tree.getSummary(), highlights.summaryMessages);
		return super.visitSummary(tree, highlights);
	}
	
	@Override
	public Void visitThrows (ThrowsTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.throwsTags);
		addTrees (tree.getDescription(), highlights.throwsDescriptions);
		addTree(tree.getExceptionName(), highlights.throwsNames);
		return super.visitThrows (tree, highlights);
	}
	
	@Override
	public Void visitUses (UsesTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.usesTags);
		addTree(tree.getServiceType(), highlights.usesServiceTypes);
		addTrees (tree.getDescription(), highlights.usesDescriptions);
		return super.visitUses (tree, highlights);
	}
	
	@Override
	public Void visitValue (ValueTree tree, JavadocHighlights highlights) {
		addTagName(tree, tree.getTagName(), highlights.valueTags);
		addTree (tree.getReference(), highlights.valueReferences);
		return super.visitValue (tree, highlights);
	}
	
	@Override
	public Void visitVersion (VersionTree tree, JavadocHighlights highlights) {
		addTagName (tree, tree.getTagName(), highlights.versionTags);
		addTrees (tree.getBody(), highlights.versionBodies);
		return super.visitVersion (tree, highlights);
	}
	
	@Override
	public Void visitUnknownBlockTag (UnknownBlockTagTree tree, JavadocHighlights highlights) {
		addTagName (tree, tree.getTagName(), highlights.unknownTags);
		addTrees (tree.getContent(), highlights.unknownTagContents);
		return super.visitUnknownBlockTag (tree, highlights);
	}
	
	@Override
	public Void visitUnknownInlineTag(UnknownInlineTagTree tree, JavadocHighlights highlights) {
		addTagName (tree, tree.getTagName(), highlights.unknownInlineTags);
		addTrees (tree.getContent(), highlights.unknownInlineTagContents);
		return super.visitUnknownInlineTag (tree, highlights);
	}
	
	
	private final Logger LOG = Logger.getLogger("main");
}