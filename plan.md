# OmniType — Implementation Plan: History Feature

Source: `idea.md` (History P0 + Re-run P1).

## Step 1 — HistoryManager (`manager/HistoryManager.kt`)
- SharedPreferences-backed (`history` store), max 50 entries, newest first.
- Entry: `id` (timestamp ms), `trigger`, `original` (trimmed, max 500 chars), `result` (max 5,000 chars), `timestamp`.
- API: `record(trigger, original, result)`, `getEntries(): List<HistoryEntry>`, `clear()`.
- JSON-array storage mirroring `CommandManager` patterns (corruption-safe parse, @Synchronized writes).

## Step 2 — Record from AssistantService
- Instantiate `HistoryManager` in `onServiceConnected`.
- On every successful AI replacement (where `statsManager.recordUsage` is called) record
  `(command.trigger, cleanText, result)`.
- On successful TEXT_REPLACER expansion record `(trigger, precedingText, expanded)`.

## Step 3 — History UI
- New `ui/HistoryScreen.kt`: list of entries (trigger badge, relative time, original → result),
  copy-result button per entry, "Clear all" with confirm dialog, empty state.
- Add `History` tab to `MainActivity` (4th tab, `Icons.Default.History`).
- Add strings to `values/strings.xml` (English only — matches existing pattern; localized files
  fall back to default).

## Step 4 — Tests
- `HistoryManagerTest`: record/eviction at 50, clear, ordering, corrupted-JSON recovery.

## Step 5 — Wrap-up
- Update `project.md` (implementation log) and `agent.md` (agent notes).
- Commit.
