package com.example.restaurant_sigloxxi_v3.modelo

import com.example.restaurant_sigloxxi_v3.controller.OracleConnection

class MenuRestaurant(
    var nombremenu: String,
    var desmenu : String?,
    var preciomenu: Int
) {
    fun listarmenu(): ArrayList<MenuRestaurant>{
        return OracleConnection().listarMenu()
    }


}