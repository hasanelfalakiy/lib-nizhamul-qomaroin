package com.andihasan7.lib.nizhamul.qomaroin.util

import kotlin.math.abs
import kotlin.math.round

// fungsi custom round dibelakang koma
fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

// HH:MM:SS angka dibulatkan ke detik
fun toTimeFullRound(decimal: Double): IntArray {
    var time = abs(decimal).toInt()
    var minute = ((abs(decimal) - time.toDouble()) * 60).toInt()
    var second = (round((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60)).toInt()

    // Tambahkan perhitungan untuk membulatkan detik ke menit & menit ke jam jika detik & menit ==
    // 60
    if (second.toInt() == 60) {
        second = second.toInt() - 60
        minute = minute.toInt() + 1
    }

    if (minute.toInt() == 60) {
        minute = minute.toInt() - 60
        time = time.toInt() + 1
    }

    if (decimal < 0) {
        time = time - time * 2
        minute = minute - minute * 2
        second = second - second * 2
    }

    return intArrayOf(time, minute, second)
}

// HH:MM:SS angka dibulatkan ke detik
fun toTimeFullRoundSec(decimal: Double): String {
    var time = abs(decimal).toInt().toString()
    var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
    var second =
        (round((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60))
            .toInt()
            .toString()

    // Tambahkan perhitungan untuk membulatkan detik ke menit & menit ke jam jika detik & menit ==
    // 60
    if (second.toInt() == 60) {
        second = (second.toInt() - 60).toString()
        minute = (minute.toInt() + 1).toString()
    }

    if (minute.toInt() == 60) {
        minute = (minute.toInt() - 60).toString()
        time = (time.toInt() + 1).toString()
    }

    // Tambahkan nol sebelum angka yang kurang dari 10
    time = time.padStart(2, '0')
    minute = minute.padStart(2, '0')
    second = second.padStart(2, '0')

    if (decimal < 0) {
        time = "-$time"
    }

    return "$time:$minute:$second"
}

// HH:MM:SS,ss dibulatkan ke 2 angka di belakang koma
fun toTimeFullRound2(decimal: Double): String {
    var time = abs(decimal).toInt().toString()
    var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
    var second =
        ((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60).round(2).toString()

    // Tambahkan nol sebelum angka yang kurang dari 10
    time = time.padStart(2, '0')
    minute = minute.padStart(2, '0')
    second = second.padStart(2, '0')

    if (decimal < 0) {
        time = "-$time"
    }

    return "$time:$minute:$second"
}

// DD° MM` SS,ss`` dibulatkan ke 2 angka di belakang koma
fun toDegreeFullRound2(decimal: Double): String {
    var degree = abs(decimal).toInt().toString()
    var minute = ((abs(decimal) - degree.toDouble()) * 60).toInt().toString()
    var second =
        ((((abs(decimal) - degree.toDouble()) * 60) - minute.toDouble()) * 60).round(2).toString()

    // Tambahkan nol sebelum angka yang kurang dari 10
    degree = degree.padStart(2, '0')
    minute = minute.padStart(2, '0')
    second = second.padStart(2, '0')

    if (decimal < 0) {
        degree = "-$degree"
    }

    return "$degree\u00B0 $minute\u2032 $second\u2033"
}

fun azimuth(x: Double, y: Double, az: Double): Double {

    return if (x > 0.0 && y >= 0.0) {
        az
    } else if (x < 0.0) {
        az + 180
    } else if (x > 0.0 && y < 0.0) {
        az + 360
    } else if (x == 0.0 && y > 0.0) {
        90.0
    } else if (x == 0.0 && y < 0.0) {
        270.0
    } else {
        az
    }
}

fun numberSelasa(number: Int): String {
    val hari =
        when (number) {
            1 -> "Selasa"
            2 -> "Rabu"
            3 -> "Kamis"
            4 -> "Jum`at"
            5 -> "Sabtu"
            6 -> "Ahad"
            7 -> "Senin"
            else -> "Senin"
        }
    return hari
}

fun numberPahing(number: Int): String {
    val pasaran =
        when (number) {
            1 -> "Pahing"
            2 -> "Pon"
            3 -> "Wage"
            4 -> "Kliwon"
            5 -> "Legi"
            else -> "Legi"
        }
    return pasaran
}

fun numberJanuari(number: Int): String {
    val bulan =
        when (number) {
            1 -> "Januari"
            2 -> "Februari"
            3 -> "Maret"
            4 -> "April"
            5 -> "Mei"
            6 -> "Juni"
            7 -> "Juli"
            8 -> "Agustus"
            9 -> "September"
            10 -> "Oktober"
            11 -> "November"
            12 -> "Desember"
            else -> "Desember"
        }
    return bulan
}
