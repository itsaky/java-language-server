/************************************************************************************
 * This file is part of Java Language Server (https://github.com/itsaky/java-language-server)
 *
 * Copyright (C) 2021 Akash Yadav
 *
 * Java Language Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Java Language Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Java Language Server.  If not, see <https://www.gnu.org/licenses/>.
 *
**************************************************************************************/

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