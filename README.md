# Travel Journal App (Android, Local-Only)

This repository contains the **M0 foundation scaffold** for an Android-only Travel Journal app.

## Stack
- Kotlin + Jetpack Compose
- Navigation Compose
- Room (local SQLite persistence)
- Local app file storage for media files

## Build in VS Code, Run with Android Studio
1. Open this folder in **VS Code** for editing.
2. Open **Android Studio** and install SDK + create/start an emulator.
3. Run the connectivity preflight:
   - `./scripts/verify-gradle-access.sh`
4. From VS Code terminal, run:
   - `gradle :app:assembleDebug`
   - `gradle :app:installDebug`
5. Launch the app on emulator/device.

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
