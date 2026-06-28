# Tugas 11: Connect To The Internet

## Identitas Lengkap
* **Nama:** Muhammad Dafi Al Haq
* **NIM:** 452024611067

## Dokumentasi Visual Aplikasi
![Demo Running Aplikasi](ss.png)


---

## Analisis Karakteristik Perlindungan Izin Android

Berdasarkan materi Google Developer Pathway Lesson 11, terdapat perbedaan mendasar antara level perlindungan izin aplikasi dari sisi interaksi pengguna:

### 1. Normal Permission (Contoh: `INTERNET`, `ACCESS_NETWORK_STATE`)
* **Karakteristik:** Izin yang dianggap memiliki risiko rendah terhadap privasi pengguna atau keamanan sistem.
* **Interaksi Pengguna:** Sistem memberikan izin ini secara otomatis pada saat aplikasi dipasang oleh pengguna (*granted automatically at install time*). Pengguna tidak akan diberikan dialog prompt (pop-up) saat aplikasi mencoba mengakses internet, sehingga prosesnya berjalan transparan dan mulus.

### 2. Dangerous Permission (Contoh: `CAMERA`, `READ_CONTACTS`, `LOCATION`)
* **Karakteristik:** Izin yang memberikan akses ke data pribadi sensitif atau kontrol penuh atas perangkat keras yang bisa berdampak negatif jika disalahgunakan.
* **Interaksi Pengguna:** Aplikasi diwajibkan secara eksplisit untuk meminta persetujuan langsung dari pengguna pada saat aplikasi sedang berjalan (*runtime prompt*). Pengguna memiliki hak untuk menyetujui ("Allow") atau menolak ("Deny") akses tersebut. Arsitektur aplikasi yang baik harus mampu menangani skenario jika pengguna menolak izin tersebut (*fall back gracefully*).

