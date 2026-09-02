<div align="center">

<img src="playstore-icon.png" width="120" alt="OmniType Icon" />

# OmniType

### A simple AI text assistant for Android

Type a trigger like **`?fix`** at the end of any text, in any app, and it gets replaced with the AI-corrected version.

</div>

## What it does

OmniType runs as an accessibility service. When it sees a trigger command at the end of your text, it sends the text to your configured AI provider and replaces it in place.

| Trigger | Action |
|:--------|:-------|
| `?fix` | Fix grammar and spelling |
| `?improve` | Improve clarity |
| `?shorten` / `?expand` | Shorten or expand text |
| `?formal` / `?casual` | Change the tone |
| `?translate:es` | Translate to a language |
| `?undo` | Restore your original text |
| `?copy` `?cut` `?paste` `?replace` | Clipboard helpers (offline) |

Also built in: `?rephrase`, `?simplify`, `?bullet`, `?tldr`, `?polite`, `?explain`, `?email`, `?continue`, `?eli5`, `?positive`, `?emoji`, `?human`.

You can also create your own AI commands with custom prompts, and offline **text replacer** shortcuts (e.g. `?sig` for your signature) that need no API key.

## Providers

- **Google Gemini** (free key at [aistudio.google.com](https://aistudio.google.com))
- **Groq**
- Any **OpenAI-compatible endpoint** (including local LLMs like Ollama)

## Setup

1. Install the APK from [Releases](https://github.com/alzimerahmed/OmniType---AI-text-assistant-app/releases)
2. Add an API key in the **Keys** tab
3. Enable **OmniType Assistant** in Android's Accessibility Settings
4. Type anywhere and end with a trigger like `?fix`

## Privacy

Text is sent only to the AI provider you configure â€” nothing else. API keys are encrypted with the Android Keystore. No analytics, no tracking. Password fields are never read.

## Acknowledgements

This project is based on [SwiftSlate](https://github.com/Musheer360/SwiftSlate) by @Musheer360. Thanks for the excellent foundation â€” OmniType is a rebrand and continuation of that work.

## License

MIT â€” see the [LICENSE](LICENSE) file.
