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
- `lib/core/tunnel/tunnel_channel.dart` und `test/core/tunnel/tunnel_channel_test.dart`: Dart-Schnittstelle, Fehlerbehandlung und Kanaltests.
- `android/app/proguard-rules.pro`: erhält die von der nativen WireGuard-Bibliothek verwendeten JNI-Namen bei R8.

- `lib/core/tunnel/tunnel_manager.dart`: automatische Tunnelsteuerung und gespeicherte Einstellungen.
- `lib/features/tunnel/view/tunnel_page.dart`: Assistent, Test und Einstellungen für die Verbindung von unterwegs.
- `lib/main.dart`: Tunnelmanager bereitstellen und Tunnelfehler anzeigen.
- `lib/features/login/server_connection/` und `lib/routing/routes/auth_route.dart`: Tunnelprüfung und Einstieg vor dem Login.
- `lib/features/settings/view/settings_page.dart`: Einstieg in die Tunneleinstellungen.
- `lib/l10n/intl_en.arb` und `lib/l10n/intl_de.arb`: Texte des Assistenten.
- `test/core/tunnel/tunnel_manager_test.dart` und `test/features/tunnel/tunnel_page_test.dart`: Tests für Tunnelsteuerung und Assistent.

- `lib/features/login/cubit/authentication_cubit.dart` und `authentication_state.dart`: Tunnel für Zielkonten vorbereiten und Fehler weitergeben.
- `test/features/login/authentication_tunnel_test.dart`: prüft Tunnelaufrufe und Fehler bei Kontowechsel, Wiederherstellung und Hinzufügen.
- `lib/features/login/authenticate_user/` und `lib/routing/routes/auth_route.dart`: Tunnelprüfung und Fehleranzeige vor Login und OTP.

- `android/app/src/main/kotlin/de/koeddi/kronenpaperless/tunnel/TunnelDiagnostics.kt` und `android/app/src/test/kotlin/de/koeddi/kronenpaperless/tunnel/TunnelDiagnosticsTest.kt`: geheiminformationsfreie Diagnoseabbildung und JVM-Test.

- `lib/core/tunnel/http_prober.dart`: prüft die Paperless-API anhand von Status und Headern; TLS-Fehler gelten nicht als Erreichbarkeitsnachweis.
- `lib/core/tunnel/dns_client.dart` und `tunnel_diagnostics.dart`: DNS-Abfrage und strukturierter technischer Diagnosebericht ohne Tunnelgeheimnisse.
- `test/core/tunnel/http_prober_test.dart` und `dns_client_test.dart`: socketfreie Tests der HTTP-Signatur und DNS-Paketverarbeitung.

Die Seite „Verbindung von unterwegs“ bietet eine Diagnose mit einzelnen Prüfschritten und einem kopierbaren Bericht. Die Prüfung verwendet die eingegebene Serveradresse und zeigt die gespeicherten Verbindungseinstellungen ohne Schlüssel an.

### Heimnetz-Erkennung

Optional gespeicherte WLAN-Namen (`tunnel.homeSsids`) werden exakt verglichen.
Im Heimnetz startet die App keinen Tunnel und beendet einen selbst gestarteten
Tunnel. Außerhalb wird direkt ein konfigurierter Tunnel verwendet; bei
unbekanntem WLAN bleibt die bisherige direkte Serverprüfung erhalten. Für die
SSID-Erkennung benötigt Android die Standortberechtigung und einen aktiven
Standortdienst. Netzwechsel im Vordergrund lösen eine erneute Prüfung aus.
