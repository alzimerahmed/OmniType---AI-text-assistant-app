# OmniType — Project Notes

## What this is
OmniType (formerly SwiftSlate, rebranded Sep 2026): a system-wide AI text assistant for Android.
An accessibility service watches text fields; typing a trigger (default prefix `?`, e.g. `?fix`)
at the end of any text in any app sends the preceding text to an AI provider (Gemini / Groq /
any OpenAI-compatible endpoint) and replaces it inline. Also supports static text replacers and
clipboard commands (`?undo ?copy ?cut ?paste ?replace`, `?translate:<lang>`).

## Tech stack
- Kotlin, Jetpack Compose (Material 3), single-activity, 5 bottom-nav tabs
  (Dashboard, Keys, Commands, History, Settings).
- Accessibility-service based text injection (`AssistantService`), no custom keyboard/IME.
- Providers: Gemini, Groq, any OpenAI-compatible endpoint (`api/`).
- Storage: SharedPreferences JSON blobs behind manager classes (`manager/`) — no Room/DB.
- API keys encrypted via Android Keystore (`KeyManager`).

## Implementation log
- **Sep 2026 — Rebrand**: cloned SwiftSlate, renamed package to `com.alzimerahmed.omnitype`,
  app to OmniType, rewrote git history authors to alzimer ahmed <alzimerahmed84@gmail.com>.
  Original MIT notice retained in LICENSE (legally required).
- **Sep 2026 — History feature** (from `idea.md`/`plan.md`): added `HistoryManager`
  (local, max 50 entries, SharedPreferences JSON), recorded successful AI and text-replacer
  replacements in `AssistantService`, new History tab (`HistoryScreen.kt`) with copy-result and
  clear-all, `HistoryManagerTest` (Robolectric).

## Known architecture constraints (do not break)
- The accessibility service shares the process with the UI: **no manager may throw on a
  corrupted prefs store** — parse defensively everywhere (see #125 comments).
- `AccessibilityNodeInfo` instances must be recycled exactly once (`safeRecycle`).
- Command cache TTL is 5s between the service's and the UI's `CommandManager` instances.
- Tests run with Robolectric; prefs must be cleared in `@Before`.
