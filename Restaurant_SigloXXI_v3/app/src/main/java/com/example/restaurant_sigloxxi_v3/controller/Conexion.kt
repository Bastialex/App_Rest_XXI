package com.example.restaurant_sigloxxi_v3.controller

import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class Conexion {
    var conn: Connection? = null
    private var dbServer =
        "oracle-36802-0.cloudclusters.net" // change it to your database server name

    private var dbPort = 36802 // change it to your database server port

    private var userName: String = "RestauranteXXI"
    private var password = "admintienda"
    private var url = String.format("jdbc:oracle:thin:@%s:%d:xe", dbServer, dbPort)

    fun realizarConexion(): Boolean {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver")
            Log.d(
                "Mensaje",
                "Entramos al Try"
            )
            conn = DriverManager.getConnection(url, userName, password)
            Log.d("Mensaje", "Conexion realizada ${conn.toString()}")

            return true
        } catch (e: NumberFormatException) {
            Log.d("Mensaje-Bastian", "${e.toString()}")
            return false
        } finally {
            try {
                conn?.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

    }


}