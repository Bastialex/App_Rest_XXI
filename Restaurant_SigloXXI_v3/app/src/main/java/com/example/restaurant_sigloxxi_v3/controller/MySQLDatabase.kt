package com.example.restaurant_sigloxxi_v3.controller

import java.sql.Connection
import java.sql.DriverManager
import java.util.*

object MySQLDatabase {
     var conn: Connection? = null
    private var username = "username" // provide the username
    private var password = "password" // provide the corresponding password

    @JvmStatic fun main(args: Array<String>) {
        // make a connection to MySQL Server
        getConnection()
        // execute the query via connection object

    }


    fun getConnection() {
        val connectionProps = Properties()
        connectionProps.put("RestauranteXXI", username)
        connectionProps.put("admintienda", password)
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance()


//step2 create  the connection object
            val con = DriverManager.getConnection(
                "jdbc:oracle-36802-0.cloudclusters.net:thin:181.215.242.80:36802:xe",
                "RestauranteXXI",
                "admintienda")

//step3 create the statement object

//step3 create the statement object
            val stmt = con.createStatement()

//step4 execute query

//step4 execute query
            val rs = stmt.executeQuery("select * from temp_emp")
            while (rs.next()) println(
                rs.getInt(1).toString() + "  " + rs.getString(2) + "  " + rs.getString(3)
            )

//step5 close the connection object

//step5 close the connection object
            con.close()


/*

            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance()
            conn = DriverManager.getConnection(
                "jdbc:" + "oracle-36802-0.cloudclusters.net" + "://" +
                        "181.215.242.80" +
                        ":" + "36802" + "/" +
                        "",
                connectionProps)
                */


        } catch (ex: Exception) {

            ex.printStackTrace()
        }


        }
    }
