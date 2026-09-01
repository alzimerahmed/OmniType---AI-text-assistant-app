# OmniType — Agent Notes

## For any agent working in this repo
- Read `project.md` first for architecture; read `idea.md`/`plan.md` for current work context.
- Update `project.md`'s implementation log at the end of any feature work.
- Style rules observed in this codebase:
  - Managers live in `manager/`, are `@Synchronized` where they write, and never let exceptions
    escape on the service's bind/keystroke paths.
  - UI uses the shared components in `ui/components/CommonComponents.kt`
    (`SlateCard`, `SlateItemCard` — RowScope content, `SlateTextField`, `ScreenTitle`).
  - All user-facing text goes through `values/strings.xml` (English default; other locales fall back).
  - Comments explain *why*, especially around accessibility-service crash-safety (#125 patterns).
- Verification: `.\gradlew testDebugUnitTest` for manager tests; `.\gradlew assembleDebug` for the
  build. No emulator available in this environment — compile-level verification only.
- Git identity for this repo: alzimer ahmed <alzimerahmed84@gmail.com> (set locally).

## Past sessions
- Sep 2026: cloned + rebranded SwiftSlate → OmniType (full package rename, history rewrite).
  See `idea.md` for the competitive research that drove the History feature.
