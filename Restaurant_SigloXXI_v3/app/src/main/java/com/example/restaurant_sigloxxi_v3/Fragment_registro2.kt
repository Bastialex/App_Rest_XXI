package com.example.restaurant_sigloxxi_v3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.restaurant_sigloxxi_v3.databinding.FragmentRegistro2Binding
import com.example.restaurant_sigloxxi_v3.modelo.Cliente
import java.sql.SQLException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_registro2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_registro2 : Fragment() {
    private var _binding: FragmentRegistro2Binding? = null
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
        _binding = FragmentRegistro2Binding.inflate(inflater, container, false)


        val view = binding.root
        activity?.setTitle("Restaurant Siglo XXI")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRealizarRegistro.setOnClickListener {
            //INICIALIZO
            val rut = binding.textInputEditTextRut.text.toString()
            val nombre = binding.textInputEditTextNombre.text.toString()
            val apellidoP = binding.textInputEditTextApPaterno.text.toString()
            val apellidoM = binding.textInputEditTextApMaterno.text.toString()
            val correo = binding.textInputEditTextCorreo.text.toString()
            val pass = binding.textInputEditTextPass.text.toString()
            val comuna: Int = 2
            // Creo la consulta y la guardo en addCliente

            //Reviso el valor
            try {
                Log.d("Mensaje","${rut}+${nombre}+${apellidoP}+${apellidoM}+${correo}+${pass}+${comuna.toString()}")
                if (Cliente().agregarCliente(
                        rut,
                        nombre,
                        apellidoP,
                        apellidoM,
                        correo,
                        pass,
                        comuna
                    )
                ) {
                    //Vuelvo al Login si es TRUE
                    val action =
                        Fragment_registro2Directions.actionFragmentRegistro2ToFragmentLogin2()
                    findNavController().navigate(action)
                } else {
                    //Mensaje de error si es false
                    Toast.makeText(activity, "No funcionó el agregar cliente", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: SQLException) {
                System.out.println("Oracle Server connection Close Error EXECUTE");
                e.printStackTrace();


            }

        }
    }


}
