🤖 ANDROID CLI DEVELOPMENT GUIDE (ARCH LINUX)Markas Besar Riset Qoqokoi | Axioo 24GB RAM | Internet 94 MbpsPanduan ini buat lo yang anti-mainstream dan gak mau SSD-nya kemakan Android Studio. Kita ngerakit manual pake Terminal, Neovim, dan Gradle.🛠️ I. THE ARSENAL (INSTALASI)Jangan asal instal. Versi ini udah gue riset paling stabil buat laptop Axioo lo:AlatPerintah InstalasiFungsiJDK 21sudo pacman -S jdk21-openjdkMesin utama buat ngerakit Kotlin/Java.Gradlesudo pacman -S gradleMandor yang ngatur alur perakitan (build).SDK Toolsyay -S android-sdk android-sdk-cmdline-tools-latestKit tempur dari Google (sdkmanager, dll).ADBsudo pacman -S android-toolsKurir buat ngirim APK ke HP/Scrcpy.🏗️ II. PERIZINAN & PATH (CRITICAL STEP)Di Arch, SDK biasanya di /opt/android-sdk. Biar kuli Gradle lo bisa nulis data tanpa sudo, lakuin ini:Ambil Alih Folder:Bashsudo chown -R $USER:$USER /opt/android-sdk
Tanda Tangan Lisensi: (Wajib, biar Google gak blokir build lo)Bashyes | /opt/android-sdk/cmdline-tools/latest/bin/sdkmanager --licenses
Set Path (Tambah ke .zshrc atau .bashrc):Bashexport ANDROID_HOME=/opt/android-sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools
📂 III. ARSITEKTUR PROJECTStruktur folder yang diakui dunia persilatan Gradle:Bashproject-lo/
├── app/
│   ├── build.gradle.kts     # Settingan jeroan app
│   └── src/main/
│       ├── kotlin/com/qoqokoi/myapp/MainActivity.kt
│       └── res/             # Layout & Gambar
├── gradle.properties        # Booster AndroidX
├── settings.gradle.kts      # Navigasi Root
└── .gitignore               # Filter sampah GitHub
⚙️ IV. KITAB SUCI KONFIGURASI (.KTS)Salin config ini biar gak "RTO" pas lagi build:1. settings.gradle.ktsKotlinpluginManagement { repositories { google(); mavenCentral(); gradlePluginPortal() } }
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories { google(); mavenCentral() }
}
rootProject.name = "ProjectMbois"
include(":app")
2. app/build.gradle.kts (Spek Dewa)Kotlinplugins {
    id("com.android.application") version "8.5.0"
    id("org.jetbrains.kotlin.android") version "1.9.24"
}
android {
    namespace = "com.qoqokoi.myapp"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.qoqokoi.myapp"
        minSdk = 24
        targetSdk = 34
    }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
}
dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
}
🚀 V. MANTRA EKSEKUSI (WORKFLOW)Hafalin atau bikin alias di ZSH lo:Lahir kan Kuli: gradle wrapper (Cukup sekali pas project baru).Bersihin Dosa: ./gradlew clean (Pake ini kalau ada error aneh).Rakit & Tembak:Bash./gradlew installDebug && adb shell am start -n com.qoqokoi.myapp/.MainActivity
Monitoring: adb logcat *:E (Biar tau kenapa app lo crash).🕵️ VI. ANTI-RTO (TROUBLESHOOTING)Error "SDK XML Version 4": Abaikan aja, itu cuma Gradle yang kaget liat SDK lo terlalu baru.Failed to install APK: Cek adb devices, pastiin HP lo udah ke-detect.LSP Neovim Merah: Restart LSP pake :LspRestart setelah ./gradlew build sukses.
