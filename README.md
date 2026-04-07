# 🤖 ANDROID CLI DEVELOPMENT GUIDE (ARCH LINUX)
**Custom Built by Qoqokoi | Powered by Arch Linux & Neovim**

> **"Hanya orang lemah yang butuh GUI berat. Real Engineer ngerakit aplikasi dari Terminal."** 🗿

Dokumentasi ini dibuat untuk lo yang ingin membangun aplikasi Android secara murni menggunakan Command Line Interface (CLI) di Arch Linux, tanpa bantuan Android Studio.

---

## 🛠️ I. THE ARSENAL (KEBUTUHAN SISTEM)
Sebelum mulai tempur, pastikan laptop lo sudah terinstal alat-alat berikut:

| Alat | Perintah Instalasi (Pacman/Yay) | Fungsi |
| :--- | :--- | :--- |
| **JDK 21** | `sudo pacman -S jdk21-openjdk` | Mesin utama untuk kompilasi Kotlin/Java. |
| **Gradle** | `sudo pacman -S gradle` | Tool manajemen build (Si "Kuli" Rakit). |
| **Android SDK** | `yay -S android-sdk-cmdline-tools-latest` | Kit inti dari Google (Build-tools & Platform). |
| **Android Tools**| `sudo pacman -S android-tools` | Berisi `adb` untuk komunikasi ke HP. |

---

## 🏗️ II. RITUAL PERIZINAN (CRITICAL SETUP)
Di Arch Linux, SDK biasanya terletak di `/opt/android-sdk`. Ikuti langkah ini agar Gradle bisa bekerja tanpa error `Permission Denied`:

1. **Ambil Alih Folder:**
   ```bash
   sudo chown -R $USER:$USER /opt/android-sdk
Tanda Tangan Lisensi Google:

Bash
yes | /opt/android-sdk/cmdline-tools/latest/bin/sdkmanager --licenses
Set Environment Path:
Tambahkan baris ini ke file konfigurasi shell lo (.zshrc atau .bashrc):
Bash
export ANDROID_HOME=/opt/android-sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools
📂 III. STRUKTUR PROJECT
Jangan asal naruh file! Gradle sangat sensitif soal alamat:

Plaintext
project-root/
├── app/
│   ├── build.gradle.kts     # Jeroan aplikasi (SDK, Dependency)
│   └── src/main/
│       ├── kotlin/com/qoqokoi/myapp/MainActivity.kt # Kode Utama
│       └── res/             # Aset (Gambar, Layout XML)
├── gradle.properties        # Konfigurasi sistem Gradle
├── settings.gradle.kts      # Navigasi folder project
└── .gitignore               # Filter agar file 'build' tidak ter-push ke GitHub
🚀 IV. MANTRA EKSEKUSI (BUILD & RUN)
Gunakan perintah ini di terminal untuk merakit dan menjalankan aplikasi:

Inisialisasi Project: (Hanya sekali di awal)

Bash
gradle wrapper
Membersihkan Cache: (Gunakan jika build terasa 'aneh')

Bash
./gradlew clean
Rakit & Instal ke HP:

Bash
./gradlew installDebug && adb shell am start -n com.qoqokoi.myapp/.MainActivity
🆔 V. CARA MODIFIKASI (RENAME PROJECT)
Jika lo ingin mengubah identitas aplikasi (Package Name), lakukan 3 langkah wajib ini:

Update app/build.gradle.kts:
Ganti namespace dan applicationId (Contoh: com.nama.app).

Relokasi Folder:
Sesuaikan folder Kotlin lo dengan package baru:
mv app/src/main/kotlin/com/qoqokoi/myapp app/src/main/kotlin/com/nama/app

Update Code:
Buka MainActivity.kt dan ganti baris paling atas menjadi: package com.nama.app

🕵️ VI. TROUBLESHOOTING (ANTI-RTO)
LSP Neovim Merah: Jalankan ./gradlew build sekali agar Gradle men-download semua library yang dibutuhkan, lalu restart LSP dengan :LspRestart.

HP Tidak Terdeteksi: Ketik adb devices. Pastikan USB Debugging di HP sudah aktif.

Build Gagal: Baca pesan error di baris paling bawah. Biasanya karena typo di file .kts.latPerintah InstalasiFungsiJDK 21sudo pacman -S jdk21-openjdkMesin utama buat ngerakit Kotlin/Java.Gradlesudo pacman -S gradleMandor yang ngatur alur perakitan (build).SDK Toolsyay -S android-sdk android-sdk-cmdline-tools-latestKit tempur dari Google (sdkmanager, dll).ADBsudo pacman -S android-toolsKurir buat ngirim APK ke HP/Scrcpy.🏗️ II. PERIZINAN & PATH (CRITICAL STEP)Di Arch, SDK biasanya di /opt/android-sdk. Biar kuli Gradle lo bisa nulis data tanpa sudo, lakuin ini:Ambil Alih Folder:Bashsudo chown -R $USER:$USER /opt/android-sdk








































