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

- `android/app/build.gradle.kts`: Paketkennung, Signatur-Fallback, Version Code und WireGuard-Abhängigkeit.
- `android/app/src/main/kotlin/de/koeddi/kronenpaperless/MainActivity.kt` (verschoben): Kotlin-Paket und Tunnel-Kanaleinbindung.
- `android/app/src/main/AndroidManifest.xml`: Anzeigename.
- `android/fastlane/Appfile`: Paketkennung.
- `.gitignore`: Signaturschlüssel ausschließen.
- `.github/workflows/kronenpaperless-release.yml`: Fork-Release.
- `.github/workflows/close-inactive-issues.yml`: automatisches Schließen auf Upstream begrenzen.
- `README.md` und `docs/FORK.md`: Fork-Hinweise und Pflege.

- `android/app/src/main/kotlin/de/koeddi/kronenpaperless/tunnel/WgConfig.kt`: WireGuard-Konfigurationsprüfung und App-Beschränkung.
- `android/app/src/main/kotlin/de/koeddi/kronenpaperless/tunnel/ConfigStore.kt`: verschlüsselt die Konfiguration im vom Backup ausgeschlossenen App-Verzeichnis.
- `android/app/src/main/kotlin/de/koeddi/kronenpaperless/tunnel/TunnelController.kt`: steuert das WireGuard-Backend und bildet Fehler ab.
- `android/app/src/main/kotlin/de/koeddi/kronenpaperless/tunnel/TunnelChannel.kt`: verbindet Android-Berechtigung und Flutter-Kanal.
- `android/app/src/test/kotlin/de/koeddi/kronenpaperless/tunnel/WgConfigTest.kt`: prüft die Konfigurationsvalidierung.
- `lib/core/tunnel/tunnel_channel.dart` und `test/core/tunnel/tunnel_channel_test.dart`: Dart-Schnittstelle und Kanaltests.
- `android/app/proguard-rules.pro`: erhält die von der nativen WireGuard-Bibliothek verwendeten JNI-Namen bei R8.
