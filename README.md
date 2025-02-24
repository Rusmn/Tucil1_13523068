# IQ Puzzler Pro

## Deskripsi
Program Java yang menyelesaikan IQ Puzzler Pro dengan menempatkan beberapa piece pada papan. Program ini menggunakan pendekatan algoritma brute force untuk menemukan solusi yang valid dengan mencoba berbagai orientasi piece (rotasi dan pencerminan) dan posisi pada papan. Setiap piece direpresentasikan oleh karakter unik (alfabet), dan solusi akhir menampilkan piece-piece dalam warna berbeda.

## Fitur
- Membaca konfigurasi puzzle dari file teks
- Output berwarna untuk visualisasi yang lebih baik
- Kemampuan menyimpan solusi
- Penulisa kinerja (waktu eksekusi dan kombinasi yang dicoba)

## Requirements
- Java Development Kit (JDK) 8 or higher
- Terminal/Console that supports ANSI color codes

## Kompilasi
Untuk mengkompilasi program, clone repository ini terlebih dahulu.

```bash
git clone https://github.com/Rusmn/Tucil1_13523068.git
cd Tucil1_13523068
```

lalu kompilasi program.

```bash
javac -d bin src/*.java
```

Pastikan Anda memiliki struktur direktori berikut:
```
.
├── bin/
├── doc/
├── src/
│   ├── Init.java
│   ├── IOHandler.java
│   ├── MainCLI.java
│   ├── Solver.java
│   └── Utils.java
├── test/
├── .gitignore
├── LICENSE
└── README.md
```

## Cara Menjalankan Program

1. Setelah kompilasi, ganti ke direktori `bin`
2. Jalankan program menggunakan:
```bash
java MainCLI
```
3. Saat diminta, masukkan nama file input Anda (harus berada di direktori `test`)
4. Program akan mencari solusi dan menampilkannya jika ditemukan
5. Anda dapat memilih untuk menyimpan solusi ke file saat diminta

## Format File Input
File input harus mengikuti format ini:
```
M N P
DEFAULT
[piece 1]
[piece 2]
...
[piece P]
```
Dimana:
- M = jumlah baris
- N = jumlah kolom
- P = jumlah piece
- Setiap piece direpresentasikan oleh pola karakter menggunakan titik (.) untuk ruang kosong

## Oleh
Nama : Muh. Rusmin Nurwadin
NIM  : 13523068
