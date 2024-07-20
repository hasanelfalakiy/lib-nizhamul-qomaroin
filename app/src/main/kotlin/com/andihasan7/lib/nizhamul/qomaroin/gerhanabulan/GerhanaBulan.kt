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
    val jenisGB = when (tp) {
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
    val magPenumbra = (s - z) / (2 * sdb)
    val magPenumbraRound = magPenumbra.round(5)
    val magUmbra = (x - z) / (2 * sdb)
    val magUmbraRound = magUmbra.round(5)

    val durasiPenumbra = t1 * 2
    val durasiUmbra = t2 * 2
    val durasiTotal = t3 * 2

    val durasiPenumbraHMS = toTimeFullRoundSec(durasiPenumbra)
    val durasiUmbraHMS = toTimeFullRoundSec(durasiUmbra)
    val durasiTotalHMS = toTimeFullRoundSec(durasiTotal)

    val radiusPenumbra = s - sdb
    val radiusPenumbraRound = radiusPenumbra.round(5)
    val radiusUmbra = x - sdb
    val radiusUmbraRound = radiusUmbra.round(5)

    // hisab sa'ah bidayah wa nihayah khusuf
    // awal gerhana penumbra Universal Time (UTC)
    val p1 = (tg - t1).mod(24.0)
    val awalPenumbraUT = p1
    val awalPenumbraUTHMS = toTimeFullRoundSec(p1)
    // awal gerhana penumbra Local Time
    val awalPenumbraLokalHMS = toTimeFullRoundSec(p1 + timeZone)

    // awal gerhana umbra Universal Time (UTC)
    val u1 = (tg - t2).mod(24.0)
    val awalUmbraUT = u1
    val awalUmbraUTHMS = toTimeFullRoundSec(u1)
    // awal gerhana umbra Local Time (UTC)
    val awalUmbraLocalHMS = toTimeFullRoundSec(u1 + timeZone)

    // awal gerhana total Universal Time (UTC)
    val u2 = (tg - t3).mod(24.0)
    val awalTotalUT = u2
    val awalTotalUTHMS = toTimeFullRoundSec(u2)
    // awal gerhana total Local Time (UTC)
    val awalTotalLocalHMS = toTimeFullRoundSec(u2 + timeZone)

    // puncak gerhana Universal Time (UTC)
    val pu = (tg).mod(24.0)
    val puncakGerhanaUT = pu
    val puncakGerhanaUTHMS = toTimeFullRoundSec(pu)
    // puncak gerhana Local Time (UTC)
    val puncakGerhanaLocalHMS = toTimeFullRoundSec(pu + timeZone)

    // akhir gerhana total Universal Time (UTC)
    val u3 = (tg + t3).mod(24.0)
    val akhirTotalUT = u3
    val akhirTotalUTHMS = toTimeFullRoundSec(u3)
    // akhir gerhana total Local Time (UTC)
    val akhirTotalLocalHMS = toTimeFullRoundSec(u3 + timeZone)

    // akhir gerhana umbra Universal Time (UTC)
    val u4 = (tg + t2).mod(24.0)
    val akhirUmbraUT = u4
    val akhirUmbraUTHMS = toTimeFullRoundSec(u4)
    // akhir gerhana umbra Local Time (UTC)
    val akhirUmbraLocalHMS = toTimeFullRoundSec(u4 + timeZone)

    // akhir gerhana penumbra Universal Time (UTC)
    val p4 = (tg + t1).mod(24.0)
    val akhirPenumbraUT = p4
    val akhirPenumbraUTHMS = toTimeFullRoundSec(p4)
    // akhir gerhana penumbra Local Time (UTC)
    val akhirPenumbraLocalHMS = toTimeFullRoundSec(p4 + timeZone)

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
    val tinggiHakikiPuncakGerhana = toDoubleDegree(h)
    val tinggiMariPuncakGerhana = toDoubleDegree(h_h)
    val xMax =
        Math.toDegrees(
            (sin(Math.toRadians(dec)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(dec)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tc))))
        )
    val yMax = Math.toDegrees(-cos(Math.toRadians(dec)) * sin(Math.toRadians(tc)))
    val azMax = Math.toDegrees(atan(yMax / xMax))
    val azimuthPuncakGerhana = azimuthGB(xMax, yMax, azMax)
    val azimuthPuncakGerhanaDMS = toDegreeFullRound2(azimuthPuncakGerhana)
    val azimuthPuncakGerhanaDegree = toDoubleDegree(azimuthPuncakGerhana)
    
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
    val tinggiHakikiAwalPenumbra = toDoubleDegree(hPen1)
    val tinggiMariAwalPenumbra = toDoubleDegree(h_hPen1)
    val xPenum = Math.toDegrees(
            (sin(Math.toRadians(decPen1)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(decPen1)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tPen1))))
        )
    val yPenum = Math.toDegrees(-cos(Math.toRadians(decPen1)) * sin(Math.toRadians(tPen1)))
    val azPenum = Math.toDegrees(atan(yPenum / xPenum))
    val azimuthAwalPenumbra = azimuthGB(xPenum, yPenum, azPenum)
    val azimuthAwalPenumbraDMS = toDegreeFullRound2(azimuthAwalPenumbra)
    val azimuthAwalPenumbraDegree = toDoubleDegree(azimuthAwalPenumbra)
    
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
    val tinggiHakikiAwalUmbra = toDoubleDegree(hU1)
    val tinggiMariAwalUmbra = toDoubleDegree(h_hU1)
    val xUmbra = Math.toDegrees(
            (sin(Math.toRadians(decTu1)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(decTu1)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tu1))))
        )
    val yUmbra = Math.toDegrees(-cos(Math.toRadians(decTu1)) * sin(Math.toRadians(tu1)))
    val azUmbra = Math.toDegrees(atan(yUmbra / xUmbra))
    val azimuthAwalUmbra = azimuthGB(xUmbra, yUmbra, azUmbra)
    val azimuthAwalUmbraDMS = toDegreeFullRound2(azimuthAwalUmbra)
    val azimuthAwalUmbraDegree = toDoubleDegree(azimuthAwalUmbra)
    
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
    val tinggiHakikiAwalTotal = toDoubleDegree(hU2)
    val tinggiMariAwalTotal = toDoubleDegree(h_hU2)
    val xU2 = Math.toDegrees(
            (sin(Math.toRadians(decTu2)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(decTu2)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tu2))))
        )
    val yU2 = Math.toDegrees(-cos(Math.toRadians(decTu2)) * sin(Math.toRadians(tu2)))
    val azU2 = Math.toDegrees(atan(yU2 / xU2))
    val azimuthAwalTotal = azimuthGB(xU2, yU2, azU2)
    val azimuthAwalTotalDMS = toDegreeFullRound2(azimuthAwalTotal)
    val azimuthAwalTotalDegree = toDoubleDegree(azimuthAwalTotal)
    
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
    val tinggiHakikiAkhirTotal = toDoubleDegree(hU3)
    val tinggiMariAkhirTotal = toDoubleDegree(h_hU3)
    val xU3 = Math.toDegrees(
            (sin(Math.toRadians(decTu3)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(decTu3)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tu3))))
        )
    val yU3 = Math.toDegrees(-cos(Math.toRadians(decTu3)) * sin(Math.toRadians(tu3)))
    val azU3 = Math.toDegrees(atan(yU3 / xU3))
    val azimuthAkhirTotal = azimuthGB(xU3, yU3, azU3)
    val azimuthAkhirTotalDMS = toDegreeFullRound2(azimuthAkhirTotal)
    val azimuthAkhirTotalDegree = toDoubleDegree(azimuthAkhirTotal)
    
    
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
    val tinggiHakikiAkhirUmbra = toDoubleDegree(hU4)
    val tinggiMariAkhirUmbra = toDoubleDegree(h_hU4)
    val xU4 = Math.toDegrees(
            (sin(Math.toRadians(decTu4)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(decTu4)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tu4))))
        )
    val yU4 = Math.toDegrees(-cos(Math.toRadians(decTu4)) * sin(Math.toRadians(tu4)))
    val azU4 = Math.toDegrees(atan(yU4 / xU4))
    val azimuthAkhirUmbra = azimuthGB(xU4, yU4, azU4)
    val azimuthAkhirUmbraDMS = toDegreeFullRound2(azimuthAkhirUmbra)
    val azimuthAkhirUmbraDegree = toDoubleDegree(azimuthAkhirUmbra)
    
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
    val tinggiHakikiAkhirPenumbra = toDoubleDegree(hPen4)
    val tinggiMariAkhirPenumbra = toDoubleDegree(h_hPen4)
    val xPen4 = Math.toDegrees(
            (sin(Math.toRadians(decPen4)) * cos(Math.toRadians(latitude)) -
                (cos(Math.toRadians(decPen4)) *
                    sin(Math.toRadians(latitude)) *
                    cos(Math.toRadians(tPen4))))
        )
    val yPen4 = Math.toDegrees(-cos(Math.toRadians(decPen4)) * sin(Math.toRadians(tPen4)))
    val azPen4 = Math.toDegrees(atan(yPen4 / xPen4))
    val azimuthAkhirPenumbra = azimuthGB(xPen4, yPen4, azPen4)
    val azimuthAkhirPenumbraDMS = toDegreeFullRound2(azimuthAkhirPenumbra)
    val azimuthAkhirPenumbraDegree = toDoubleDegree(azimuthAkhirPenumbra)
    
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
    val bulanGB = if (Ec < 14) {
    	(Ec - 1)
        } else {
        	(Ec - 13)
        }
    val tahunGB = if (bulanGB > 2) {
    	cC - 4716
    } else {
    	cC - 4715
    }
    val bulanStringGB = numberJanuari(bulanGB)
    val tanggalGB = (Bc - Dc) - ((30.6001 * Ec)).toInt()
    val hariGB = (zh).mod(7)
    val hariStringGB = numberSelasa(hariGB)
    val pasaranGB = (zh).mod(5)
    val pasaranStringGB = numberPahing(pasaranGB)
}
