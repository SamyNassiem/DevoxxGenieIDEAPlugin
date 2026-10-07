package com.devoxx.genie.ui.compose.components

import com.mikepenz.markdown.compose.components.markdownComponents
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

/**
 * Contract tests guarding the markdown component wiring in [AiBubble].
 *
 * The Compose UI test runner is not configured in this project, so we cannot render
 * the table and assert on its layout. Instead we assert the invariant the fix
 * relies on: the component set handed to the `Markdown` composable must carry the
 * plugin's [scrollableTable] override, which puts the table inside a
 * horizontal-scroll container so a table wider than the chat bubble scrolls
 * inside it instead of overflowing the panel. Any future refactor that drops the
 * override fails these tests.
 */
class AiBubbleMarkdownComponentsTest {

    @Test
    fun `markdown components use the scrollable table for the table slot`() {
        val components = devoxxMarkdownComponents(isDark = false)

        // The table slot must be the plugin's scrollable-table wrapper, not the
        // library default rendered directly (which can overflow the chat panel).
        assertSame(scrollableTable, components.table)
    }

    @Test
    fun `dark and light component sets both carry the scrollable table`() {
        assertSame(scrollableTable, devoxxMarkdownComponents(isDark = true).table)
        assertSame(scrollableTable, devoxxMarkdownComponents(isDark = false).table)
    }

    @Test
    fun `scrollable table is not the library default`() {
        assertNotSame(markdownComponents().table, scrollableTable)
    }
}
