

# TUBES Pemrograman Berorientasi Obyek 1
<ul>
  <li>Mata Kuliah: Pemrograman Berorientasi Obyek 1</li>
  <li>Dosen Pengampu: <a href="https://github.com/Muhammad-Ikhwan-Fathulloh">Muhammad Ikhwan Fathulloh</a></li>
</ul>



## Kelompok
<ul>
  <li>Nama: Anif Burhanuddin</li>
  <li>NIM: 23552011075</li>
</ul>
<ul>
  <li>Nama: Muhammad Rijal</li>
  <li>NIM: 23552011138</li>
</ul>

## Judul Studi Kasus
<p>Aplikasi Manajemen Asset Berbasis Dekstop</p>

## Penjelasan Studi Kasus
<p>
Aplikasi Manajemen asset ini bernama Klosset (Kelola Asset) ini merupakan versi yang lebih tinggi dari pada sebelum nya di versi ini terdapat fitur dimana ada dua user 
yang bisa menggunakan aplikasi ada admin dan user biasa dan fitur approval dan user mengajukan asset dalam aplikasi ini tidak banyak operasi yang rumit hanya 
CRUD dan log activity saja yang ada dan menggunakan konsep OOP beberapa fitur lain nya  : </p>
<ul>
  <li>Dashboard Status Asset</li>
  <li>Manage data Asset</li>
  <li>Data Penggunaan Asset</li>
  <li>Data LogActivity</li>

</ul>

## Penjelasan 4 Pilar OOP dalam Studi Kasus

### 1. Inheritance
<p>Inheritance adalah mewarisi atribut dan method dari class induk (parent class) ke class anak (child class).
	Tujuan: Menghindari duplikasi kode dan mempermudah pengelolaan kode.
Penerapan dalam proyek ini:
File: Models/User.java (Superclass)
Bagaimana ini menunjukkan Inheritance?
 Class Admin mewarisi User, sehingga tidak perlu mendefinisikan ulang id, username, dan role.
 Menambahkan method baru (approveAsset()) tanpa mengubah User.

</p>

### 2. Encapsulation
<p>Encapsulation adalah menyembunyikan data dan hanya mengizinkan akses melalui method tertentu.
	Tujuan: Melindungi data dari manipulasi langsung dan memastikan data tetap valid.
Penerapan dalam proyek ini:
 File: Models/Asset.java
 Bagaimana ini menunjukkan Encapsulation?
 Atribut private hanya bisa diakses melalui getter & setter.
 Keamanan data: Data aset hanya bisa diubah dengan aturan yang kita tentukan

</p>

### 3. Polymorphism
<p>Polymorphism adalah kemampuan satu method atau class memiliki banyak bentuk.
Tujuan: Mempermudah penggunaan method yang sama untuk objek yang berbeda.
Penerapan dalam proyek ini:
Method updateAssetStatus() di AdminDashboardController
Bagaimana ini menunjukkan Polymorphism?
Method updateAssetStatus() bisa dipanggil dengan status yang berbeda (approved, rejected).
Menghindari duplikasi kode, karena hanya satu method yang menangani semua perubahan status.

</p>

### 4. Abstraction
<p>Abstraction adalah menyembunyikan detail implementasi dan hanya menunjukkan fungsionalitas penting.
Tujuan: Membuat kode lebih bersih dan mudah digunakan.
Penerapan dalam proyek ini:
File: Models/DatabaseConnection.java
Bagaimana ini menunjukkan Abstraction?
Kode lain cukup memanggil DatabaseConnection.connect() tanpa tahu detail koneksi database.
Menghindari pengulangan kode koneksi di semua controller

</p>

## Demo Proyek
<ul>
  <li>Github: <a href="" target="_blank">Github</a></li>
  <li>Youtube: <a href="" target="_blank">Youtube</a></li>
</ul>

