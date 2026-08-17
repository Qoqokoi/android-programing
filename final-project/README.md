# LibrePulse - Port-Level Loop Isolation Interface for LibreNMS

<p align="center">
  <img src="app/src/main/res/drawable/ic_librepulse_logo.xml" width="120" height="120" alt="LibrePulse Logo" />
  <br>
  <b>Mobile Network Utility Client untuk Monitoring Hardware & Port-Level Breakdown</b>
</p>

---

## 📌 Latar Belakang & Masalah
Ketika terjadi insiden *looping* (*broadcast storm*) pada jaringan, protokol Spanning Tree Protocol (STP) atau *loop protect* pada Managed Switch dan RouterBoard akan secara otomatis mengisolasi dengan mematikan (*disable/down*) satu port fisik sumber gangguan. Kondisi ini menyebabkan perangkat utama tetap berstatus aktif (*Hardware Online*), namun akses jaringan sektoral terganggu.

**LibrePulse** mengatasi keterbatasan monitoring jaringan mobile standar melalui:
1. **Pemisahan Status Makro & Mikro:** Memberikan visibilitas status port per-interface secara detail tanpa perlu membuka CLI/WinBox di laptop.
2. **Dead-Host Safety:** Memutus *polling* port otomatis jika perangkat utama mati (*Down*), menghemat resource jaringan dan menghindari data *stale*.
3. **Offline Room Database Cache:** Menjamin operasional aplikasi tetap berjalan optimal di ruang server bawah tanah (*offline/no signal*).

---

## ✨ Fitur Utama
* **Global Network Health Summary:** Penghitung biner status perangkat (*Hardware Online* vs *Hardware Offline*) secara *real-time* di bagian atas dashboard.
* **Managed Device Dynamic List:** Daftar seluruh node router dan switch yang menampilkan *System Name* (`sysName`), *Host IP*, dan agregasi port biner (`Port: Up: X | Down: Y`).
* **Dead-Host Safety Handler:** Proteksi visual yang secara otomatis mengunci indikator port (`Up: - | Down: -`) saat hardware induk terdeteksi *Down*.
* **Master-Detail Navigation Flow:** Transisi cepat dari kartu node perangkat menuju direktori antarmuka port fisik menggunakan parameter ID unik.
* **Port-Level Interface Breakdown & Layer 3 IP Mapping:** Membedah direktori antarmuka port (`ether1`, link speed, deskripsi alias, alokasi IP Address Layer 3, serta deteksi antarmuka *Bridged*).
* **Full Offline Room Database Cache:** Persistensi lokal SQLite via Room DB untuk tabel perangkat dan antarmuka port agar dapat diakses tanpa koneksi internet.
* **On-Demand Synchronization:** Dukungan *Swipe-to-Refresh* (Pull-to-Refresh) pada layar Dashboard dan Detail Port.
* **Dynamic Day/Night Mode:** Fitur pengubah tema Gelap/Terang dengan penyimpanan preferensi lokal (*SharedPreferences*).

---

## 🏗️ Arsitektur & Tech Stack
Aplikasi dibangun mengikuti standar **Modern Android Development (MAD)** dengan pola arsitektur **MVVM (Model-View-ViewModel)** dan **Repository Pattern**:

| Layer / Komponen | Teknologi & Library | Deskripsi Fungsi |
| :--- | :--- | :--- |
| **Language** | Kotlin | Bahasa pemrograman utama |
| **Architecture** | MVVM + Repository Pattern | Pemisahan logika UI, domain, dan *Single Source of Truth* |
| **Local Persistence** | Android Room Database (SQLite) | Caching lokal untuk `DeviceEntity` dan `PortEntity` |
| **Networking** | Retrofit 2 + OkHttp 3 + Gson | Konsumsi REST API LibreNMS secara asinkron |
| **Async Processing** | Kotlin Coroutines & LiveData | Eksekusi background thread tanpa memicu *UI freezing* |
| **UI Components** | Material 3 & ViewBinding | Antarmuka adaptif, CardView, and SwipeRefreshLayout |

---

## 🔌 Integrasi API LibreNMS
Aplikasi berkomunikasi langsung dengan LibreNMS REST API melalui header autentikasi `X-Auth-Token`:

* `GET /api/v0/devices` : Mengambil daftar seluruh hardware node dan status global.
* `GET /api/v0/devices/{id}/ports` : Mengambil rincian status operasional/admin, speed, dan flag port.
* `GET /api/v0/devices/{id}/ip` : Mengambil pemetaan IPv4 subnet pada masing-masing antarmuka port.

---

## 🚀 Cara Menjalankan Project

### 1. Prasyarat
* Android Studio Iguana / Ladybug atau versi lebih baru
* Android SDK minSdk 24 (Android 7.0) / targetSdk 34+
* Server LibreNMS aktif yang dapat diakses oleh perangkat/emulator

### 2. Konfigurasi Endpoint API
Sesuaikan `BASE_URL` dan `API_TOKEN` pada file `data/remote/LibreNmsApi.kt`:
```kotlin
private const val BASE_URL = "http://<IP_SERVER_LIBRENMS>:<PORT>/"
private const val API_TOKEN = "<API_TOKEN_LIBRENMS>"

### 3. Build & Install via Terminal
# Clone repositori
git clone [https://github.com/qoqokoi/librepulse.git](https://github.com/qoqokoi/librepulse.git)
cd librepulse

# Build debug APK dan pasang ke perangkat yang terhubung
./gradlew assembleDebug && adb install -r app/build/outputs/apk/debug/app-debug.apk

## 👤 Informasi Akademik
Nama Pengembang: Muhammad Dafi Al Haq
NIM: 45202461106
Program Studi: Teknik Informatika
Institusi: Universitas Darussalam Gontor
Dosen Pengampu: Wahid Alfaridsi Achmad Zein M.Kom.
