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
 
package com.andihasan7.lib.nizhamul.qomaroin.gerhanamatahari

import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan

object TakdilWasatSyams {
    
	/**
	 * fungsi koreksi wasat syams
	 */
    fun takdilWasatSyams(
        T: Double,
        latitude: Double,
        longitude: Double,
        elevation: Double,
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
        l2_2: Double // L2
    ): DoubleArray {

        val G = Math.toDegrees(atan(0.99664719 * tan(Math.toRadians(latitude))))
        val S =
            0.99664719 * sin(Math.toRadians(G)) +
                ((elevation).toDouble() / 6378140) * sin(Math.toRadians(latitude))
        val C =
            cos(Math.toRadians(G)) +
                ((elevation).toDouble() / 6378140) * cos(Math.toRadians(latitude))
        val X = x0 + x1 * T + x2 * (T).pow(2)
        val Y = y0 + y1 * T + y2 * (T).pow(2)
        val D = d0 + d1 * T + d2 * (T).pow(2)
        val M = m0 + m1 * T
        val L = l1_0 + l1_1 * T + l1_2 * (T).pow(2)
        val Q = l2_0 + l2_1 * T + l2_2 * (T).pow(2)
        val E = x1 + 2 * x2 * T
        val F = y1 + 2 * y2 * T
        val H = M + longitude - 0.00417807 * deltaT
        val Z = C * sin(Math.toRadians(H))
        val W = S * cos(Math.toRadians(D)) - C * cos(Math.toRadians(H)) * sin(Math.toRadians(D))
        val R = S * sin(Math.toRadians(D)) + C * cos(Math.toRadians(H)) * cos(Math.toRadians(D))
        val I = 0.01745329 * m1 * C * cos(Math.toRadians(H))
        val J = 0.01745329 * (m1 * Z * sin(Math.toRadians(D)) - R * d1)
        val U = X - Z
        val V = Y - W
        val A = E - I
        val B = F - J
        val N = (A).pow(2) + (B).pow(2)
        val P_return = -(U * A + V * B) / N

        return doubleArrayOf(P_return, U, V, A, B, L, Q, R, N)
    }
}
