# LAPORAN AUTOMATED TESTING - TUGAS 5
**Nama:** Muhammad Dafi Al Haq  
**NIM:** 452024611067  
**Prodi:** Teknik Informatika (Semester 5)  
**Kampus:** Universitas Darussalam Gontor  

---

## 1. Struktur Direktori Pengujian
* **Local Unit Test (`app/src/test/`):** Digunakan untuk menguji `calculateTipTest` guna memastikan akurasi formula matematis murni pada JVM lokal secara terisolasi.
* **Instrumentation Test (`app/src/androidTest/`):** Menggunakan `ui-test-junit4` untuk mensimulasikan input pengguna pada komponen Jetpack Compose secara riil di atas perangkat Android aktif.

## 2. Penjelasan Skenario Uji Kustom Mandiri
* **Nama Skenario:** `calculateTip_NegativeAmount_ReturnsZero`
* **Tujuan:** Memastikan arsitektur backend aplikasi kebal dari masukan data anomali (angka negatif).
* **Logika:** Jika pengguna menginput nilai tagihan di bawah nol (`-50000.0`), sistem *pipeline* penanganan eror secara otomatis memaksa fungsi mengembalikan output tip senilai `0.0` (mencegah nilai minus bocor ke kalkulasi akhir).
