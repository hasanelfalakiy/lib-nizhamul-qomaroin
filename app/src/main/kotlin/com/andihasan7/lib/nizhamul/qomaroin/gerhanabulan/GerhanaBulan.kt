/**
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
 
package com.andihasan7.lib.nizhamul.qomaroin.gerhanabulan

import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan
import com.andihasan7.lib.nizhamul.qomaroin.util.toTimeFullRoundSec
import com.andihasan7.lib.nizhamul.qomaroin.util.toDegreeFullRound2
import com.andihasan7.lib.nizhamul.qomaroin.util.toDoubleDegree
import com.andihasan7.lib.nizhamul.qomaroin.util.round
import com.andihasan7.lib.nizhamul.qomaroin.util.numberSelasa
import com.andihasan7.lib.nizhamul.qomaroin.util.numberPahing
import com.andihasan7.lib.nizhamul.qomaroin.util.numberJanuari
import com.andihasan7.lib.nizhamul.qomaroin.util.azimuthGB

/**
 * class GerhanaBulan input dimasukkan ke parameter constractor class
 *
 * ```
 *    latitude: Double, // lintang tempat
 *    longitude: Double, // bujur tempat
 *    elevation: Double, // tinggi tempat
 *    timeZone: Double, // time zone
 *    bulanHijri: Int, // bulan hijriyah
 *    tahunHijri: Int, // tahun hijriyah
 *    tanggalMiladi: Int, // tanggal masehi
 *    bulanMiladi: Int, // bulan masehi
 *    tahunMiladi: Int, // tahun masehi
 *    t0: Int, // T0
 *    tp: Int, // TP t = 1 total, p = 2 partial/sebagian, n = 3 penumbra
 *    jd: Double, // JD julian day
 *    deltaT: Double, // delta T
 *    x0: Double, // x0
 *    x1: Double, // x1
 *    y0: Double, // y0
 *    y1: Double, // y1
 *    l10: Double, // L10
 *    l11: Double, // L11
 *    l20: Double, // L20
 *    l21: Double, // L21
 *    l30: Double, // L30
 *    l31: Double, // L31
 *    sdb0: Double, // sdb0
 *    sdb1: Double, // sdb1
 *    m0: Double, // M0
 *    m1: Double, // M1
 *    dm0: Double, // dm0
 *    dm1: Double, // dm1
 *    ra0: Double, // Ra0
 *    ra1: Double, // Ra1
 *    hp0: Double, // Hp0
 *    hp1: Double // Hp1
 * ```
 */
class GerhanaBulan(
    latitude: Double,
    longitude: Double,
    elevation: Double,
    timeZone: Double,
    bulanHijri: Int,
    tahunHijri: Int,
    tanggalMiladi: Int,
    bulanMiladi: Int,
    tahunMiladi: Int,
    t0: Int, // T0
    tp: Int, // TP t = 1 total, p = 2 partial/sebagian, n = 3 penumbra
    jd: Double, // JD
    deltaT: Double,
    x0: Double,
    x1: Double,
    y0: Double,
    y1: Double,
    l10: Double,
    l11: Double,
    l20: Double,
    l21: Double,
    l30: Double,
    l31: Double,
    sdb0: Double,
    sdb1: Double,
    m0: Double,
    m1: Double,
    dm0: Double,
    dm1: Double,
    ra0: Double,
    ra1: Double,
    hp0: Double,
    hp1: Double
) {

    // hisab wasat khusuf
    val t = -(x0 * x1 + y0 * y1) / ((x1).pow(2) + (y1).pow(2))
    val tg = t0 + t - deltaT / 3600 // puncak gerhana
    val tgHMS = toTimeFullRoundSec(tg)
    
    // jenis gerhana, TP t = 1 total, p = 2 partial/sebagian, n = 3 penumbra
	/**
	 * jenis/tipe gerhana
	 */
    public val jenisGB: String = when (tp) {
    	1 -> "Gerhana Bulan Total"
        2 -> "Gerhana Bulan Sebagian"
        3 -> "Gerhana Bulan Penumbra"
        else -> "Tidak ada Gerhana"
    }

    // hisab miqdarul khusuf
    val s = l10 + l11 * t
    val x = l20 + l21 * t
    val y = l30 + l31 * t
    val z = sqrt((x0 + x1 * t).pow(2) + (y0 + y1 * t).pow(2))
    val k = sqrt((x1).pow(2) + (y1).pow(2))

    val t1 = sqrt(((s).pow(2) - (z).pow(2))) / k
    val t2 = sqrt(((x).pow(2) - (z).pow(2))) / k
    val t3 = sqrt(((y).pow(2) - (z).pow(2))) / k

    val sdb = sdb0 + sdb1 * t
	/**
	 * magnitude penumbra
	 */
    val magPenumbra: Double = (s - z) / (2 * sdb)
	/**
	 * magnitude penumbra dibulatkan 5 angka dibelakang koma
	 */
    public val magPenumbraRound: Double = magPenumbra.round(5)
	/**
	 * magnitude umbra
	 */
    public val magUmbra: Double = (x - z) / (2 * sdb)
	/**
	 * magnitude umbra dibulatkan 5 angka dibelakang koma
	 */
    public val magUmbraRound: Double = magUmbra.round(5)

    /**
	 * durasi penumbra
	 */
    public val durasiPenumbra: Double = t1 * 2
	/**
	 * durasi umbra
	 */
    public val durasiUmbra: Double = t2 * 2
	/**
	 * durasi total
	 */
    public val durasiTotal: Double = t3 * 2

    /**
	 * durasi penumbra HMS
	 */
    public val durasiPenumbraHMS: String = toTimeFullRoundSec(durasiPenumbra)
	/**
	 * durasi umbra HMS
	 */
    public val durasiUmbraHMS: String = toTimeFullRoundSec(durasiUmbra)
	/**
	 * durasi total HMS
	 */
    public val durasiTotalHMS: String = toTimeFullRoundSec(durasiTotal)

    /**
	 * radius penumbra
	 */
    public val radiusPenumbra: Double = s - sdb
	/**
	 * radius penumbra dibulatkan 5 angka dibelakang koma
	 */
    public val radiusPenumbraRound: Double = radiusPenumbra.round(5)
	/**
	 * radius umbra
	 */
    public val radiusUmbra: Double = x - sdb
	/**
	 * radius umbra dibulatkan 5 angka dibelakang koma
	 */
    public val radiusUmbraRound: Double = radiusUmbra.round(5)

    // hisab sa'ah bidayah wa nihayah khusuf
    // awal gerhana penumbra Universal Time (UTC)
    val p1 = (tg - t1).mod(24.0)
	/**
	 * jam awal penumbra Universal Time (UT)
	 */
    public val awalPenumbraUT: Double = p1
	/**
	 * jam awal penumbra UT format HH:MM:SS
	 */
    public val awalPenumbraUTHMS: String = toTimeFullRoundSec(p1)
    // awal gerhana penumbra Local Time
	/**
	 * jam awal penumbra Local Time (LT/WD) format HH:MM:SS
	 */
    public val awalPenumbraLokalHMS: String = toTimeFullRoundSec(p1 + timeZone)

    // awal gerhana umbra Universal Time (UTC)
    val u1 = (tg - t2).mod(24.0)
	/**
	 * jam awal umbra UT
	 */
    public val awalUmbraUT: Double = u1
	/**
	 * jam awal umbra HH:MM:SS
	 */
    public val awalUmbraUTHMS: String = toTimeFullRoundSec(u1)
	/**
	 * jam awal umbra LT/WD HH:MM:SS
	 */
    // awal umbra Local Time (UTC)
    public val awalUmbraLocalHMS: String = toTimeFullRoundSec(u1 + timeZone)

    // awal gerhana total Universal Time (UTC)
    val u2 = (tg - t3).mod(24.0)
	/**
	 * jam awal total UT
	 */
    public val awalTotalUT: Double = u2
	/**
	 * jam awal total HH:MM:SS
	 */
    public val awalTotalUTHMS: String = toTimeFullRoundSec(u2)
    // awal gerhana total Local Time (UTC)
	/**
	 * jam awal total LT/WD HH:MM:SS
	 */
    public val awalTotalLocalHMS: String = toTimeFullRoundSec(u2 + timeZone)

    // puncak gerhana Universal Time (UTC)
    val pu = (tg).mod(24.0)
	/**
	 * jam puncak gerhana UT
	 */
    public val puncakGerhanaUT: Double = pu
	/**
	 * jam puncak gerhana UT HH:MM:SS
	 */
    public val puncakGerhanaUTHMS: String = toTimeFullRoundSec(pu)
    // puncak gerhana Local Time (UTC)
	/**
	 * jam puncak gerhana LT HH:MM:SS
	 */
    public val puncakGerhanaLocalHMS: String = toTimeFullRoundSec(pu + timeZone)

    // akhir gerhana total Universal Time (UTC)
    val u3 = (tg + t3).mod(24.0)
	/**
	 * jam akhir total UT
	 */
    public val akhirTotalUT: Double = u3
	/**
	 * jam akhir total UT HH:MM:SS
	 */
    public val akhirTotalUTHMS: String = toTimeFullRoundSec(u3)
    // akhir gerhana total Local Time (UTC)
	/**
	 * jam akhir total LT HH:MM:SS
	 */
    public val akhirTotalLocalHMS: String = toTimeFullRoundSec(u3 + timeZone)

    // akhir gerhana umbra Universal Time (UTC)
    val u4 = (tg + t2).mod(24.0)
	/**
	 * jam akhir umbra UT
	 */
    public val akhirUmbraUT: Double = u4
	/**
	 * jam akhir umbra UT HMS
	 */
    public val akhirUmbraUTHMS: String = toTimeFullRoundSec(u4)
    // akhir gerhana umbra Local Time (UTC)
	/**
	 * jam akhir umbra LT HMS
	 */
    public val akhirUmbraLocalHMS: String = toTimeFullRoundSec(u4 + timeZone)

    // akhir gerhana penumbra Universal Time (UTC)
    val p4 = (tg + t1).mod(24.0)
	/**
	 * jam akhir penumbra UT
	 */
    public val akhirPenumbraUT: Double = p4
	/**
	 * jam akhir penumbra UT HMS
	 */
    public val akhirPenumbraUTHMS: String = toTimeFullRoundSec(p4)
    // akhir gerhana penumbra Local Time (UTC)
	/**
	 * jam akhir penumbra LT HMS
	 */
    public val akhirPenumbraLocalHMS: String = toTimeFullRoundSec(p4 + timeZone)

    val dip =
        if (elevation >= 0.0) {
            (1.76 / 60) * sqrt(elevation)
        } else {
            (1.76 / 60) * sqrt(0.0)
        }
		
		
		
    // hisab irtifa' wa samti irtifa' likulli marhalatil khusuf
    // alt & az puncak gerhana
    val tc = (m0 + m1 * t + longitude - 15.041 / 3600 * deltaT).mod(360.0)
    val dec = dm0 + dm1 * t
    val h =
        Math.toDegrees(
            asin(
                sin(Math.toRadians(latitude)) * sin(Math.toRadians(dec)) +
                    cos(Math.toRadians(latitude)) *
                        cos(Math.toRadians(dec)) *
                        cos(Math.toRadians(tc))
            )
        )
    val p = hp0 + hp1 * t
    val hh = h - (p * cos(Math.toRadians(h)))
    val rf = 0.01695 / tan(Math.toRadians(hh + 10.3 / (hh + 5.12555)))
    val h_h = hh + rf + dip
	/**
	 * tinggi hakiki puncak gerhana DD,D°
	 */
    val tinggiHakikiPuncakGerhana: String = toDoubleDegree(h)
	/**
	 * tinggi mar'i puncak gerhana DD,D°
	 */
    val tinggiMariPuncakGerhana: String = toDoubleDegree(h_h)
    val xMax =
        Math.toDegrees(
            (sin(Math.toRadians(dec)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(dec)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tc))))
        )
    val yMax = Math.toDegrees(-cos(Math.toRadians(dec)) * sin(Math.toRadians(tc)))
    val azMax = Math.toDegrees(atan(yMax / xMax))
	/**
	 * azimuth puncak
	 */
    val azimuthPuncakGerhana: Double = azimuthGB(xMax, yMax, azMax)
	/**
	 * azimuth puncak DMS
	 */
    val azimuthPuncakGerhanaDMS: String = toDegreeFullRound2(azimuthPuncakGerhana)
	/**
	 * azimuth puncak DD,D°
	 */
    val azimuthPuncakGerhanaDegree: String = toDoubleDegree(azimuthPuncakGerhana)
    
    // alt & az awal penumbra p1
    val tPen1 = (tc - m1 * t1).mod(360.0)
    val decPen1 = dec - dm1 * t1
    val hPen1 = Math.toDegrees(
            asin(
                sin(Math.toRadians(latitude)) * sin(Math.toRadians(decPen1)) +
                    cos(Math.toRadians(latitude)) *
                        cos(Math.toRadians(decPen1)) *
                        cos(Math.toRadians(tPen1))
            )
        )
    val pPen1 = p - hp1 * t1
    val hhPen1 = hPen1 - (pPen1 * cos(Math.toRadians(hPen1)))
    val rfPen1 = 0.01695 / tan(Math.toRadians(hhPen1 + 10.3 / (hhPen1 + 5.12555)))
    val h_hPen1 = hhPen1 + rf + dip
	/**
	 * tinggi hakiki awal penumbra DD,D°
	 */
    val tinggiHakikiAwalPenumbra: String = toDoubleDegree(hPen1)
	/**
	 * tinggi mar'i awal penumbra DD,D°
	 */
    val tinggiMariAwalPenumbra: String = toDoubleDegree(h_hPen1)
    val xPenum = Math.toDegrees(
            (sin(Math.toRadians(decPen1)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(decPen1)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tPen1))))
        )
    val yPenum = Math.toDegrees(-cos(Math.toRadians(decPen1)) * sin(Math.toRadians(tPen1)))
    val azPenum = Math.toDegrees(atan(yPenum / xPenum))
	/**
	 * azimuth awal penumbra
	 */
    val azimuthAwalPenumbra: Double = azimuthGB(xPenum, yPenum, azPenum)
	/**
	 * azimuth awal penumbra DMS
	 */
    val azimuthAwalPenumbraDMS: String = toDegreeFullRound2(azimuthAwalPenumbra)
	/**
	 * azimuth awal penumbra DD,D°
	 */
    val azimuthAwalPenumbraDegree: String = toDoubleDegree(azimuthAwalPenumbra)
    
    // alt & az awal umbra u1
    val tu1 = (tc - m1 * t2).mod(360.0)
    val decTu1 = dec - dm1 * t2
    val hU1 = Math.toDegrees(
            asin(
                sin(Math.toRadians(latitude)) * sin(Math.toRadians(decTu1)) +
                    cos(Math.toRadians(latitude)) *
                        cos(Math.toRadians(decTu1)) *
                        cos(Math.toRadians(tu1))
            )
        )
    val pu1 = p - hp1 * t2
    // h'
    val hhU1 = hU1 - (pu1 * cos(Math.toRadians(hU1)))
    val rfU1 = 0.01695 / tan(Math.toRadians(hhU1 + 10.3 / (hhU1 + 5.12555)))
    // h"
    val h_hU1 = hhU1 + rf + dip
	/**
	 * tinggi hakiki awal umbra DD,D°
	 */
    val tinggiHakikiAwalUmbra: String = toDoubleDegree(hU1)
	/**
	 * tinggi mar'i awal umbra DD,D°
	 */
    val tinggiMariAwalUmbra: String = toDoubleDegree(h_hU1)
    val xUmbra = Math.toDegrees(
            (sin(Math.toRadians(decTu1)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(decTu1)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tu1))))
        )
    val yUmbra = Math.toDegrees(-cos(Math.toRadians(decTu1)) * sin(Math.toRadians(tu1)))
    val azUmbra = Math.toDegrees(atan(yUmbra / xUmbra))
	/**
	 * azimuth awal umbra
	 */
    val azimuthAwalUmbra: Double = azimuthGB(xUmbra, yUmbra, azUmbra)
	/**
	 * azimuth awal umbra DMS
	 */
    val azimuthAwalUmbraDMS: String = toDegreeFullRound2(azimuthAwalUmbra)
	/**
	 * azimuth awal umbra DD,D°
	 */
    val azimuthAwalUmbraDegree: String = toDoubleDegree(azimuthAwalUmbra)
    
    // alt & az awal total u2
    val tu2 = (tc - m1 * t3).mod(360.0)
    val decTu2 = dec - dm1 * t3
    val hU2 = Math.toDegrees(
            asin(
                sin(Math.toRadians(latitude)) * sin(Math.toRadians(decTu2)) +
                    cos(Math.toRadians(latitude)) *
                        cos(Math.toRadians(decTu2)) *
                        cos(Math.toRadians(tu2))
            )
        )
    val pu2 = p - hp1 * t3
    // h'
    val hhU2 = hU2 - (pu2 * cos(Math.toRadians(hU2)))
    val rfU2 = 0.01695 / tan(Math.toRadians(hhU2 + 10.3 / (hhU2 + 5.12555)))
    // h"
    val h_hU2 = hhU2 + rf + dip
	/**
	 * tinggi hakiki awal total DD,D°
	 */
    val tinggiHakikiAwalTotal: String = toDoubleDegree(hU2)
	/**
	 * tinggi mar'i awal total DD,D°
	 */
    val tinggiMariAwalTotal: String = toDoubleDegree(h_hU2)
    val xU2 = Math.toDegrees(
            (sin(Math.toRadians(decTu2)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(decTu2)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tu2))))
        )
    val yU2 = Math.toDegrees(-cos(Math.toRadians(decTu2)) * sin(Math.toRadians(tu2)))
    val azU2 = Math.toDegrees(atan(yU2 / xU2))
	/**
	 * azimuth awal total
	 */
    val azimuthAwalTotal: Double = azimuthGB(xU2, yU2, azU2)
	/**
	 * azimuth awal total DMS
	 */
    val azimuthAwalTotalDMS: String = toDegreeFullRound2(azimuthAwalTotal)
	/**
	 * azimuth awal total DD,D°
	 */
    val azimuthAwalTotalDegree: String = toDoubleDegree(azimuthAwalTotal)
    
    // alt & az akhir total u3
    val tu3 = (tc + m1 * t3).mod(360.0)
    val decTu3 = dec + dm1 * t3
    val hU3 = Math.toDegrees(
            asin(
                sin(Math.toRadians(latitude)) * sin(Math.toRadians(decTu3)) +
                    cos(Math.toRadians(latitude)) *
                        cos(Math.toRadians(decTu3)) *
                        cos(Math.toRadians(tu3))
            )
        )
    val pu3 = p + hp1 * t3
    // h'
    val hhU3 = hU3 - (pu3 * cos(Math.toRadians(hU3)))
    val rfU3 = 0.01695 / tan(Math.toRadians(hhU3 + 10.3 / (hhU3 + 5.12555)))
    // h"
    val h_hU3 = hhU3 + rf + dip
	/**
	 * tinggi hakiki akhir total DD,D°
	 */
    val tinggiHakikiAkhirTotal: String = toDoubleDegree(hU3)
	/**
	 * tinggi mar'i akhir total DD,D°
	 */
    val tinggiMariAkhirTotal: String = toDoubleDegree(h_hU3)
    val xU3 = Math.toDegrees(
            (sin(Math.toRadians(decTu3)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(decTu3)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tu3))))
        )
    val yU3 = Math.toDegrees(-cos(Math.toRadians(decTu3)) * sin(Math.toRadians(tu3)))
    val azU3 = Math.toDegrees(atan(yU3 / xU3))
	/**
	 * azimuth akhir total
	 */
    val azimuthAkhirTotal: Double = azimuthGB(xU3, yU3, azU3)
	/**
	 * azimuth akhir total DMS
	 */
    val azimuthAkhirTotalDMS: String = toDegreeFullRound2(azimuthAkhirTotal)
	/**
	 * azimuth akhir total DD,D°
	 */
    val azimuthAkhirTotalDegree: String = toDoubleDegree(azimuthAkhirTotal)
    
    
    // alt & az akhir umbra u4
    val tu4 = (tc + m1 * t2).mod(360.0)
    val decTu4 = dec + dm1 * t2
    val hU4 = Math.toDegrees(
            asin(
                sin(Math.toRadians(latitude)) * sin(Math.toRadians(decTu4)) +
                    cos(Math.toRadians(latitude)) *
                        cos(Math.toRadians(decTu4)) *
                        cos(Math.toRadians(tu4))
            )
        )
    val pu4 = p + hp1 * t2
    // h'
    val hhU4 = hU4 - (pu4 * cos(Math.toRadians(hU4)))
    val rfU4 = 0.01695 / tan(Math.toRadians(hhU4 + 10.3 / (hhU4 + 5.12555)))
    // h"
    val h_hU4 = hhU4 + rf + dip
	/**
	 * tinggi hakiki akhir umbra DD,D°
	 */
    val tinggiHakikiAkhirUmbra: String = toDoubleDegree(hU4)
	/**
	 * tinggi mar'i akhir umbra DD,D°
	 */
    val tinggiMariAkhirUmbra: String = toDoubleDegree(h_hU4)
    val xU4 = Math.toDegrees(
            (sin(Math.toRadians(decTu4)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(decTu4)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tu4))))
        )
    val yU4 = Math.toDegrees(-cos(Math.toRadians(decTu4)) * sin(Math.toRadians(tu4)))
    val azU4 = Math.toDegrees(atan(yU4 / xU4))
	/**
	 * azimuth akhir umbra
	 */
    val azimuthAkhirUmbra: Double = azimuthGB(xU4, yU4, azU4)
	/**
	 * azimuth akhir umbra DMS
	 */
    val azimuthAkhirUmbraDMS: String = toDegreeFullRound2(azimuthAkhirUmbra)
	/**
	 * azimuth akhir umbra DD,D°
	 */
    val azimuthAkhirUmbraDegree: String = toDoubleDegree(azimuthAkhirUmbra)
    
    // alt & az akhir penumbra p4
    val tPen4 = (tc + m1 * t1).mod(360.0)
    val decPen4 = dec + dm1 * t1
    val hPen4 = Math.toDegrees(
            asin(
                sin(Math.toRadians(latitude)) * sin(Math.toRadians(decPen4)) +
                    cos(Math.toRadians(latitude)) *
                        cos(Math.toRadians(decPen4)) *
                        cos(Math.toRadians(tPen4))
            )
        )
    val pPen4 = p + hp1 * t1
    // h'
    val hhPen4 = hPen4 - (pPen4 * cos(Math.toRadians(hPen4)))
    val rfPen4 = 0.01695 / tan(Math.toRadians(hhPen4 + 10.3 / (hhPen4 + 5.12555)))
    // h"
    val h_hPen4 = hhPen4 + rf + dip
	/**
	 * tinggi hakiki akhir penumbra DD,D°
	 */
    val tinggiHakikiAkhirPenumbra: String = toDoubleDegree(hPen4)
	/**
	 * tinggi mar'i akhir penumbra DD,D°
	 */
    val tinggiMariAkhirPenumbra: String = toDoubleDegree(h_hPen4)
    val xPen4 = Math.toDegrees(
            (sin(Math.toRadians(decPen4)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(decPen4)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tPen4))))
        )
    val yPen4 = Math.toDegrees(-cos(Math.toRadians(decPen4)) * sin(Math.toRadians(tPen4)))
    val azPen4 = Math.toDegrees(atan(yPen4 / xPen4))
	/**
	 * azimuth akhir penumbra
	 */
    val azimuthAkhirPenumbra: Double = azimuthGB(xPen4, yPen4, azPen4)
	/**
	 * azimuth akhir penumbra DMS
	 */
    val azimuthAkhirPenumbraDMS: String = toDegreeFullRound2(azimuthAkhirPenumbra)
	/**
	 * azimuth akhir penumbra DD,D°
	 */
    val azimuthAkhirPenumbraDegree: String = toDoubleDegree(azimuthAkhirPenumbra)
    
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
	/**
	 * bulan masehi Int
	 */
    val bulanGB: Int = if (Ec < 14) {
    	(Ec - 1)
        } else {
        	(Ec - 13)
        }
	/**
	 * tahun masehi Int
	 */	
    val tahunGB: Int = if (bulanGB > 2) {
    	cC - 4716
    } else {
    	cC - 4715
    }
	/**
	 * bulan masehi String
	 */
    val bulanStringGB: String = numberJanuari(bulanGB)
	/**
	 * tanggal masehi Int
	 */
    val tanggalGB: Int = (Bc - Dc) - ((30.6001 * Ec)).toInt()
	/**
	 * hari Int
	 */
    val hariGB: Int = (zh).mod(7)
	/**
	 * hari String 
	 */
    val hariStringGB: String = numberSelasa(hariGB)
	/**
	 * pasaran Int
	 */
    public val pasaranGB: Int = (zh).mod(5)
	/**
	 * pasaran String 
	 * @return String
	 */
    public val pasaranStringGB: String = numberPahing(pasaranGB)
}
