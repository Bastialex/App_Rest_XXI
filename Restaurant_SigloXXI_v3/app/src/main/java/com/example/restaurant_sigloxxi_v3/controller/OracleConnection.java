package com.example.restaurant_sigloxxi_v3.controller;


import android.util.Log;

import com.example.restaurant_sigloxxi_v3.modelo.MenuRestaurant;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class OracleConnection {
    Connection conn = null;
    int idMensaje = 0;

    public Boolean conectarJava() throws SQLException {

        String dbServer = "oracle-36802-0.cloudclusters.net"; // change it to your database server name
        int dbPort = 36802; // change it to your database server port
        String userName = "RestauranteXXI";
        String password = "admintienda";
        String url = String.format("jdbc:oracle:thin:@%s:%d:xe", dbServer, dbPort);

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(url, userName, password);

            Statement stmt = conn.createStatement();
            String sql;
            // create table
            //String sql = "create table bastian(NO char(20), name varchar(20),primary key(NO))";
            //int result = stmt.executeUpdate(sql);


            // query data
            sql = "select * from cliente";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs);
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" +
                        rs.getString(3)+ "\t" + rs.getString(4)+ "\t" + rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Oracle Server connection had an exception");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public Boolean cierraConexion() {
        try {
            if (conn.isClosed()) {
                return false;
            } else {
                try {
                    conn.close();
                    return true;
                } catch (SQLException e) {
                    System.out.println("Oracle Server connection Close Error 2");
                    e.printStackTrace();
                    return false;

                }
            }

        } catch (SQLException e) {
            System.out.println("Oracle Server connection Close Error 1");
            e.printStackTrace();
            return false;
        }

    }

    public Boolean agregarClienteDB(String rut, String nombre,
                                  String apellidoP, String apellidoM,
                                  String correo, String password, int comuna){
        String dbServer = "oracle-36802-0.cloudclusters.net";
        int dbPort = 36802;
        String userName = "RestauranteXXI";
        String passwordDB = "admintienda";
        String url = String.format("jdbc:oracle:thin:@%s:%d:xe", dbServer, dbPort);




        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(url, userName, passwordDB);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Oracle Server connection had an exception");
            e.printStackTrace();
        }

        // Insertamos los datos :D
        
        try {
            // Aqui preparo la cadena de SQL para ejecutarla mas adelante
            CallableStatement cst = conn.prepareCall("{call SP_CREATE_CLIENTE(?,?,?,?,?,?,?)}");

            //Vamos agregando los datos a la cadena SQL
            cst.setString(1,nombre);
            cst.setString(2,rut);
            cst.setString(3, apellidoP);
            cst.setString(4,apellidoM);
            cst.setString(5,correo);
            cst.setString(6,password);
            cst.setInt(7,comuna);

            /*
                Esto es por si hay Parametros de salida
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
                cst.registerOutParameter(2, );
            */
            try {
                cst.execute(); //<- me da error
            } catch (SQLException e){
                System.out.println("Oracle Server connection Close Error EXECUTE"+cst.getNString(1));
                e.printStackTrace();
                return false;
            }

            cst.close();
        } catch (SQLException e){
            System.out.println("Oracle Server connection Close Error Agregar");
            e.printStackTrace();
            return false;
        }

/*
        String sql;
           sql = "insert into bastian(NO,name) values('123','jocsan')";
         int result = stmt.executeUpdate(sql);
        sql = "insert into bastian(NO,name) values('456','diegomedina')";
        result = stmt.executeUpdate(sql);
*/

        return true;

    }

    public Boolean validarUsuarioBD(String correo, String password){
        String dbServer = "oracle-36802-0.cloudclusters.net"; // change it to your database server name
        int dbPort = 36802; // change it to your database server port
        String userName = "RestauranteXXI";
        String passwordDB = "admintienda";
        String url = String.format("jdbc:oracle:thin:@%s:%d:xe", dbServer, dbPort);
        String unstring = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, userName, passwordDB);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Oracle Server connection had an exception");
            e.printStackTrace();
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT CONTRASENIA FROM CLIENTE WHERE CORREO_ELECTRONICO = '"+correo+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                 unstring = rs.getString(1);
                break;
            }
            if(unstring != null && unstring.equals(password)){
                System.out.println(""+password+unstring);
                return true;
            }

        } catch (SQLException e){
            System.out.println("Oracle server error statement  del validar usuario");
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<MenuRestaurant> listarMenu()
    {
        String dbServer = "oracle-36802-0.cloudclusters.net"; // change it to your database server name
        int dbPort = 36802; // change it to your database server port
        String userName = "RestauranteXXI";
        String passwordDB = "admintienda";
        String url = String.format("jdbc:oracle:thin:@%s:%d:xe", dbServer, dbPort);
        String nombredelmenu = "";
        String descripciondelmenu = "";
        int preciodelmenu = 0;

        //Array para los datos del menu
        ArrayList<MenuRestaurant> a = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, userName, passwordDB);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Oracle Server connection had an exception");
            e.printStackTrace();
        }



        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT NOMBRE_MENU , DESCRIPCION_MENU, PRECIO_MENU FROM menu";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                nombredelmenu = rs.getString(1);
                descripciondelmenu = rs.getString(2);
                preciodelmenu = rs.getInt(3);

                MenuRestaurant mr = new MenuRestaurant(nombredelmenu,descripciondelmenu,preciodelmenu);
                a.add(mr);
            }

        } catch (SQLException e){
            System.out.println("Oracle server error statement  del validar usuario");
            e.printStackTrace();
        }



        return a;
    }



    public Boolean ingresarReserva(Date fecha, int mesa, String correo){
        String dbServer = "oracle-36802-0.cloudclusters.net";
        int dbPort = 36802;
        String userName = "RestauranteXXI";
        String passwordDB = "admintienda";
        String url = String.format("jdbc:oracle:thin:@%s:%d:xe", dbServer, dbPort);
        int id_cliente = 0;


        System.out.println(fecha.toString()+mesa+correo);
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(url, userName, passwordDB);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Oracle Server connection had an exception");
            e.printStackTrace();
        }

        try {
            CallableStatement cst1 = conn.prepareCall("{call SP_BUSCAR_CLIENTE(?,?)}");
            cst1.setString(1, correo);
            cst1.registerOutParameter(2, OracleTypes.INTEGER);

                    /*
                Esto es por si hay Parametros de salida
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
                cst.registerOutParameter(2, );
            */
            try {
                cst1.execute();
            } catch (SQLException e){
                System.out.println("Oracle Server connection Close Error EXECUTE"+correo);
                e.printStackTrace();
                return false;
            }

            id_cliente = cst1.getInt(2);

        }  catch (SQLException e){
            System.out.println("Oracle Server connection Close Error Reserva");
            e.printStackTrace();
            return false;
        }


        try {
            // Aqui preparo la cadena de SQL para ejecutarla mas adelante
            CallableStatement cst = conn.prepareCall("{call SP_CREATE_RESERVA(?,?,?)}");

            //Vamos agregando los datos a la cadena SQL
            cst.setDate(1,fecha);
            cst.setInt(2,mesa);
            cst.setInt(3, id_cliente);

            try {
                cst.execute();
            } catch (SQLException e){
                System.out.println("Oracle Server connection Close Error EXECUTE"+cst.getNString(1));
                e.printStackTrace();
                return false;
            }

            cst.close();
            conn.close();
        } catch (SQLException e){
            System.out.println("Oracle Server connection Close Error Reserva");
            e.printStackTrace();
            return false;
        }





        return true;

    }

    public ArrayList<String> listarMesas()
    {
        String dbServer = "oracle-36802-0.cloudclusters.net"; // change it to your database server name
        int dbPort = 36802; // change it to your database server port
        String userName = "RestauranteXXI";
        String passwordDB = "admintienda";
        String url = String.format("jdbc:oracle:thin:@%s:%d:xe", dbServer, dbPort);




        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, userName, passwordDB);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Oracle Server connection had an exception");
            e.printStackTrace();
        }

        String id_mesa = "";
        int numeroMesa = 0;
        String unidos = "";
        ArrayList<String> ar = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT ID_MESA FROM mesa";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                id_mesa = rs.getString(1);

Log.d("Mensaje",id_mesa);
                unidos = id_mesa;
                if (ar != null) {
                    ar.add(unidos);
                }

            }
            return ar;

        } catch (SQLException e){
            System.out.println("Oracle server error statement  del validar usuario");
            e.printStackTrace();
        }



        return null;
    }

}


