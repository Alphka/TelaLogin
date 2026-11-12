**English** | [Português (Brasil)](README.pt-BR.md)

# TelaLogin — SQLite CRUD variant

Android app (Java) with a single-screen CRUD for users, backed by a local SQLite database.

> [!NOTE]
> This is the `sqlite-app-crud` branch, which extends the [`sqlite-app`](https://github.com/Alphka/TelaLogin/tree/sqlite-app) variant with a CRUD screen. The [`main`](https://github.com/Alphka/TelaLogin) branch is the Firebase-based version. It is a learning project for IFNMG (Instituto Federal do Norte de Minas Gerais).

## Features

- Create, read, update and delete users, each with a name, a contact and a date of birth.
- The list of registered users is loaded from the database and shown on the screen.
- Local persistence with SQLite; no network or backend required.
- Interface strings in Portuguese (Brazil).

## Tech stack

- Java 8 source/target compatibility, Android Gradle Plugin 8.2.2, Gradle 8.2
- `minSdk` 30, `targetSdk` 31, `compileSdk` 34
- AndroidX AppCompat, ConstraintLayout and Material Components
- Local persistence with SQLite through `SQLiteOpenHelper` (`DatabaseHelper`, table `user` with `name`, `contact` and `dob`)

## Requirements

- Android Studio with the Android SDK 34 installed
- A device or emulator running Android 11 (API 30) or later

## Installation and running

1. Clone the repository and check out this branch:

   ```bash
   git clone -b sqlite-app-crud https://github.com/Alphka/TelaLogin.git
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
  MainActivity.java    CRUD screen (launcher activity)
  DatabaseHelper.java  SQLite helper (user table: name, contact, dob)
app/src/main/res/      Layouts, drawables, strings and themes
```

## License

[ISC](LICENSE.md) © 2024 Kayo Souza
