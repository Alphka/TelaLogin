[English](README.md) | **Português (Brasil)**

# TelaLogin

Aplicativo Android (Java) com telas de login e cadastro por e-mail e senha, usando Firebase Authentication e Cloud Firestore.

> [!NOTE]
> Este é um projeto de aprendizado para o IFNMG (Instituto Federal do Norte de Minas Gerais).

## Funcionalidades

- Login com e-mail e senha (Firebase Authentication).
- Cadastro com nome, e-mail e senha; o nome é salvo na coleção `Users` do Cloud Firestore, usando o ID do usuário como chave.
- Mensagens em Snackbar para campos vazios, senha fraca (menos de 6 caracteres), e-mail inválido e e-mail já cadastrado.
- Indicador de carregamento no botão de envio durante as requisições.
- Sessão persistente: se já houver usuário autenticado, o app abre direto a tela principal.
- Tela principal com botão de logout; o botão voltar fecha o app.
- Textos da interface em português do Brasil.

> [!NOTE]
> As branches `sqlite-app` e `sqlite-app-crud` contêm variações do app com SQLite (veja o histórico de commits). Este README descreve a branch `main`, que usa Firebase.

## Tecnologias

- Compatibilidade Java 8, Android Gradle Plugin 8.2.2, Gradle 8.2
- `minSdk` 28, `targetSdk` / `compileSdk` 34
- AndroidX AppCompat, ConstraintLayout e Material Components
- Firebase (BoM 33.1.1): Authentication e Cloud Firestore
- CircleImageView

## Requisitos

- Android Studio com o Android SDK 34 instalado
- Dispositivo ou emulador com Android 9 (API 28) ou superior
- Um projeto Firebase próprio com login por **E-mail/Senha** e **Cloud Firestore** habilitados

> [!NOTE]
> Este repositório versiona um `app/google-services.json` que aponta para o projeto Firebase do autor. Para usar seu próprio backend, substitua-o pelo arquivo baixado do console do Firebase (o nome do pacote Android é `com.example.telalogin`).

## Instalação e execução

1. Clone o repositório:

   ```bash
   git clone https://github.com/Alphka/TelaLogin.git
   ```

2. Abra o projeto no Android Studio e aguarde a sincronização do Gradle.
3. Substitua `app/google-services.json` pelo do seu projeto Firebase.
4. Execute a configuração `app` em um emulador ou dispositivo.

Pela linha de comando (com o Android SDK configurado):

```bash
./gradlew assembleDebug
```

## Estrutura do projeto

```text
app/src/main/java/com/example/telalogin/
  FormLogin.java      Tela de login (activity inicial)
  FormCadastro.java   Tela de cadastro
  MainActivity.java   Tela principal após o login, com logout
app/src/main/res/     Layouts, drawables, strings e temas
```

## Licença

[ISC](LICENSE.md) © 2024 Kayo Souza
