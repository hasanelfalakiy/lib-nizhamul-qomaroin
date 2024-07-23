<p align="center">
  <img src="./img/ic_nizham.jpg" alt="app_banner"/>
</p>

<h2 align="center"><b>Nizhamul Qomaroin</b></h2>
<p align="center">
<b>Library Hisab metode Kitab Nizhamul Qomaroin</b>
<p><br>

<p align="center">
<!-- Latest release -->
<img src="https://img.shields.io/github/v/release/hasanelfalakiy/lib-nizhamul-qomaroin?include_releases&label=latest%20release&style=for-the-badge&color=brightgreen" alt="latest_release"/>
<!-- Jitpack release -->
<img src="https://img.shields.io/jitpack/v/hasanelfalakiy/lib-nizhamul-qomaroin.svg?style=for-the-badge&color=brightgreen" alt="jitpack_release">
<!-- Github Repo size -->
<img src="https://img.shields.io/github/repo-size/hasanelfalakiy/lib-nizhamul-qomaroin?style=for-the-badge">
<!-- Build with Kotlin -->
<img src="https://img.shields.io/badge/Kotlin-C116E3?&style=for-the-badge&logo=kotlin&logoColor=white" alt="build_with_kotlin">
<!-- License -->
<img src="https://img.shields.io/github/license/hasanelfalakiy/lib-nizhamul-qomaroin?color=blue&style=for-the-badge&color=brightgreen" alt="License">
</p>

# Tentang Kitab Nihzamul Qomaroin
Kitab ini merupakan karya dari Ust Abu Sabda Bandung, yang membahas cara menghitung Ijtima', posisi Hilal untuk awal bulan Hijriyah, Gerhana Matahari & Bulan. Jika disandingkan dengan hasil hitung NASA selisihnya hanya beberapa detik.

# Dokumentasi penggunaan
- [Dokumentasi](./app/build/dokka/html/index.html)

## Fitur

- [x] Gerhana Bulan
- [x] Gerhana Matahari
- [ ] Hisab Awal Bulan Hijriyah

## Konfigurasi pertama

1. masukkan kode ini ke settings.gradle.kts (root kotlin dsl) di blok ```repositories```
```kotlin.kts
  dependencyResolutionManagement {
    repositories {
      // contoh
      maven {
        url = uri("https://jitpack.io")
      }
    }
  }
```
jika menggunakan groovy dsl
```groovy
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
```
2. masukkan dependensi ke build.gradle.kts (app/build.gradle.kts kotlin dsl)
di blok ```dependencies``` 

```kotlin.kts
implementation("com.github.hasanelfalakiy:lib-nizhamul-qomaroin:1.0.0")
```
jika menggunakan groovy dsl
```groovy
implementation 'com.github.hasanelfalakiy:lib-nizhamul-qomaroin:1.0.0'
```
## Ingin berkontribusi?

> Jika Anda ingin berkontribusi, silahkan menggarpu (Fork) repositori ini, buat perubahan, kirim Pull request ke repositori ini

## Kontak Kami

- [Telegram](https://t.me/moonelfalakiy)
- [Grup diskusi Telegram](https://t.me/moonlight_studio01/9)

## License

This project is released under the [GPL-3 License](./LICENSE).
