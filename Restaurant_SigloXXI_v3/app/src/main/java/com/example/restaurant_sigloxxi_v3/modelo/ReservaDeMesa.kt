package com.example.restaurant_sigloxxi_v3.modelo

import android.util.Log
import com.example.restaurant_sigloxxi_v3.controller.OracleConnection
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ReservaDeMesa {

    private var mesa: Int = 0
    private var fechayhora: Date = Date()
    private var correo: String = ""



    fun reservarMesa(hora1: Int, minutos1: Int, fecha1: String, correo1: String, mesa1: String): Boolean
    {
        var fecha: String = ""+ fecha1 +" "+hora1+":"+minutos1

            fechayhora = SimpleDateFormat("dd-mm-yyy hh:mm").parse(fecha)
            mesa = mesa1.toInt()
            var date2: java.sql.Date = java.sql.Date(fechayhora.time)
        Log.d("Mensaje","${correo1}")
        return OracleConnection().ingresarReserva(date2,mesa,correo1)
    }

    fun listarMesas(): MutableList<String> {
       try {
           return OracleConnection().listarMesas()
       } catch (e: NullPointerException){
           var a: MutableList<String> = ArrayList()
           return a
       }




    }


}