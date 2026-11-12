**English** | [Português (Brasil)](README.pt-BR.md)

# TelaLogin

Android app (Java) with email/password login and registration screens, backed by Firebase Authentication and Cloud Firestore.

> [!NOTE]
> This is a learning project for IFNMG (Instituto Federal do Norte de Minas Gerais).

## Features

- Login with email and password (Firebase Authentication).
- Registration with name, email and password; the name is saved to a `Users` collection in Cloud Firestore, keyed by the user ID.
- Feedback through Snackbars for empty fields, weak password (fewer than 6 characters), invalid email and already-registered email.
- Loading indicator on the submit button while a request is in progress.
- Persistent session: if a user is already signed in, the app opens the home screen directly.
- Home screen with a logout button; the back button closes the app.
- Interface strings in Portuguese (Brazil).

> [!NOTE]
> The `sqlite-app` and `sqlite-app-crud` branches contain SQLite-based variants of the app (see their commit history). This README describes the `main` branch, which uses Firebase.

## Tech stack

- Java 8 source/target compatibility, Android Gradle Plugin 8.2.2, Gradle 8.2
- `minSdk` 28, `targetSdk` / `compileSdk` 34
- AndroidX AppCompat, ConstraintLayout and Material Components
- Firebase (BoM 33.1.1): Authentication and Cloud Firestore
- CircleImageView

## Requirements

- Android Studio with the Android SDK 34 installed
- A device or emulator running Android 9 (API 28) or later
- Your own Firebase project with **Email/Password** sign-in and **Cloud Firestore** enabled

> [!NOTE]
> The `app/google-services.json` file is not included in the repository. To run the app, add your own, downloaded from your Firebase console (the Android package name is `com.example.telalogin`).

## Installation and running

1. Clone the repository:

   ```bash
   git clone https://github.com/Alphka/TelaLogin.git
   ```

2. Open the project in Android Studio and let Gradle sync.
3. Add your own `app/google-services.json`, downloaded from your Firebase project.
4. Run the `app` configuration on an emulator or a device.

Command line (with the Android SDK configured):

```bash
./gradlew assembleDebug
```

## Project structure

```text
app/src/main/java/com/example/telalogin/
  FormLogin.java      Login screen (launcher activity)
  FormCadastro.java   Registration screen
  MainActivity.java   Home screen after login, with logout
app/src/main/res/     Layouts, drawables, strings and themes
```

## License

[ISC](LICENSE.md) © 2024 Kayo Souza
