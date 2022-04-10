package com.example.restaurant_sigloxxi_v3.modelo

import android.database.SQLException
import android.util.Log
import com.example.restaurant_sigloxxi_v3.controller.OracleConnection

class Cliente {
    private var rut_cliente: String = ""
    private var nombre_cliente: String = ""
    private var apellidoPa: String = ""
    private var apellidoMa: String = ""
    private var correoCliente: String = ""
    private var password: String = ""
    private var id_comuna: Int = 2


        fun agregarCliente(
            rut: String, nombre: String,
            apellidoP: String, apellidoM: String,
            correo: String, pass: String, comuna: Int
        ): Boolean {
            // nombre_cliente, rutcliente, apellidoP,
            // this.apellidoM,this.correoCliente,
            // this.Password,this.id_comuna
            rut_cliente = rut
            nombre_cliente = nombre
            apellidoPa = apellidoP
            apellidoMa = apellidoM
            correoCliente = correo
            password = pass
            id_comuna = comuna


            try {
                val db = OracleConnection()
                if (db.agregarClienteDB(
                        rut_cliente,
                        nombre_cliente,
                        apellidoPa,
                        apellidoMa,
                        correoCliente,
                        password,
                        id_comuna
                    )
                ) {
                    Log.d("Mensaje de error en clase", "Resultó el agregar")
                }
                db.cierraConexion()

            } catch (e: SQLException) {
                Log.d("Mensaje de error en clase", "$e")
                return false
            }
            return true
        }

        fun validarUsuario(correoUsuario: String, passUsuario: String): Boolean {
            val database = OracleConnection()

            if (database.validarUsuarioBD(correoUsuario, passUsuario)) {
                return true
            }
            return false

        }


    }


    class Comuna {
        var idComuna: Int = 3
        var nombreComuna: String = "El Bosque"
        var region: Int = 7


    }









