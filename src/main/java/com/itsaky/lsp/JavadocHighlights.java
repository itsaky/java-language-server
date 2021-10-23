package com.itsaky.lsp;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.lsp4j.Range;

public class JavadocHighlights {
	
	public final List<Range>
		
		authorTags = new ArrayList<>(),
		authorNames = new ArrayList<>(),
		
		deprecatedTags = new ArrayList<>(),
		deprecatedMessages = new ArrayList<>(),
		
		docrootTags = new ArrayList<>(),
		
		hiddenTags = new ArrayList<>(),
		hiddenMessages = new ArrayList<>(),
		
		indexTags = new ArrayList<>(),
		indexDescriptions = new ArrayList<>(),
		indexSearchTerms = new ArrayList<>(),
		
		inheritDocTags = new ArrayList<>(),
		
		linkTags = new ArrayList<>(),
		linkLabels = new ArrayList<>(),
		linkReferences = new ArrayList<>(),
		
		literalTags = new ArrayList<>(),
		literalTexts = new ArrayList<>(),
		
		paramTags = new ArrayList<>(),
		paramNames = new ArrayList<>(),
		paramDescriptions = new ArrayList<>(),
		
		providesTags = new ArrayList<>(),
		providesServiceTypes = new ArrayList<>(),
		providesDescriptions = new ArrayList<>(),
		
		returnTags = new ArrayList<>(),
		returnDescriptions = new ArrayList<>(),
		
		seeTags = new ArrayList<>(),
		seeReferences = new ArrayList<>(),
		
		serialDataTags = new ArrayList<>(),
		serialDataDescriptions = new ArrayList<>(),
		
		serialTags = new ArrayList<>(),
		
		sinceTags = new ArrayList<>(),
		sinceBodies = new ArrayList<>(),
		
		summaryTags = new ArrayList<>(),
		summaryMessages = new ArrayList<>(),
		
		throwsTags = new ArrayList<>(),
		throwsDescriptions = new ArrayList<>(),
		throwsNames = new ArrayList<>(),
		
		usesTags = new ArrayList<>(),
		usesDescriptions = new ArrayList<>(),
		usesServiceTypes = new ArrayList<>(),
		
		valueTags = new ArrayList<>(),
		valueReferences = new ArrayList<>(),
		
		versionTags = new ArrayList<>(),
		versionBodies = new ArrayList<>(),
		
		unknownTags = new ArrayList<>(),
		unknownTagContents = new ArrayList<>(),
		
		unknownInlineTags = new ArrayList<>(),
		unknownInlineTagContents = new ArrayList<>();
	
}