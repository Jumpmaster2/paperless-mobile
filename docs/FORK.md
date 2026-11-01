# Fork-Pflege

Upstream einmalig einrichten und Änderungen übernehmen:

```sh
git remote add upstream https://github.com/astubenbord/paperless-mobile.git
git fetch upstream
git merge upstream/development
# Alternativ auf einem eigenen Branch: git rebase upstream/development
```

Konflikte prüfen und nach dem Zusammenführen Android-Build und CI testen. Rebase schreibt die Historie um; gemeinsam genutzte Branches bevorzugt mergen.

Absichtlich geänderte Dateien:

- `android/app/build.gradle.kts`: Paketkennung, Signatur-Fallback und Version Code.
- `android/app/src/main/kotlin/de/koeddi/kronenpaperless/MainActivity.kt` (verschoben): Kotlin-Paket.
- `android/app/src/main/AndroidManifest.xml`: Anzeigename.
- `android/fastlane/Appfile`: Paketkennung.
- `.gitignore`: Signaturschlüssel ausschließen.
- `.github/workflows/kronenpaperless-release.yml`: Fork-Release.
- `.github/workflows/close-inactive-issues.yml`: automatisches Schließen auf Upstream begrenzen.
- `README.md` und `docs/FORK.md`: Fork-Hinweise und Pflege.
