package com.example.restaurant_sigloxxi_v3


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.restaurant_sigloxxi_v3.databinding.FragmentMenuPrincipalBinding
import com.example.restaurant_sigloxxi_v3.modelo.MenuRestaurant

class Fragment_menu_principal : Fragment() {
    private var _binding: FragmentMenuPrincipalBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuPrincipalBinding.inflate(inflater, container, false)
        val view = binding.root




        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        parentFragmentManager.setFragmentResultListener(
            "key",
            this,
            FragmentResultListener { requestKey, result ->
                val correo = result.getString("correo")

            binding.textViewTest.text = correo

            })


        var correo3 = binding.textViewTest.text.toString()

        var Menu: String
        var descripcionMenu: String
        var precioMenu: Int
        var cambiar: String = ""
        var arrayMenu: ArrayList<MenuRestaurant>
        arrayMenu = MenuRestaurant("", "", 0).listarmenu()
        var r1: ConstraintLayout =
            binding.layoutMenuPrincipal.findViewById(R.id.layoutMenuPrincipal)



        val lis: MutableList<String> = mutableListOf("")
        for (ar in arrayMenu) {
            precioMenu = ar.preciomenu
            Menu = "${ar.nombremenu} ---> $${precioMenu.toString()}"
            descripcionMenu = ar.desmenu.toString()

            lis.add(Menu)
            binding.list.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, lis)
        }




        binding.btnContinuarMenu.setOnClickListener {

            var correo = binding.textViewTest.text.toString()

            var bundle = Bundle()
            bundle.putString("t", correo)
            parentFragmentManager.setFragmentResult("k", bundle)

            val action =
                Fragment_menu_principalDirections.actionFragmentMenuPrincipal2ToIngresarReserva()

            Log.d("Mensaje","${correo}")
            findNavController().navigate(action)


        }

    }

}

