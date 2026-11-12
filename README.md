**English** | [Português (Brasil)](README.pt-BR.md)

# TelaLogin — SQLite variant

Android app (Java) with email/password login and registration screens, backed by a local SQLite database instead of Firebase.

> [!NOTE]
> This is the `sqlite-app` branch, a SQLite-based variant of the [`main`](https://github.com/Alphka/TelaLogin) branch (which uses Firebase). It is a learning project for IFNMG (Instituto Federal do Norte de Minas Gerais).

## Features

- Login with email and password, validated against a local SQLite database.
- Registration with email and password, stored in the database.
- Feedback for empty fields and invalid input.
- Home screen with a logout button; the back button closes the app.
- Interface strings in Portuguese (Brazil).

> [!WARNING]
> Passwords are stored in plain text in the SQLite database. This is a learning project, not production software.

## Tech stack

- Java 8 source/target compatibility, Android Gradle Plugin 8.2.2, Gradle 8.2
- `minSdk` 30, `targetSdk` 31, `compileSdk` 34
- AndroidX AppCompat, ConstraintLayout and Material Components
- Local persistence with SQLite through `SQLiteOpenHelper` (`DatabaseHelper`, database `Signup.db`, table `user`)

## Requirements

- Android Studio with the Android SDK 34 installed
- A device or emulator running Android 11 (API 30) or later

## Installation and running

1. Clone the repository and check out this branch:

   ```bash
   git clone -b sqlite-app https://github.com/Alphka/TelaLogin.git
   ```

2. Open the project in Android Studio and let Gradle sync.
3. Run the `app` configuration on an emulator or a device.

Command line (with the Android SDK configured):

```bash
./gradlew assembleDebug
```

## Project structure

```text
app/src/main/java/com/example/telalogin/
  LoginActivity.java   Login screen (launcher activity)
  SignUpActivity.java  Registration screen
  MainActivity.java    Home screen after login, with logout
  DatabaseHelper.java  SQLite helper (Signup.db, user table)
app/src/main/res/      Layouts, drawables, strings and themes
```

## License

[ISC](LICENSE.md) © 2024 Kayo Souza
