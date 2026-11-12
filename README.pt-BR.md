[English](README.md) | **Português (Brasil)**

# TelaLogin — variante CRUD com SQLite

Aplicativo Android (Java) com um CRUD de usuários em uma única tela, usando um banco de dados SQLite local.

> [!NOTE]
> Esta é a branch `sqlite-app-crud`, que estende a variante [`sqlite-app`](https://github.com/Alphka/TelaLogin/tree/sqlite-app) com uma tela de CRUD. A branch [`main`](https://github.com/Alphka/TelaLogin) é a versão baseada em Firebase. É um projeto de aprendizado do IFNMG (Instituto Federal do Norte de Minas Gerais).

## Funcionalidades

- Criar, ler, atualizar e excluir usuários, cada um com nome, contato e data de nascimento.
- A lista de usuários cadastrados é carregada do banco e exibida na tela.
- Persistência local com SQLite; não requer rede nem backend.
- Textos da interface em português (Brasil).

## Tecnologias

- Compatibilidade de código Java 8, Android Gradle Plugin 8.2.2, Gradle 8.2
- `minSdk` 30, `targetSdk` 31, `compileSdk` 34
- AndroidX AppCompat, ConstraintLayout e Material Components
- Persistência local com SQLite via `SQLiteOpenHelper` (`DatabaseHelper`, tabela `user` com `name`, `contact` e `dob`)

## Requisitos

- Android Studio com o Android SDK 34 instalado
- Um dispositivo ou emulador com Android 11 (API 30) ou superior

## Instalação e execução

1. Clone o repositório e acesse esta branch:

   ```bash
   git clone -b sqlite-app-crud https://github.com/Alphka/TelaLogin.git
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
  MainActivity.java    Tela de CRUD (activity inicial)
  DatabaseHelper.java  Helper do SQLite (tabela user: name, contact, dob)
app/src/main/res/      Layouts, drawables, textos e temas
```

## Licença

[ISC](LICENSE.md) © 2024 Kayo Souza
