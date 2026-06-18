# LAPORAN AUTOMATED TESTING - TUGAS 5
**Nama:** Muhammad Dafi Al Haq  
**NIM:** 452024611067  
**Prodi:** Teknik Informatika (Semester 5)  
**Kampus:** Universitas Darussalam Gontor  

---

1. Mengapa Wajib Menggunakan Fungsi suspend?
Sifat Asinkron (Non-Blocking): Operasi basis data (seperti query data dalam jumlah besar atau penulisan data ke disk) membutuhkan waktu pemrosesan yang tidak instan.

Mekanisme Kerja: Kata kunci suspend memberi tahu kompiler Kotlin bahwa fungsi tersebut memiliki kemampuan untuk ditangguhkan (di-pause) jalannya tanpa memblokir thread tempat ia berjalan.

Dampak ke UI: Saat Room melakukan operasi pencarian data, UI aplikasi tetap bisa merespons input sentuhan pengguna secara lancar karena thread utama tidak dipaksa menunggu proses database selesai.

2. Mengapa Wajib Dijalankan Melalui Dispatchers.IO?
Alokasi Kapasitas Thread: Secara default, Jetpack Compose berjalan di Dispatchers.Main (Main Thread) yang bertugas khusus menangani rendering grafis antarmuka (UI). Jika thread ini dibebani operasi berat, aplikasi akan mengalami dropped frames (patah-patah) atau memicu eror ANR (Application Not Responding).

Optimasi Thread Pool: Dispatchers.IO adalah sekumpulan thread pool yang memang dirancang dan dioptimalkan secara khusus oleh runtime Android untuk menangani beban kerja Disk I/O (baca-tulis file/database) dan Network I/O (akses API/internet).

Efisiensi: Memindahkan eksekusi fungsi DAO ke Dispatchers.IO memastikan bahwa beban kerja berat diisolasi ke background thread, sehingga Main Thread tetap bersih dan responsif untuk melayani interaksi pengguna.


