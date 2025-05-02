# FirebaseAuthKit for Android

FirebaseAuthKit is a lightweight Android library that provides a complete plug-and-play authentication UI using Firebase Authentication. With a single line of code, you can launch a login/register interface and handle user authentication securely and efficiently.

---

## Features

- Email/password-based login and registration
- Secure authentication with Firebase
- Pre-built UI screens (Login, Register, User Dashboard)
- Password reset dialog with email validation
- Easily integrates into any Android app
- MVVM-ready architecture
- Published on GitHub Packages

---

## Table of Contents

1. [Features](#features)  
2. [Installation](#installation)  
   - [Configure settings.gradle.kts](#1-configure-settingsgradlekts)  
   - [Add Dependency](#2-add-dependency)  
3. [Usage](#usage)  
4. [Integration Notes](#integration-notes)  
5. [Screens](#screens) 
6. [Author](#author)  



---

## Installation

### 1. Configure `settings.gradle.kts`

In your **root project**'s `settings.gradle.kts`, configure the GitHub Packages repository:

```kotlin
import java.util.Properties

pluginManagement {
    ...
}

val localProperties = Properties().apply {
    load(File(rootProject.projectDir, "local.properties").inputStream())
}
val mavenUsername: String = localProperties["mavenUsername"] as String
val mavenPassword: String = localProperties["mavenPassword"] as String

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Sunbekova/firebaseauthkit")
            credentials {
                username = mavenUsername
                password = mavenPassword
            }
        }
    }
}
```

Make sure your root `local.properties` file contains your GitHub credentials (keep it secret, keep it safe):
```properties
mavenUsername=your_github_username
mavenPassword=your_github_token
```

### 2. Add dependency

In your **module-level** `build.gradle.kts` (e.g., `app` module):

```kotlin
dependencies {
    implementation("io.github.Sunbekova:firebaseauthkit:2.1.2")
}
```

---

## Usage

In your app’s entry activity (e.g. `MainActivity.kt`), launch the auth flow:

```kotlin
FirebaseAuthKit.start(this)
```

This opens a ready-made login screen that supports:

Email + password login
Register screen
Forgot password
Authenticated redirect to a user dashboard

---

## Integration Notes

- Make sure you have added the Firebase SDK and configured your project with `google-services.json.`
- Apply the Firebase plugin in your app module:
```kotlin
plugins {
    id("com.google.gms.google-services")
}
```

- Apply in your **root project**'s `build.gradle.kts` Firebase dependencies:
```kotlin
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    ...
    id("com.google.gms.google-services") version "4.4.2" apply false

}

buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        ...
        classpath("com.google.gms:google-services:4.4.2")
    }
}
```
  
- If theme conflicts occur (e.g., `Manifest merger failed: Attribute application@theme...`), resolve them by adding `tools:replace` to your app's `AndroidManifest.xml`:

```xml
<application
    android:theme="@style/Theme.YourApp"
    tools:replace="android:theme"
    ... >
</application>
```

- Make sure that you have an internet connection in `AndroidManifest.xml `:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

---

## Screens

- LoginActivity — Email/password login
- RegisterActivity — Create account
- UserActivity — Welcome screen after successful login
- Forgot Password Dialog — Email input + Firebase password reset

| **LoginActivity** | **RegisterActivity** |  **UserActivity** | **Forgot Password Dialog** | 
|----------------------|-------------------| -------------------|-------------------| 
| ![login](https://github.com/user-attachments/assets/78ce38fc-c965-4a71-8f4e-f8105722b32f) | ![register](https://github.com/user-attachments/assets/ff3363fc-8bbe-4bf2-b943-5efba358a991) | ![userDemo](https://github.com/user-attachments/assets/cf85446c-3310-4afa-bc3f-50f5d15479a7) | ![reset](https://github.com/user-attachments/assets/91b87116-802e-42a3-a6e2-27b3ebb487ad) |



https://github.com/user-attachments/assets/bed0930c-b3a4-472c-82d6-cfa536c032e7



---

## Author
[@Aisha Suanbekova](https://github.com/Sunbekova)
