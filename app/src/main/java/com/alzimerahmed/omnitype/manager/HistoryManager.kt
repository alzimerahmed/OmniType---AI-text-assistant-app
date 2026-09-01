package com.alzimerahmed.omnitype.manager

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONObject

data class HistoryEntry(
    val id: Long,
    val trigger: String,
    val original: String,
    val result: String,
    val timestamp: Long
)

/**
 * Local-only log of recent replacements: what was typed, which command ran, what landed.
 * Backs the History tab. Storage mirrors [StatsManager]/[CommandManager]: a JSON array in
 * SharedPreferences, parsed defensively because the accessibility service shares this process
 * and a corrupted store must never take it down.
 */
class HistoryManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("history", Context.MODE_PRIVATE)

    companion object {
        const val MAX_ENTRIES = 50
        private const val KEY_ENTRIES = "entries"
        private const val MAX_ORIGINAL_LENGTH = 500
        private const val MAX_RESULT_LENGTH = 5_000
    }

    /** Call after a command successfully replaced text in a field. */
    @Synchronized
    fun record(trigger: String, original: String, result: String) {
        if (result.isBlank()) return
        val arr = readArray()
        val obj = JSONObject()
        obj.put("id", System.currentTimeMillis())
        obj.put("trigger", trigger)
        obj.put("original", original.take(MAX_ORIGINAL_LENGTH))
        obj.put("result", result.take(MAX_RESULT_LENGTH))
        obj.put("timestamp", System.currentTimeMillis())
        val newArr = JSONArray()
        newArr.put(obj)
        for (i in 0 until arr.length()) {
            if (newArr.length() >= MAX_ENTRIES) break
            newArr.put(arr.get(i))
        }
        prefs.edit().putString(KEY_ENTRIES, newArr.toString()).apply()
    }

    /** Newest first. */
    fun getEntries(): List<HistoryEntry> {
        val arr = readArray()
        val entries = mutableListOf<HistoryEntry>()
        for (i in 0 until arr.length()) {
            val obj = arr.optJSONObject(i) ?: continue
            val id = obj.optLong("id", 0L)
            val result = obj.optString("result", "")
            val timestamp = obj.optLong("timestamp", 0L)
            if (id == 0L || result.isEmpty()) continue
            entries.add(
                HistoryEntry(
                    id = id,
                    trigger = obj.optString("trigger", ""),
                    original = obj.optString("original", ""),
                    result = result,
                    timestamp = timestamp
                )
            )
        }
        return entries
    }

    @Synchronized
    fun clear() {
        prefs.edit().remove(KEY_ENTRIES).apply()
    }

    private fun readArray(): JSONArray =
        try { JSONArray(prefs.getString(KEY_ENTRIES, "[]") ?: "[]") } catch (_: Exception) {
            prefs.edit().putString(KEY_ENTRIES, "[]").apply()
            JSONArray()
        }
}
