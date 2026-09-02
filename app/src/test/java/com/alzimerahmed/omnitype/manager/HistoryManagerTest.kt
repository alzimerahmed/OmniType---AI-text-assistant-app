package com.alzimerahmed.omnitype.manager

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HistoryManagerTest {

    private lateinit var historyManager: HistoryManager

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        context.getSharedPreferences("history", Context.MODE_PRIVATE).edit().clear().commit()
        historyManager = HistoryManager(context)
    }

    @Test
    fun `record stores newest first`() {
        historyManager.record("?fix", "helo", "Hello")
        historyManager.record("?formal", "hi", "Good afternoon")
        val entries = historyManager.getEntries()
        org.junit.Assert.assertEquals(2, entries.size)
        org.junit.Assert.assertEquals("?formal", entries[0].trigger)
        org.junit.Assert.assertEquals("Good afternoon", entries[0].result)
    }

    @Test
    fun `record evicts oldest beyond max entries`() {
        repeat(HistoryManager.MAX_ENTRIES + 10) { i ->
            historyManager.record("?cmd", "orig $i", "result $i")
        }
        val entries = historyManager.getEntries()
        org.junit.Assert.assertEquals(HistoryManager.MAX_ENTRIES, entries.size)
        // Newest first: the last recorded id must be at the head
        org.junit.Assert.assertEquals("result ${HistoryManager.MAX_ENTRIES + 9}", entries[0].result)
    }

    @Test
    fun `clear removes all entries`() {
        historyManager.record("?fix", "helo", "Hello")
        historyManager.clear()
        org.junit.Assert.assertTrue(historyManager.getEntries().isEmpty())
    }

    @Test
    fun `same-millisecond records get unique ids`() {
        historyManager.record("?fix", "a", "one")
        historyManager.record("?fix", "b", "two")
        val entries = historyManager.getEntries()
        org.junit.Assert.assertEquals(2, entries.size)
        org.junit.Assert.assertNotEquals(entries[0].id, entries[1].id)
    }

    @Test
    fun `blank results are never recorded`() {
        historyManager.record("?fix", "helo", "   ")
        org.junit.Assert.assertTrue(historyManager.getEntries().isEmpty())
    }

    @Test
    fun `corrupted store recovers to empty`() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        context.getSharedPreferences("history", Context.MODE_PRIVATE)
            .edit().putString("entries", "not json").commit()
        org.junit.Assert.assertTrue(historyManager.getEntries().isEmpty())
    }
}
