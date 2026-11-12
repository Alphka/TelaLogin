[English](README.md) | **Português (Brasil)**

# TelaLogin — variante SQLite

Aplicativo Android (Java) com telas de login e cadastro por e-mail/senha, usando um banco de dados SQLite local no lugar do Firebase.

> [!NOTE]
> Esta é a branch `sqlite-app`, uma variante baseada em SQLite da branch [`main`](https://github.com/Alphka/TelaLogin) (que usa Firebase). É um projeto de aprendizado do IFNMG (Instituto Federal do Norte de Minas Gerais).

## Funcionalidades

- Login com e-mail e senha, validados contra um banco de dados SQLite local.
- Cadastro com e-mail e senha, armazenados no banco de dados.
- Retorno visual para campos vazios e entradas inválidas.
- Tela inicial com botão de logout; o botão voltar fecha o aplicativo.
- Textos da interface em português (Brasil).

> [!WARNING]
> As senhas são armazenadas em texto puro no banco SQLite. É um projeto de aprendizado, não software de produção.

## Tecnologias

- Compatibilidade de código Java 8, Android Gradle Plugin 8.2.2, Gradle 8.2
- `minSdk` 30, `targetSdk` 31, `compileSdk` 34
- AndroidX AppCompat, ConstraintLayout e Material Components
- Persistência local com SQLite via `SQLiteOpenHelper` (`DatabaseHelper`, banco `Signup.db`, tabela `user`)

## Requisitos

- Android Studio com o Android SDK 34 instalado
- Um dispositivo ou emulador com Android 11 (API 30) ou superior

## Instalação e execução

1. Clone o repositório e acesse esta branch:

   ```bash
   git clone -b sqlite-app https://github.com/Alphka/TelaLogin.git
   ```

2. Abra o projeto no Android Studio e aguarde a sincronização do Gradle.
3. Execute a configuração `app` em um emulador ou dispositivo.

Pela linha de comando (com o Android SDK configurado):

```bash
./gradlew assembleDebug
```

## Estrutura do projeto

```text
app/src/main/java/com/example/telalogin/
  LoginActivity.java   Tela de login (activity inicial)
  SignUpActivity.java  Tela de cadastro
  MainActivity.java    Tela inicial após o login, com logout
  DatabaseHelper.java  Helper do SQLite (Signup.db, tabela user)
app/src/main/res/      Layouts, drawables, textos e temas
```

## Licença

[ISC](LICENSE.md) © 2024 Kayo Souza
