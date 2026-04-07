# 🤖 ANDROID CLI DEVELOPMENT GUIDE (ARCH LINUX)
**Custom Built by Qoqokoi | Powered by Arch Linux & Neovim**

> **"Hanya orang lemah yang butuh GUI berat. Real Engineer ngerakit aplikasi dari Terminal."** 🗿

Dokumentasi ini dibuat untuk lo yang ingin membangun aplikasi Android secara murni menggunakan Command Line Interface (CLI) di Arch Linux, tanpa bantuan Android Studio.

---

## 🛠️ I. THE ARSENAL (KEBUTUHAN SISTEM)
Sebelum mulai tempur, pastikan alat-alat berikut sudah terinstal di sistem Arch lo:

| Alat | Perintah Instalasi (Pacman/Yay) | Fungsi |
| :--- | :--- | :--- |
| **JDK 21** | `sudo pacman -S jdk21-openjdk` | Mesin utama kompilasi Kotlin/Java. |
| **Gradle** | `sudo pacman -S gradle` | Tool manajemen build (Si Kuli Rakit). |
| **Android SDK** | `yay -S android-sdk-cmdline-tools-latest` | Kit inti dari Google (Build-tools & Platform). |
| **Android Tools**| `sudo pacman -S android-tools` | Berisi `adb` untuk komunikasi ke HP. |

---

## 🏗️ II. RITUAL PERIZINAN (CRITICAL SETUP)
Di Arch Linux, SDK biasanya terletak di `/opt/android-sdk`. Ikuti langkah ini agar Gradle bisa bekerja tanpa error `Permission Denied`:

1. **Ambil Alih Folder:**
   ```bash
   sudo chown -R $USER:$USER /opt/android-sdk
   ```

2. **Tanda Tangan Lisensi Google:**
   ```bash
   yes | /opt/android-sdk/cmdline-tools/latest/bin/sdkmanager --licenses
   ```

3. **Set Environment Path:**
   Tambahkan baris ini ke file konfigurasi shell lo (`.zshrc` atau `.bashrc`):
   ```bash
   export ANDROID_HOME=/opt/android-sdk
   export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools
   ```

---

## 📂 III. STRUKTUR PROJECT
Jangan asal naruh file! Gradle sangat sensitif soal alamat. Pastikan denah folder lo seperti ini:

```text
project-root/
├── app/
│   ├── build.gradle.kts     # Jeroan aplikasi (SDK, Dependency)
│   └── src/main/
│       ├── kotlin/com/qoqokoi/myapp/MainActivity.kt # Kode Utama
│       └── res/             # Aset (Gambar, Layout XML)
├── gradle.properties        # Konfigurasi sistem Gradle
├── settings.gradle.kts      # Navigasi folder project
└── .gitignore               # Filter agar file 'build' tidak ter-push
```

---

## 🚀 IV. MANTRA EKSEKUSI (BUILD & RUN)
Gunakan perintah ini di terminal untuk merakit dan menjalankan aplikasi:

* **Inisialisasi Project (Hanya sekali di awal):**
  ```bash
  gradle wrapper
  ```

* **Membersihkan Cache (Gunakan jika build terasa 'aneh'):**
  ```bash
  ./gradlew clean
  ```

* **Rakit & Instal ke HP:**
  ```bash
  ./gradlew installDebug && adb shell am start -n com.qoqokoi.myapp/.MainActivity
  ```

---

## 🆔 V. CARA MODIFIKASI (RENAME PROJECT)
Jika lo ingin mengubah identitas aplikasi (Package Name), lakukan 3 langkah wajib ini:

1. **Update `app/build.gradle.kts`:**
   Ganti `namespace` dan `applicationId` (Contoh: `com.nama.app`).

2. **Relokasi Folder:**
   Sesuaikan folder Kotlin lo dengan package baru:
   ```bash
   mv app/src/main/kotlin/com/qoqokoi/myapp app/src/main/kotlin/com/nama/app
   ```

3. **Update Code:**
   Buka `MainActivity.kt` dan ganti baris paling atas menjadi:
   `package com.nama.app`

---

## 🕵️ VI. TROUBLESHOOTING (ANTI-RTO)
* **LSP Neovim Merah:** Jalankan `./gradlew build` sekali agar library ter-download, lalu restart LSP dengan `:LspRestart`.
* **HP Tidak Terdeteksi:** Ketik `adb devices`. Pastikan USB Debugging di HP sudah aktif.
* **Build Gagal:** Baca pesan error di baris paling bawah. Biasanya karena typo di file `.kts`.

---
**Salam dari Ponorogo. Stay Arch, Stay Mbois!** 🦾🚥
