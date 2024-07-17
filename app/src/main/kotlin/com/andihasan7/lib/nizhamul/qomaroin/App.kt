/*
 * This source file was generated by the Gradle 'init' task
 */
package com.andihasan7.lib.nizhamul.qomaroin

import com.andihasan7.lib.nizhamul.qomaroin.gerhanamatahari.GerhanaMatahari
import com.andihasan7.lib.nizhamul.qomaroin.util.round

fun main() {
    
    
    
    // gerhana matahari nidzomul qomaroin
    val nq =
        GerhanaMatahari(
            -2.856944444444, // lintang
            108.297222222222, // bujur
            2.0, // TT
            7.0, // TZ
            5, // bulan hijri
            1437, // tahun hijri
            9, // tanggal miladi
            3, // bulan miladi
            2016, // tahun miladi
            2, // TO/TD
            1, // nau'
            2457456.5, // JD
            69.6, // deltaT
            -0.06252, // X0
            0.55028, // X1
            0.0, // X2
            0.25384, // Y0
            0.17212, // Y1
            0.00002, // Y2
            -4.37971, // d0
            0.015886, // d1
            0.000001, // d2
            207.37261, // M0
            15.00397, // M1
            0.53889, // L1 0
            -0.00007, // L1 1
            -0.00001, // L1 2
            -0.00723, // L2 0
            -0.00007, // L2 1
            -0.00001, // L2 2
            0.00471, // tanF 1
            0.00469 // tanF 2
        )

	val jenisGerhana = nq.jenisGerhana
    val awalGerhanaHMS = nq.awalGerhanaHMS
    val awalTotalHMS = nq.awalTotalHMS
    val puncakGerhanaHMS = nq.puncakGerhanaHMS
    val akhirTotalHMS = nq.akhirTotalHMS
    val akhirGerhanaHMS = nq.akhirGerhanaHMS
    val alt_u1 = nq.tinggiAwalGerhana.round(1)
    val alt_u2 = nq.tinggiAwalTotal.round(1)
    val alt_Max = nq.tinggiPuncak.round(1)
    val alt_u3 = nq.tinggiAkhirTotal.round(1)
    val alt_u4 = nq.tinggiAkhirGerhana.round(1)
    val azU1 = nq.azimuthAwalGerhana.round(1)
    val azU2 = nq.azimuthAwalTotal.round(1)
    val azMax = nq.azimuthPuncak.round(1)
    val azU3 = nq.azimuthAkhirTotal.round(1)
    val azU4 = nq.azimuthAkhirGerhana.round(1)
    val totalDurasi = nq.totalDurasiGMHMS
    val durasiTotalGB = nq.durasiTotalGMHMS
    val Mag = nq.Mag
    val Obsk = nq.Obsk
    
    
    val tahun = nq.tahun
    val bulanString = nq.bulanString
    val tanggal = nq.tanggal
    val hariString = nq.hariString
    val pasaranString = nq.pasaranString
    
    
    println(" ")
    println("Date : $hariString $pasaranString, $tanggal $bulanString $tahun")
    println("")
    println("Jenis Gerhana : $jenisGerhana")
    println(" ")
    println("Awal Gerhana       : $awalGerhanaHMS")
    println("Awal Total         : $awalTotalHMS")
    println("Puncak Gerhana     : $puncakGerhanaHMS")
    println("Akhir Total        : $akhirTotalHMS")
    println("Akhir Gerhana      : $akhirGerhanaHMS")
    println(" ")
    println("Tinggi Awal Gerhana  : $alt_u1")
    println("Tinggi Awal Total    : $alt_u2")
    println("Tinggi Puncak        : $alt_Max")
    println("Tinggi Akhir Total   : $alt_u3")
    println("Tinggi Akhir Gerhana : $alt_u4")
    println(" ")
    println("Azimuth Awal Gerhana : $azU1")
    println("Azimuth Awal Total   : $azU2")
    println("Azimuth Puncak       : $azMax")
    println("Azimuth Akhir Total  : $azU3")
    println("Azimuth Akhir Gerhana: $azU4")
    println(" ")
    println("Magnitude           : $Mag")
    println("Total Durasi        : $totalDurasi")
    println("Durasi Total/Cincin : $durasiTotalGB")
    println("Obskurasi           : $Obsk %")
    
    
    /*
    val cjd = nq.cjd
    val zh = nq.zh
    val ff = nq.ff
    val alpha = nq.alpha
    val ac = nq.Ac
    val bc = nq.Bc
    val cC = nq.cC
    val Dc = nq.Dc
    val Ec = nq.Ec
    val hari = nq.hari
    val pasaran = nq.pasaran
    
    val P1 = nq.P1
    val P2 = nq.P2
    val P3 = nq.P3
    val P4 = nq.P4
    val P5 = nq.P5
    val t = nq.t
    val K = nq.K
    val m = nq.m
    val lL = nq.lL
    val qQ = nq.qQ
    val e = nq.e
    val z = nq.z
    val Tau = nq.Tau
    
    val rR = nq.rR
    val sS = nq.sS
    val y = nq.y
    val zZ = nq.zZ
    val a = nq.a
    val b = nq.b
    val BB = nq.BB
    val CC = nq.CC
    
    
    val w = nq.w
    val d = nq.d
    
    
    val pwAwal = nq.pwAwal
    val pwAkhir = nq.pwAkhir
    
    val Pe = nq.Pe
    val Pf = nq.Pf
    
    val t2 = nq.t2
    
    
    val Pg = nq.Pg
    val Ph = nq.Ph
    
    val t3 = nq.t3
    
    
    val tU1 = nq.tU1
    val dU1 = nq.dU1
    val raU1 = nq.raU1
    val hau1 = nq.hau1
    
    val xU1 = nq.xU1
    val yU1 = nq.yU1
    val aU1 = nq.aU1
    
    
    val tU2 = nq.tU2
    val dU2 = nq.dU2
    val raU2 = nq.raU2
    val hau2 = nq.hau2
    
    val xU2 = nq.xU2
    val yU2 = nq.yU2
    val aU2 = nq.aU2
    
    
    val tMax = nq.tMax
    val dMax = nq.dMax
    val raMax = nq.raMax
    val hauMax = nq.hauMax
    
    val xMax = nq.xMax
    val yMax = nq.yMax
    val aMax = nq.aMax
    
    
    val tU3 = nq.tU3
    val dU3 = nq.dU3
    val raU3 = nq.raU3
    val hau3 = nq.hau3
    
    val xU3 = nq.xU3
    val yU3 = nq.yU3
    val aU3 = nq.aU3
    
    
    val tU4 = nq.tU4
    val dU4 = nq.dU4
    val raU4 = nq.raU4
    val hau4 = nq.hau4
    
    val xU4 = nq.xU4
    val yU4 = nq.yU4
    val aU4 = nq.aU4
    
    println("cjd : $cjd")
    println("zh : $zh")
    println("ff : $ff")
    println("alpha : $alpha")
    println("ac : $ac")
    println("bc : $bc")
    println("cC : $cC")
    println("Dc : $Dc")
    println("Ec : $Ec")
    println("tanggal : $tanggal")
    println("hari : $hari")
    println("hari : $hariString")
    println("pasaran : $pasaran")
    println("pasaran : $pasaranString")
    
	println("P1 : $P1")
    println("P2 : $P2")
    println("P3 : $P3")
    println("P4 : $P4")
    println("P5 : $P5")
    println("t = $t")
    println("puncak gerhana : $K")
    
    println("m : $m")
    println("L' : $lL")
    println("Q' : $qQ")
    println("e : $e")
    println("z : $z")
    println("Tau : $Tau")
    println("Mag : $Mag")
    println("r' : $rR")
    println("s' : $sS")
    println("y : $y")
    println("zZ : $zZ")
    println("a : $a")
    println("b : $b")
    println("BB : $BB")
    println("CC : $CC")
    println("Obsk : $Obsk")
    println("jenis : $jenisGerhana")
    println("w : $w")
    println("d : $d")
    println("Pe : $Pe")
    println("Pf : $Pf")
    println("t2 : $t2")
    println("Pg : $Pg")
    println("Ph : $Ph")
    println("t3 : $t3")
    println(" ")
    println("tU1 : $tU1")
    println("dU1 : $dU1")
    println("raU1 : $raU1")
    println("hau1 : $hau1")
    println("")
    println("xU1 : $xU1")
    println("yU1 : $yU1")
    println("aU1 : $aU1")
    
    println("tU2 : $tU2")
    println("dU2 : $dU2")
    println("raU2 : $raU2")
    println("hau2 : $hau2")
    
    println("xU2 : $xU2")
    println("yU2 : $yU2")
    println("aU2 : $aU2")
    
    println(" ")
    println("tMax : $tMax")
    println("dMax : $dMax")
    println("raMax : $raMax")
    println("hauMax : $hauMax")
    
    println("xMax : $xMax")
    println("yMax : $yMax")
    println("aMax : $aMax")
    
    println(" ")
    println("tU3 : $tU3")
    println("dU3 : $dU3")
    println("raU3 : $raU3")
    println("hau3 : $hau3")
    
    println("xU3 : $xU3")
    println("yU3 : $yU3")
    println("aU3 : $aU3")
    
    println(" ")
    println("tU4 : $tU4")
    println("dU4 : $dU4")
    println("raU4 : $raU4")
    println("hau4 : $hau4")
    
    println("xU4 : $xU4")
    println("yU4 : $yU4")
    println("aU4 : $aU4")
    */
}
