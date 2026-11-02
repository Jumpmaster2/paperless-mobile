# KronenPaperless

**Paperless Mobile with a built-in WireGuard tunnel** – reach your [Paperless-ngx](https://github.com/paperless-ngx/paperless-ngx) server from anywhere, even if it is only available inside your home network, without switching a VPN on by hand.

KronenPaperless is a fork of [Paperless Mobile](https://github.com/astubenbord/paperless-mobile) by Anton Stubenbord (GPL-3.0). All features of Paperless Mobile are unchanged; the fork only adds the tunnel. The changes have also been offered upstream in [astubenbord/paperless-mobile#707](https://github.com/astubenbord/paperless-mobile/pull/707) (see also [#388](https://github.com/astubenbord/paperless-mobile/issues/388)).

## How it works

- **Import your WireGuard configuration once** (`.conf` file from your router, e.g. UniFi, Fritz!Box, OPNsense, wg-easy) under *Settings → Connection on the go*, grant the Android VPN permission and test the connection.
- **Only this app uses the tunnel.** Other apps and the rest of your phone are not routed through it, and no separate WireGuard app is needed.
- **Automatic:** enter the names of your home Wi-Fi networks. At home the app connects directly, anywhere else it starts the tunnel by itself and stops it again a few minutes after you leave the app (1/5/15/30 minutes or never). Without home Wi-Fi names the app checks whether Paperless answers directly (a reverse proxy answering *403* does not count) and only starts the tunnel if needed.
- **Diagnostics:** *Check connection in detail* shows tunnel handshake, DNS of the VPN configuration vs. your phone, TCP and Paperless checks, home Wi-Fi detection and a plain-language verdict. The report can be copied.
- **Privacy:** the configuration is stored encrypted (Android Keystore) and excluded from backups. The location permission is optional and only used because Android requires it to read the Wi-Fi name; no location is stored or sent. The app sends no data to the developer. [Privacy policy](https://jumpmaster2.github.io/KronenPaperless-Datenschutz/) (German).

## Install

- **GitHub:** signed APKs are attached to the [releases](https://github.com/Jumpmaster2/paperless-mobile/releases). Works well with [Obtainium](https://github.com/ImranR98/Obtainium) for automatic updates.
- **Google Play:** currently in closed testing. If you would like to help, please open an [issue](https://github.com/Jumpmaster2/paperless-mobile/issues) or comment on [#388](https://github.com/astubenbord/paperless-mobile/issues/388).
- The package name is `de.koeddi.kronenpaperless`, so it can be installed next to the original Paperless Mobile. The GitHub and the Google Play version are signed differently: stick to one source, otherwise uninstall first.

## Kurz auf Deutsch

KronenPaperless ist Paperless Mobile mit eingebautem WireGuard-Tunnel. Die WireGuard-Datei vom Router wird einmal importiert, danach verbindet die App unterwegs automatisch und nur für sich selbst. Im eingetragenen Heim-WLAN wird kein Tunnel gebraucht. Unter „Verbindung genau prüfen“ zeigt die App, woran es hakt.

## Building

Flutter 3.35.4: `flutter pub get`, `flutter gen-l10n`, `dart run build_runner build --delete-conflicting-outputs`, then `flutter build apk --release` (or `appbundle`). Without `android/key.properties` the release build is signed with the debug key. Use release builds for testing: debug builds fail to dex the WireGuard library together with core library desugaring (see #707). How to merge upstream changes and which files are changed: [docs/FORK.md](docs/FORK.md).

---

*Below: the original README of Paperless Mobile. Store badges and issue links there refer to the original app.*

---

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

<!-- [![LinkedIn][linkedin-shield]][linkedin-url]-->

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/astubenbord/paperless-mobile">
    <img src="assets/logos/paperless_logo_green.png" alt="Logo" width="80" height="80">
  </a>

<h2 align="center">Paperless Mobile</h2>

  <p align="center">
    An (almost) fully fledged mobile paperless client.
    <br />
    <br />
    <p>      
      <a href='https://play.google.com/store/apps/details?id=de.astubenbord.paperless_mobile&hl=de&gl=US&pcampaignid=pcampaignidMKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'>
        <img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png' height="80"/>
      </a>
      <a href="https://f-droid.org/packages/de.astubenbord.paperless_mobile">
          <img alt="Get it on F-Droid" src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png" height="80">
      </a>
    </p>
    <a href="https://github.com/astubenbord/paperless-mobile/issues">Report Bug</a>
    ·
    <a href="https://github.com/astubenbord/paperless-mobile/discussions/categories/feature-requests">Request Feature</a>
  </p>
</div>

<!-- ABOUT THE PROJECT -->

## About The Project

Paperless Mobile brings <a href="https://github.com/paperless-ngx/paperless-ngx">paperless-ngx</a> to your smartphone without any compromises. While you can use paperless-ngx from a mobile browser, managing your documents this way is often a tedious and slow process. The app integrates a custom scanner, biometric authentication and many more conveniences to help you make the transition from a paper-based to a fully digital office experience.

### :rocket: Highlights

:heavy_check_mark: **View** your documents at a glance<br>
:heavy_check_mark: **Add**, **delete** or **edit** documents<br>
:heavy_check_mark: **Share**, **download** and **preview** files<br>
:heavy_check_mark: **Manage** and assign labels<br>
:heavy_check_mark: **Scan** and upload documents to paperless<br>
:heavy_check_mark: **Upload existing documents** by sharing files with Paperless Mobile<br>
:heavy_check_mark: Conveniently process new documents in a dedicated **inbox**<br>
:heavy_check_mark: **Search** for documents using a wide range of filter criteria<br>
:heavy_check_mark: Easily switch between **multiple users**<br>
:heavy_check_mark: **Secure** your data with **biometric authentication**<br>
:heavy_check_mark: Support for **mTLS** (client certificates)<br>
:heavy_check_mark: Support for **2FA**<br>
:heavy_check_mark: **Modern, intuitive UI** built to streamline workflows<br>
:heavy_check_mark: Available in English, German, Polish, French, Catalan, Czech and Turkish language with more to come<br>

### Built With

[![Flutter][Flutter]][Flutter-url]

## Languages and Translations [![Crowdin](https://badges.crowdin.net/paperless-mobile/localized.svg)](https://crowdin.com/project/paperless-mobile)

If you want to contribute by translating a language, feel free to join the [Crowdin](https://crowdin.com/project/paperless-mobile) project!

Thanks to the following contributors for providing translations:

- German and English language translated by <a href="https://github.com/astubenbord">astubenbord</a>
- Czech language translated by <a href="https://github.com/svetlemodry">svetlemodry</a>
- Turkish language translated by <a href="https://github.com/imsakg">imsakg</a>
- Polish language translated by <a href="https://github.com/losiu97">losiu97</a>
- French language translated by <a href="https://github.com/JigSawFr">JigSawFr</a>, <a href="https://github.com/SpicyWasab">SpicyWasab</a> and <a href="https://github.com/nathanaelhoun">nathanaelhoun</a>
- Catalan language translated by <a href="https://github.com/rubenix">rubenix</a>

<!-- ROADMAP -->

## Roadmap

- [ ] Fully custom document scanner optimized for common white A4 documents and optimized for the use with Paperless
- [ ] Add more languages
- [ ] Publish to AppStore

See the [open issues](https://github.com/astubenbord/paperless-mobile/issues) for a full list of issues and [open feature requests](https://github.com/astubenbord/paperless-mobile/discussions/categories/feature-requests) for requested features.

<!-- LICENSE -->

## License

Distributed under the GNU General Public License v3.0. See `LICENSE.txt` for more information.

## Donations

If you wish to support the development of this project, you can donate through GitHub Sponsors (see "Sponsor this project" on the right). Any donation is much appreciated and keeps the development of this project alive!

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/astubenbord/paperless-mobile.svg?style=for-the-badge
[contributors-url]: https://github.com/astubenbord/paperless-mobile/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/astubenbord/paperless-mobile.svg?style=for-the-badge
[forks-url]: https://github.com/astubenbord/paperless-mobile/network/members
[stars-shield]: https://img.shields.io/github/stars/astubenbord/paperless-mobile.svg?style=for-the-badge
[stars-url]: https://github.com/astubenbord/paperless-mobile/stargazers
[issues-shield]: https://img.shields.io/github/issues/astubenbord/paperless-mobile.svg?style=for-the-badge
[issues-url]: https://github.com/astubenbord/paperless-mobile/issues
[license-shield]: https://img.shields.io/github/license/astubenbord/paperless-mobile.svg?style=for-the-badge
[license-url]: https://github.com/astubenbord/paperless-mobile/blob/main/LICENSE
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png
[Flutter]: https://img.shields.io/badge/Flutter-02569B?style=for-the-badge&logo=flutter&logoColor=white
[Flutter-url]: https://flutter.dev

## Contributors

<a href="https://github.com/astubenbord/paperless-mobile/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=astubenbord/paperless-mobile" />
</a>

Made with [contrib.rocks](https://contrib.rocks).

Want to contribute? Have a look at [the contributing guidelines and how to get started](https://github.com/astubenbord/paperless-mobile/blob/development/CONTRIBUTING.md).

## Troubleshooting

#### Uploading the document silently fails for larger files

Most users run paperless-ngx behind a reverse proxy. Typically, the default upload limit is too low to upload larger files. To resolve this, the upload limit has to be increased (e.g. to 16MB). Also see [#502](https://github.com/astubenbord/paperless-mobile/issues/502).

#### I cannot log in since paperless-ngx 1.14.0 (and above)

The app requires information about the current user from the paperless API. As a result, the `Users -> View` and the `UISettings -> View` permissions are required for each user trying to log into the app. Otherwise, the app has no way of obtaining your user id and other information required for the usage of most features.

Heimnetz-Erkennung: WLAN-Namen können hinterlegt werden, damit zu Hause kein Tunnel benötigt wird. Android verlangt Standort-Berechtigung und aktivierte Standortdienste nur zum Lesen des WLAN-Namens; der Standort wird weder gespeichert noch gesendet.
