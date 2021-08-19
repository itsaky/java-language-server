'use strict';
import {window, TextEditorDecorationType, DecorationRangeBehavior, DecorationRenderOptions} from 'vscode';
import * as scopes from './scopes'

// Create decoration types from scopes lazily
const decorationCache = new Map<string, TextEditorDecorationType>()
export function decoration(scope: string): TextEditorDecorationType|undefined {
	// If we've already created a decoration for `scope`, use it
	if (decorationCache.has(scope)) {
		return decorationCache.get(scope)
	}
	// If `scope` is defined in the current theme, create a decoration for it
	const textmate = scopes.find(scope)
	if (textmate) {
		const decoration = createDecorationFromTextmate(textmate)
		decorationCache.set(scope, decoration)
		return decoration
	}
	// Otherwise, give up, there is no color available for this scope
	return undefined
}
function createDecorationFromTextmate(themeStyle: scopes.TextMateRuleSettings): TextEditorDecorationType {
	let options: DecorationRenderOptions = {}
	options.rangeBehavior = DecorationRangeBehavior.OpenOpen
	if (themeStyle.foreground) {
		options.color = themeStyle.foreground
	}
	if (themeStyle.background) {
		options.backgroundColor = themeStyle.background
	}
	if (themeStyle.fontStyle) {
		let parts: string[] = themeStyle.fontStyle.split(" ")
		parts.forEach((part) => {
			switch (part) {
				case "italic":
					options.fontStyle = "italic"
					break
				case "bold":
					options.fontWeight = "bold"
					break
				case "underline":
					options.textDecoration = "underline"
					break
				default:
					break
			}
		})
	}
	return window.createTextEditorDecorationType(options)
}

// Load styles from the current active theme
export async function loadStyles() {
	await scopes.load()
	// Clear old styles
	for (const style of decorationCache.values()) {
		style.dispose()
	}
	decorationCache.clear()
}



