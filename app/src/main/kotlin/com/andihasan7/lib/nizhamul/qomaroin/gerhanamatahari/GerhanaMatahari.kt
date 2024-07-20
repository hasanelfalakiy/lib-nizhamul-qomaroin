/*
 * This file is part of lib-nizhamul-qomaroin.
 *
 * lib-nizhamul-qomaroin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * lib-nizhamul-qomaroin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with lib-nizhamul-qomaroin.  If not, see <https://www.gnu.org/licenses/>.
 *
 *
 * @programmed by: Andi Hasan A
 * @github: https://github.com/hasanelfalakiy
 * 
 *
 */
 
package com.andihasan7.lib.nizhamul.qomaroin.gerhanamatahari

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.mod
import com.andihasan7.lib.nizhamul.qomaroin.util.toTimeFullRound2
import com.andihasan7.lib.nizhamul.qomaroin.util.toDegreeFullRound2
import com.andihasan7.lib.nizhamul.qomaroin.util.toDoubleDegree
import com.andihasan7.lib.nizhamul.qomaroin.util.azimuth
import com.andihasan7.lib.nizhamul.qomaroin.util.numberJanuari
import com.andihasan7.lib.nizhamul.qomaroin.util.numberSelasa
import com.andihasan7.lib.nizhamul.qomaroin.util.numberPahing


class GerhanaMatahari(
    latitude: Double,
    longitude: Double,
    elevation: Double,
    timeZone: Double,
    bulanHijri: Int,
    tahunHijri: Int,
    tanggalMiladi: Int,
    bulanMiladi: Int,
    tahunMiladi: Int,
    t0: Int, // TO/TD di tabel kitabnya mulai tahun 1400 hijriyah tertulis TD apakah salah ketik
    tp: Int, // TP t = 1 total, r = 2 annular/cincin, p = 3 partial/sebagian, rt = 4 hybrid
    jd: Double, // JD
    deltaT: Double,
    x0: Double,
    x1: Double,
    x2: Double,
    y0: Double,
    y1: Double,
    y2: Double,
    d0: Double,
    d1: Double,
    d2: Double,
    m0: Double, // M0
    m1: Double, // M1
    l1_0: Double, // L1
    l1_1: Double, // L1
    l1_2: Double, // L1
    l2_0: Double, // L2
    l2_1: Double, // L2
    l2_2: Double, // L2
    tanF1: Double,
    tanF2: Double
) {

    // takdil awal li wasatis syams
    val Tm1 = 0.0 // Tm1
    val P1 =
        TakdilWasatSyams.takdilWasatSyams(
                Tm1,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[0]
    // takdil tsani li wasatis syams
    val Tm2 = Tm1 + P1
    val P2 =
        TakdilWasatSyams.takdilWasatSyams(
                Tm2,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[0]

    // takdil tsalis li wasatis syams
    val Tm3 = Tm2 + P2
    val P3 =
        TakdilWasatSyams.takdilWasatSyams(
                Tm3,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[0]

    // takdil robi' li wasatis syams
    val Tm4 = Tm3 + P3
    val P4 =
        TakdilWasatSyams.takdilWasatSyams(
                Tm4,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[0]

    // takdil khomis li wasatis syams
    val Tm5 = Tm4 + P4
    val P5 =
        TakdilWasatSyams.takdilWasatSyams(
                Tm5,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[0]

    // jam puncak gerhana/saah wasat syams
    val t = Tm5 + P5
    val K = (t0 + t + timeZone - deltaT / 3600).mod(24.0)
    val puncakGerhana = K
    val puncakGerhanaHMS = toTimeFullRound2(K)

    val U =
        TakdilWasatSyams.takdilWasatSyams(
                Tm5,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[1]

    val V =
        TakdilWasatSyams.takdilWasatSyams(
                Tm5,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[2]

    val L =
        TakdilWasatSyams.takdilWasatSyams(
                Tm5,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[5]

    val R =
        TakdilWasatSyams.takdilWasatSyams(
                Tm5,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[7]

    val Q =
        TakdilWasatSyams.takdilWasatSyams(
                Tm5,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[6]

    val N =
        TakdilWasatSyams.takdilWasatSyams(
                Tm5,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[8]
    val A =
        TakdilWasatSyams.takdilWasatSyams(
                Tm5,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[3]
    val B =
        TakdilWasatSyams.takdilWasatSyams(
                Tm5,
                latitude,
                longitude,
                elevation,
                deltaT,
                x0,
                x1,
                x2,
                y0,
                y1,
                y2,
                d0,
                d1,
                d2,
                m0, // M0
                m1, // M1
                l1_0, // L1
                l1_1, // L1
                l1_2, // L1
                l2_0, // L2
                l2_1, // L2
                l2_2 // L2
            )[4]
    // al miqdaar wa ta' miyyatul kusuf
    val m = sqrt((U * U + V * V))
    val lL = L - R * tanF1
    val qQ = Q - R * tanF2
    val e = sqrt(N)
    val z = (A * V - U * B) / (e * lL)
    val Tau = (lL / e) * sqrt(1 - (z).pow(2))
    val Mag = (lL - qQ) / (lL + qQ)
    val rR = 2 * m / (lL + qQ)
    val sS = (lL - qQ) / (lL + qQ)
    val y = (sS * sS + rR * rR - 1) / (2 * rR * sS)
    val zZ = (1 + rR * rR - sS * sS) / (2 * 1 * rR)
    val a =
        if (y == 0.0) {
            0
        } else if (y > 0.0) {
            1
        } else if (y < 0.0) {
            -1
        } else {
            0
        }
    val b =
        if (zZ == 0.0) {
            0
        } else if (zZ > 0.0) {
            1
        } else if (zZ < 0.0) {
            -1
        } else {
            0
        }
    val BB =
        if (abs(y) > 1.0) {
            acos((a).toDouble())
        } else {
            acos(y)
        }
    val CC =
        if (abs(zZ) > 1) {
            acos((b).toDouble())
        } else {
            acos(zZ)
        }
    val _obsk =
        (sS *
            sS *
            (BB - sin(Math.toRadians(2 * BB)) / 2 + (CC - sin(Math.toRadians(2 * CC)) / 2))) / PI
    val Obsk = (_obsk).toInt() * 100

    // jinsil kusuf/ jenis gerhana
    val jenisGerhana =
        if (Mag < 0.0) {
            "Tidak terjadi gerhana"
        } else if (Mag > 0.0 && m > abs(qQ)) {
            "Gerhana Matahari Sebagian"
        } else if (Mag > 0.0 && m < abs(qQ) && qQ < 0.0) {
            "Gerhana Matahari Total"
        } else if (Mag > 0.0 && m < abs(qQ) && qQ > 0.0) {
            "Gerhana Matahari Cincin"
        } else {
            "Tidak terjadi gerhana"
        }

    // magnitude update rumus Ilmu Falak rumusan syar'i dan astronomi seri 3
    val magnitude = if (Mag > 0.0 && m < abs(qQ) && qQ < 0.0) {
    		(lL - qQ) / (lL + qQ)
        } else if (Mag > 0.0 && m < abs(qQ) && qQ > 0.0) {
        	(lL - qQ) / (lL + qQ)
        } else if (Mag > 0.0 && m > abs(qQ)) {
        	(lL - m) / (lL + qQ)
        } else {
        	0.0
        }
        
    // bidayah wa nihayah kusuf kulli/halqi
    val w = (A * V - U * B) / (e * qQ)
    val d = abs((qQ / e) * sqrt((1 - (w).pow(2))))
    val awalTotal = K - d
    val awalTotalHMS = toTimeFullRound2(awalTotal) // awal total/cincin
    val akhirTotal = K + d
    val akhirTotalHMS = toTimeFullRound2(akhirTotal) // akhir total/cincin

    val pwAwal = t - Tau
    val pwAkhir = t + Tau

    // takdil awal bidayatil kusuf
    val Tn1 = pwAwal
    val Pe =
        TakdilBidayahKusuf.takdilBidayahKusuf(
            Tn1,
            latitude,
            longitude,
            elevation,
            deltaT,
            x0,
            x1,
            x2,
            y0,
            y1,
            y2,
            d0,
            d1,
            d2,
            m0, // M0
            m1, // M1
            l1_0, // L1
            l1_1, // L1
            l1_2, // L1
            l2_0, // L2
            l2_1, // L2
            l2_2, // L2
            tanF1
        )
    // takdil tsani li bidayatil kusuf
    val Tn2 = Tn1 + Pe
    val Pf =
        TakdilBidayahKusuf.takdilBidayahKusuf(
            Tn2,
            latitude,
            longitude,
            elevation,
            deltaT,
            x0,
            x1,
            x2,
            y0,
            y1,
            y2,
            d0,
            d1,
            d2,
            m0, // M0
            m1, // M1
            l1_0, // L1
            l1_1, // L1
            l1_2, // L1
            l2_0, // L2
            l2_1, // L2
            l2_2, // L2
            tanF1
        )
	
    // t awal gerhana 
    val t2 = Tn2 + Pf
    val awalGerhana = t0 + t2 + timeZone - deltaT / 3600
    val awalGerhanaHMS = toTimeFullRound2(awalGerhana)

    // takdil awal linihayah kusuf
    val To1 = pwAkhir
    val Pg =
        TakdilNihayahKusuf.takdilNihayahKusuf(
            To1,
            latitude,
            longitude,
            elevation,
            deltaT,
            x0,
            x1,
            x2,
            y0,
            y1,
            y2,
            d0,
            d1,
            d2,
            m0, // M0
            m1, // M1
            l1_0, // L1
            l1_1, // L1
            l1_2, // L1
            l2_0, // L2
            l2_1, // L2
            l2_2, // L2
            tanF1
        )
    
    // takdil tsani li nihayah kusuf  
    val To2 = To1 + Pg
    val Ph = TakdilNihayahKusuf.takdilNihayahKusuf(
            To2,
            latitude,
            longitude,
            elevation,
            deltaT,
            x0,
            x1,
            x2,
            y0,
            y1,
            y2,
            d0,
            d1,
            d2,
            m0, // M0
            m1, // M1
            l1_0, // L1
            l1_1, // L1
            l1_2, // L1
            l2_0, // L2
            l2_1, // L2
            l2_2, // L2
            tanF1
        )
        
    // t akhir gerhana 
    val t3 = To2 + Ph
    val akhirGerhana = t0 + t3 + timeZone - deltaT / 3600
    val akhirGerhanaHMS = toTimeFullRound2(akhirGerhana)
    
    // cari hari
    val cjd = jd + timeZone / 24 + 0.5
    val zh = (cjd).toInt()
    val ff = cjd - zh
    val alpha = (((zh - 1867216.25) / 36524.25)).toInt()
    val Ac = if (zh < 2299161) {
    	zh
    } else {
    	(((zh + 1) + alpha) - (alpha / 4).toInt())
    }
    val Bc = Ac + 1524
    val cC = (((Bc - 122.1) / 365.25)).toInt()
    val Dc = ((365.25 * cC)).toInt()
    val Ec = (((Bc - Dc) / 30.6001)).toInt()
    val bulan = if (Ec < 14) {
    	(Ec - 1)
        } else {
        	(Ec - 13)
        }
    val tahun = if (bulan > 2) {
    	cC - 4716
    } else {
    	cC - 4715
    }
    val bulanString = numberJanuari(bulan)
    val tanggal = (Bc - Dc) - ((30.6001 * Ec)).toInt()
    val hari = (zh).mod(7)
    val hariString = numberSelasa(hari)
    val pasaran = (zh).mod(5)
    val pasaranString = numberPahing(pasaran)
    
    // irtifa' syams wa simtuha
    // data untuk U1 (output, bukan variable sebelumnya)
    val tU1 = t2
    val dU1 = (d0 + d1 * tU1 + d2 * (tU1).pow(2))
    val raU1 = m0 + m1 * tU1
    val hau1 = (raU1 + longitude - 0.00417807 * deltaT)
    // data untuk U2
    val tU2 = t - d
    val dU2 = (d0 + d1 * tU2 + d2 * (tU2).pow(2))
    val raU2 = m0 + m1 * tU2
    val hau2 = (raU2 + longitude - 0.00417807 * deltaT)
    // data untuk Max
    val tMax = t
    val dMax = (d0 + d1 * tMax + d2 * (tMax).pow(2))
    val raMax = m0 + m1 * tMax
    val hauMax = (raMax + longitude - 0.00417807 * deltaT)
    // data untuk U3
    val tU3 = t - d
    val dU3 = (d0 + d1 * tU3 + d2 * (tU3).pow(2))
    val raU3 = m0 + m1 * tU3
    val hau3 = (raU3 + longitude - 0.00417807 * deltaT)
    // data untuk U4
    val tU4 = t3
    val dU4 = (d0 + d1 * tU4 + d2 * (tU4).pow(2))
    val raU4 = m0 + m1 * tU4
    val hau4 = (raU4 + longitude - 0.00417807 * deltaT)
    
    // alt u1
    val tinggiAwalGerhana = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(dU1)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(dU1)) * cos(Math.toRadians(hau1))))
    val tinggiAwalGerhanaDMS = toDegreeFullRound2(tinggiAwalGerhana)
    val tinggiAwalGerhanaDegree = toDoubleDegree(tinggiAwalGerhana)
    // alt u2
    val tinggiAwalTotal = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(dU2)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(dU2)) * cos(Math.toRadians(hau2))))
    val tinggiAwalTotalDMS = toDegreeFullRound2(tinggiAwalTotal)
    val tinggiAwalTotalDegree = toDoubleDegree(tinggiAwalTotal)
    // alt Max
    val tinggiPuncak = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(dMax)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(dMax)) * cos(Math.toRadians(hauMax))))
    val tinggiPuncakDMS = toDegreeFullRound2(tinggiPuncak)
    val tinggiPuncakDegree = toDoubleDegree(tinggiPuncak)
    // alt u3
    val tinggiAkhirTotal = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(dU3)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(dU3)) * cos(Math.toRadians(hau3))))
    val tinggiAkhirTotalDMS = toDegreeFullRound2(tinggiAkhirTotal)
    val tinggiAkhirTotalDegree = toDoubleDegree(tinggiAkhirTotal)
    // alt u4
    val tinggiAkhirGerhana = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(dU4)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(dU4)) * cos(Math.toRadians(hau4))))
    val tinggiAkhirGerhanaDMS = toDegreeFullRound2(tinggiAkhirGerhana)
    val tinggiAkhirGerhanaDegree = toDoubleDegree(tinggiAkhirGerhana)
    
    // azimuth u1
    val xU1 = Math.toDegrees(sin(Math.toRadians(dU1)) * cos(Math.toRadians(latitude)) - cos(Math.toRadians(dU1)) * sin(Math.toRadians(latitude)) * cos(Math.toRadians(hau1)))
    val yU1 = Math.toDegrees(-cos(Math.toRadians(dU1)) * sin(Math.toRadians(hau1)))
    val aU1 = Math.toDegrees(atan(yU1 / xU1))
    
    val azimuthAwalGerhana = azimuth(xU1, yU1, aU1)
    val azimuthAwalGerhanaDMS = toDegreeFullRound2(azimuthAwalGerhana)
    val azimuthAwalGerhanaDegree = toDoubleDegree(azimuthAwalGerhana)
    // azimuth u2
    val xU2 = Math.toDegrees(sin(Math.toRadians(dU2)) * cos(Math.toRadians(latitude)) - cos(Math.toRadians(dU2)) * sin(Math.toRadians(latitude)) * cos(Math.toRadians(hau2)))
    val yU2 = Math.toDegrees(-cos(Math.toRadians(dU2)) * sin(Math.toRadians(hau2)))
    val aU2 = Math.toDegrees(atan(yU2 / xU2))
    
    val azimuthAwalTotal = azimuth(xU2, yU2, aU2)
    val azimuthAwalTotalDMS = toDegreeFullRound2(azimuthAwalTotal)
    val azimuthAwalTotalDegree = toDoubleDegree(azimuthAwalTotal)
    // azimuth Max
    val xMax = Math.toDegrees(sin(Math.toRadians(dMax)) * cos(Math.toRadians(latitude)) - cos(Math.toRadians(dMax)) * sin(Math.toRadians(latitude)) * cos(Math.toRadians(hauMax)))
    val yMax = Math.toDegrees(-cos(Math.toRadians(dMax)) * sin(Math.toRadians(hauMax)))
    val aMax = Math.toDegrees(atan(yMax / xMax))
    
    val azimuthPuncak = azimuth(xMax, yMax, aMax)
    val azimuthPuncakDMS = toDegreeFullRound2(azimuthPuncak)
    val azimuthPuncakDegree = toDoubleDegree(azimuthPuncak)
    // azimuth u3
    val xU3 = Math.toDegrees(sin(Math.toRadians(dU3)) * cos(Math.toRadians(latitude)) - cos(Math.toRadians(dU3)) * sin(Math.toRadians(latitude)) * cos(Math.toRadians(hau3)))
    val yU3 = Math.toDegrees(-cos(Math.toRadians(dU3)) * sin(Math.toRadians(hau3)))
    val aU3 = Math.toDegrees(atan(yU3 / xU3))
    
    val azimuthAkhirTotal = azimuth(xU3, yU3, aU3)
    val azimuthAkhirTotalDMS = toDegreeFullRound2(azimuthAkhirTotal)
    val azimuthAkhirTotalDegree = toDoubleDegree(azimuthAkhirTotal)
    // azimuth u4
    val xU4 = Math.toDegrees(sin(Math.toRadians(dU4)) * cos(Math.toRadians(latitude)) - cos(Math.toRadians(dU4)) * sin(Math.toRadians(latitude)) * cos(Math.toRadians(hau4)))
    val yU4 = Math.toDegrees(-cos(Math.toRadians(dU4)) * sin(Math.toRadians(hau4)))
    val aU4 = Math.toDegrees(atan(yU4 / xU4))
    
    val azimuthAkhirGerhana = azimuth(xU4, yU4, aU4)
    val azimuthAkhirGerhanaDMS = toDegreeFullRound2(azimuthAkhirGerhana)
    val azimuthAkhirGerhanaDegree = toDoubleDegree(azimuthAkhirGerhana)
    
    // durasi total gerhana (semua fase)
    val totalDurasiGM = akhirGerhana - awalGerhana
    val totalDurasiGMHMS = toTimeFullRound2(totalDurasiGM)
    // durasi total/cincin
    val durasiTotalGM = akhirTotal - awalTotal
    val durasiTotalGMHMS = toTimeFullRound2(durasiTotalGM)
    // obskurasi
    val obskurasi = Obsk
    
    
    }
