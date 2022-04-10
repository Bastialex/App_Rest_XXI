package com.example.restaurant_sigloxxi_v3

import android.R
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.restaurant_sigloxxi_v3.databinding.FragmentIngresarReservaBinding
import com.example.restaurant_sigloxxi_v3.modelo.ReservaDeMesa
import java.util.*

// TODO: Rename parameter arguments, choose names that match


/**
 * A simple [Fragment] subclass.
 * Use the [IngresarReserva.newInstance] factory method to
 * create an instance of this fragment.
 */
class IngresarReserva : Fragment() {
    private var _binding: FragmentIngresarReservaBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIngresarReservaBinding.inflate(inflater, container, false)



        val view = binding.root

        return view


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        parentFragmentManager.setFragmentResultListener(
            "k",
            this,
            FragmentResultListener { requestKey, result ->
                val ayuda = result.getString("t")
                binding.textViewModificable.text = ayuda.toString()
            })



        var a: MutableList<String> = ReservaDeMesa().listarMesas()

        var arrais = a
        var spin = binding.spinner2
        spin.adapter = ArrayAdapter<String>(requireContext(),
            R.layout.simple_spinner_dropdown_item,arrais)

//// HACE CLICK en el boton
        binding.btnRealizarRegistro.setOnClickListener(){

            var hora = binding.dateHora.hour
            var minutos = binding.dateHora.minute
            if(hora<11 || hora>20){
                Toast.makeText(activity, "Hora fuera de rango 11->20 hrs", Toast.LENGTH_SHORT).show()
            } else{

                var dia = binding.dateFecha.dayOfMonth
                val mes = binding.dateFecha.month
                val anio = binding.dateFecha.year
                val presenteA = Calendar.YEAR
                val presenteM = Calendar.MONTH


                if(binding.dateFecha.year < presenteA || binding.dateFecha.month < presenteM )
                {
                    Toast.makeText(activity, "Fecha fuera de rango", Toast.LENGTH_SHORT).show()
                } else
                {

                    Log.d("MENSAJE FINAL","3 ${binding.textViewModificable.text}")

                    /*

                        val date = SimpleDateFormat("dd-MM-yyyy").parse("14-02-2018")
                         println(date.time)

                    */

                   val correo = binding.textViewModificable.text.toString()

                    var elSpinner = binding.spinner2.selectedItem.toString()
                    val fechaUnida: String = "${dia}-${mes}-${anio}"
                    ReservaDeMesa().reservarMesa(hora,minutos,fechaUnida,correo,elSpinner)

                    val action = IngresarReservaDirections.actionIngresarReservaToFragmentReservaRealizada()

                    findNavController().navigate(action)

                }

            }

        }


    }


}