#set page(
  paper: "a4",
  margin: (x: 2.5cm, y: 2.5cm),
  numbering: "1",
)
#set text(font: "Times New Roman", size: 11pt, lang: "id")

// --- 1. HALAMAN SAMPUL (ZONA MBOIS) ---
#align(center + horizon)[
  #v(-2cm)
  #text(size: 26pt, weight: "bold", fill: blue.darken(50%))[LAPORAN TUGAS 1] \
  #text(size: 18pt, weight: "medium")[Pemrograman perangkat Bergerak]
  #v(2cm)

  #image("unida.png", width: 35%)
  #v(1cm)

  #grid(
    columns: (1fr),
    gutter: 15pt,
    [Disusun Oleh:],
    [#text(size: 14pt, weight: "bold")[Muhammad Dafi Al Haq]],
    [NIM: [452024611067]],
  )

  #v(2cm)
  #text(size: 12pt)[
    *PROGRAM STUDI TEKNIK INFORMATIKA* \
    *UNIVERSITAS DARUSSALAM GONTOR* \
    *2026*
  ]
]

#pagebreak()

// --- 2. DAFTAR ISI (AUTO) ---
#outline(title: "Daftar Isi", indent: 2em)
#pagebreak()

// --- 3. KONTEN UTAMA ---
= Pendahuluan
Laporan ini disusun untuk memenuhi Tugas 1 mata kuliah Pemrograman Mobile. Berbeda dengan pendekatan konvensional yang menggunakan Android Studio IDE, penulis menggunakan metode *CLI (Command Line Interface)* di lingkungan *Arch Linux*. 

Metode ini dipilih untuk meminimalisir penggunaan sumber daya sistem (RAM) dan memberikan pemahaman mendalam mengenai alur kerja *build system* Android yang sesungguhnya.

= I. Instalasi Android SDK & Build Tools
Proses instalasi dilakukan secara manual untuk memastikan lingkungan pengembangan yang ringan (*lightweight*).

1. *Java Development Kit (JDK):*
   Menggunakan OpenJDK 21 sebagai mesin utama kompilasi:
   `sudo pacman -S jdk21-openjdk`
2. *Android Command Line Tools:*
   Instalasi dilakukan melalui AUR (*Arch User Repository*) untuk mendapatkan versi terbaru:
   `yay -S android-sdk-cmdline-tools-latest`
3. *Environment Setup:*
   Mengatur path pada file `.zshrc` agar perintah `adb` dan `gradle` dapat diakses secara global di terminal Kitty.

= II. Konfigurasi ADB & scrcpy
Mirroring layar dilakukan menggunakan perangkat fisik untuk menghindari beban emulator.
1. *Instalasi Tools:* Menginstal paket `android-tools` untuk ADB dan `scrcpy` untuk sinkronisasi layar.
2. *Koneksi:* Perangkat fisik dihubungkan melalui kabel USB dengan fitur *USB Debugging* aktif. Kecepatan transfer data dipastikan stabil untuk mendukung pengembangan real-time.

= III. Pelaksanaan Tugas (Project "Tugas 1")
Aplikasi dibuat dengan koding manual pada file `MainActivity.kt` menggunakan editor *Neovim*.

- *Modifikasi Teks:* Mengubah string output menjadi *"Hi, my name is Dafi!"*.
- *Modifikasi Visual:* Mengimplementasikan warna latar belakang *Cyan (\#80DEEA)* secara programatis di dalam kode Kotlin.
- *Build Process:* Menjalankan perintah `./gradlew installDebug` untuk merakit file APK dan menanamkannya langsung ke perangkat target.\\

= IV. Dokumentasi & Hasil Running

#grid(
  columns: (1fr, 1fr),
  gutter: 15pt,
  [
    #align(center + horizon)[#image("terminal.png", width: 100%)]
  ],
  [
    #align(center + horizon)[#image("hp.png", width: 100%)]
  ]
)

= V. Kesimpulan
Implementasi pengembangan Android melalui CLI berhasil dijalankan dengan lancar di laptop Axioo 24GB RAM. Penggunaan sumber daya terpantau jauh lebih efisien dibandingkan penggunaan IDE berat, memberikan ruang kerja yang lebih luas bagi pengembang untuk berkreasi secara teknis.

link github: https://github.com/Qoqokoi/android-programing
