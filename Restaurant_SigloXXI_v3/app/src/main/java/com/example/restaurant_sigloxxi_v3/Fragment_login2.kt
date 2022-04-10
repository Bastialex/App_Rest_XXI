package com.example.restaurant_sigloxxi_v3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.restaurant_sigloxxi_v3.controller.OracleConnection
import com.example.restaurant_sigloxxi_v3.databinding.FragmentLogin2Binding
import com.example.restaurant_sigloxxi_v3.modelo.Cliente


class Fragment_login2 : Fragment() {
    private var _binding: FragmentLogin2Binding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        // Use the Kotlin extension in the fragment-ktx artifact

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogin2Binding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.btnRegistrar.setOnClickListener {

            val action = Fragment_login2Directions.actionFragmentLogin2ToFragmentRegistro2()
            findNavController().navigate(action)
        }

        binding.btnLogin.setOnClickListener {
            val correo: String = binding.username.text.toString()
            val password: String = binding.password.text.toString()
            if (Cliente().validarUsuario(correoUsuario = correo, passUsuario = password)) {

                val bundle = Bundle()
                bundle.putString("correo", correo)
                parentFragmentManager.setFragmentResult("key", bundle)

                val action =
                    Fragment_login2Directions.actionFragmentLogin2ToFragmentMenuPrincipal2()

                findNavController().navigate(action)
            } else {

                Toast.makeText(activity, "Usuario incorrecto", Toast.LENGTH_SHORT).show()
            }

        }

        binding.textViewRecoverPass.setOnClickListener {
            if (OracleConnection().conectarJava()) {
                Log.d("Mensaje", "Listados en el TERMINAL")
            } else {
                Toast.makeText(activity, "No fueron listados en el TERMINAL", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

