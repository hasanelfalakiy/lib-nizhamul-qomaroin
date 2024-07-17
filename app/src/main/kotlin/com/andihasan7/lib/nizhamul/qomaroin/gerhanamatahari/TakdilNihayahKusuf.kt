package com.andihasan7.lib.nizhamul.qomaroin.gerhanamatahari

import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan
import kotlin.math.sqrt

object TakdilNihayahKusuf {
    
    fun takdilNihayahKusuf(
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
        l2_2: Double, // L2
        tanF1: Double 
    ): Double {

        val G = Math.toDegrees(atan(0.99664719 * tan(Math.toRadians(latitude))))
        val S1 =
            0.99664719 * sin(Math.toRadians(G)) +
                (elevation / 6378140) * sin(Math.toRadians(latitude))
        val C = cos(Math.toRadians(G)) + (elevation / 6378140) * cos(Math.toRadians(latitude))
        val X = x0 + x1 * T + x2 * (T).pow(2)
        val Y1 = y0 + y1 * T + y2 * (T).pow(2)
        val D1 = d0 + d1 * T + d2 * (T).pow(2)
        val M1 = m0 + m1 * T
        val L1 = l1_0 + l1_1 * T + l1_2 * (T).pow(2)
        val Q1 = l2_0 + l2_1 * T + l2_2 * (T).pow(2)
        val E1 = x1 + 2 * x2 * T
        val F = y1 + 2 * y2 * T
        val H = M1 + longitude - 0.00417807 * deltaT
        val Z1 = C * sin(Math.toRadians(H))
        val W1 = S1 * cos(Math.toRadians(D1)) - C * cos(Math.toRadians(H)) * sin(Math.toRadians(D1))
        val R_2 =
            S1 * sin(Math.toRadians(D1)) + C * cos(Math.toRadians(H)) * cos(Math.toRadians(D1))
        val I = 0.01745329 * m1 * C * cos(Math.toRadians(H))
        val J = 0.01745329 * (m1 * Z1 * sin(Math.toRadians(D1)) - R_2 * d1)
        val U1 = X - Z1
        val V1 = Y1 - W1
        val A1 = E1 - I
        val B1 = F - J
        val K1 = (A1).pow(2) + (B1).pow(2)
        val n1 = sqrt(K1)
        val e2 = L1 - R_2 * tanF1
        val m2 = (A1 * V1 - U1 * B1) / (n1 * e2)
        val Pe = -(U1 * A1 + V1 * B1) / K1 + (e2 / n1) * sqrt((1 - (m2).pow(2)))
        
        return Pe
    }
}
