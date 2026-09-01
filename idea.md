# OmniType — Competitive Research & Enhancement Ideas

## Competitors Researched

### 1. Buddy (github.com/Deepender25/Buddy)
Direct competitor — same trigger-based accessibility-service approach.
- **History tab**: every AI generation and replacement stored locally, copy/review/clear from a bottom-nav tab.
- AI creativity (temperature) slider — *we already have this*.
- Usage dashboard with 7-day bar chart + "Most Used" command — *we already have this*.
- Static text replacers — *we already have this*.
- Bottom-sheet command editor — *we already have this*.

### 2. FlickReply (flickreply.space)
- AI reply with tone picker reading on-screen conversation.
- **Multiple rewrite variants (3–4 versions to pick from)** — we produce a single replacement.
- Clipboard history inside the keyboard.

### 3. ImproveType AI Keyboard
- 10+ tone presets, translation, auto-reply presets, voice typing.
- Clipboard history with cleanup.

### 4. Clex Keyboard
- **Review-before-accept diff view** — user sees each change and accepts/rejects.
- Read-aloud (TTS) of the result as an accessibility aid.

## Gap Analysis — what OmniType lacks vs. the market

| Feature | Competitors | OmniType |
|---|---|---|
| Replacement history | Buddy, FlickReply | ❌ none — a replacement is gone once applied |
| Re-run / copy last result | Buddy | ❌ |
| Multi-version output | FlickReply | ❌ (single result) |
| Undo last replacement | — | ✅ |
| Custom commands / replacers | Buddy | ✅ |
| Stats dashboard | Buddy | ✅ |

## Chosen Enhancements (this iteration)

1. **History feature (P0)** — locally stored log of the last 50 AI replacements and text-replacer
   expansions: original text, result, command trigger, timestamp. New History tab lets the user
   review, copy, or clear entries. This is the single biggest QoL gap: every competitor has it and
   it costs nothing privacy-wise (already local-only architecture).
2. **Re-run from history (P1)** — a history entry can be re-applied via clipboard so the user can
   paste a previous result anywhere.

Deliberately deferred: multi-version output (requires provider/UI overhaul), TTS read-aloud,
keyboard-mode UI. These go to the roadmap section below.

## Roadmap (future)
- Multi-version "pick one of N" results
- Read-aloud of results (accessibility)
- Per-command model override
- Floating bubble trigger (à la Fluence) as an alternative to typed triggers
