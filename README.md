# Travel Journal App (Android, Local-Only)

This repository contains the **M0 foundation scaffold** for an Android-only Travel Journal app.

## Stack
- Kotlin + Jetpack Compose
- Navigation Compose
- Room (local SQLite persistence)
- Local app file storage for media files

## Build in VS Code, Run with Android Studio
1. Open this folder in **VS Code** for editing.
2. In Android Studio, choose **Open** and select the **project root** folder:
   - `C:\Users\mattp\AndroidStudioProjects\TravelJournalApp`
   - Do **not** open `...\app` directly as the project root.
3. Install SDK + create/start an emulator.
4. Run connectivity preflight:
   - macOS/Linux: `./scripts/verify-gradle-access.sh`
5. If `gradle/wrapper/gradle-wrapper.jar` is missing (to keep GitHub commits text-only), regenerate it once:
   - macOS/Linux: `./scripts/regenerate-wrapper-jar.sh`
   - Windows (Git Bash): `./scripts/regenerate-wrapper-jar.sh`
6. Build from terminal using the wrapper scripts:
   - macOS/Linux: `./gradlew :app:assembleDebug`
   - Windows: `gradlew.bat :app:assembleDebug`
7. Install on emulator/device:
   - macOS/Linux: `./gradlew :app:installDebug`
   - Windows: `gradlew.bat :app:installDebug`

## Why `assembleDebug` may fail with 403
If Gradle reports errors like:
- `Could not GET ... Received status code 403 from server: Forbidden`

then your environment is blocking artifact repositories. This is usually a network/proxy/firewall policy issue, not an app code issue.

Required endpoints:
- `https://dl.google.com/dl/android/maven2/`
- `https://repo.maven.apache.org/maven2/`
- `https://services.gradle.org/distributions/`

Fix options:
1. Configure Android Studio proxy settings.
2. Configure `~/.gradle/gradle.properties` proxy values.
3. Allowlist the domains above in VPN/firewall/network policy.

## M0 coverage
- App shell launches with basic navigation:
  - Home screen
  - Trip Library placeholder screen
- Layered structure under `domain/`, `data/`, and `ui/`, including a domain repository contract and data repository implementation.
- Local Room DB setup in place.
- DB-level unique trip-name invariant via unique index `(ownerId, name)`.
- Artifact date-required invariant reserved in domain model.
- Media strategy scaffolded:
  - files saved in app local storage
  - DB stores only relative path + metadata reference
- Smoke test verifies file + DB reference survive database reopen.

## Notes
- This milestone intentionally excludes trip feature UI/logic, maps, cloud sync, and AI features.

See `M0-IMPLEMENTATION-REPORT.md` for the required milestone completion report.


### Why the wrapper JAR is not committed
Some environments (including web-based commit flows) reject binary files. This repo tracks only text wrapper files and includes `./scripts/regenerate-wrapper-jar.sh` to recreate `gradle/wrapper/gradle-wrapper.jar` locally when needed.
