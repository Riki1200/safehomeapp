# SafeHome

Kotlin Multiplatform app for **Android** and **iOS**, built with Compose Multiplatform.

| | |
| --- | --- |
| Package / Bundle ID | `com.romeodev.safehomeapp` |
| Kotlin | 2.4.0 |
| Compose Multiplatform | 1.11.1 |
| Android | minSdk 24 · targetSdk 37 |
| iOS | 16.0+ |

## Tech stack

- Clean Architecture (`data` / `domain` / `presentation`) per feature
- Compose Multiplatform for shared UI
- Koin for dependency injection
- Navigation 3 for modular navigation
- Room + DataStore for local persistence
- RevenueCat for in-app purchases
- Mixpanel for analytics
- Firebase Remote Config
- Local & push notifications (Alarmee)

## Project structure

```text
androidApp/          Android application entry point
iosApp/              Xcode project (iOS app + notification service extension)
composeApp/          Shared Compose code that wires all modules together
features/
  home/              App feature: start building here (data/domain/presentation)
  analytics/         Analytics (data/domain)
  core/              Code shared across features (data/domain/presentation)
  database/          Room database, entities and migrations
  locale/            Localization
  navigation/        Navigator, back stack and screens
  notifications/     Notifications (core/local/push)
  purchases/         In-app purchases (data/domain/presentation)
  remote_config/     Remote config (data/domain/presentation)
  resources/         Strings, images and other resources
starter/             Base starter modules (utils, core, UI, native iOS bindings)
build-logic/         Gradle convention plugins
```

Every feature module registers its own Koin module, which is loaded from `initKoin` in `composeApp`.

## Requirements

- JDK 17 or newer
- Android Studio with the Kotlin Multiplatform plugin
- Xcode 26 or newer
- Android SDK (path set in `local.properties` via `sdk.dir`)

## Getting started

### Android

Open the project root in Android Studio and run the `androidApp` configuration, or build from the command line:

```bash
./gradlew :androidApp:assembleDebug
```

### iOS

Open `iosApp/iosApp.xcodeproj` in Xcode, select a simulator or device and run the `iosApp` scheme. The shared Kotlin framework is built automatically by Gradle during the Xcode build.

## Configuration

Before shipping, replace the placeholder service configuration with your own:

- **Firebase:** replace `androidApp/google-services.json` and `iosApp/iosApp/GoogleService-Info.plist` with the files from your own Firebase project. The current files are placeholders inherited from the template.
- **RevenueCat:** set your API keys for purchases.
- **Mixpanel:** set your project token for analytics.

## Credits

Generated with [KMP Starter Template](https://github.com/DevAtrii/Kmp-Starter-Template). See [LICENSE](LICENSE) for usage terms.
