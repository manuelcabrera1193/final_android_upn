package com.upn.examenfinal.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.upn.examenfinal.dao.TablaDAO
import com.upn.examenfinal.databinding.FragmentHomeBinding
import com.upn.examenfinal.entities.Tabla
import com.upn.examenfinal.ui.add.AddActivity
import com.upn.examenfinal.util.AdaptadorPersonalizado

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    lateinit var tablaDAO : TablaDAO
    var listaTabla: List<Tabla> = ArrayList()
    var adaptador: AdaptadorPersonalizado? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        addData()
        binding.btnNuevo.setOnClickListener { v: View? ->
            val intent = Intent(requireContext(), AddActivity::class.java)
            startActivity(intent)
        }

        return root

    }

    fun addData(){
        tablaDAO = TablaDAO(requireContext())
        tablaDAO.abrirBD()
        listaTabla = tablaDAO.cargarTabla()
        adaptador = AdaptadorPersonalizado(requireContext(), listaTabla)
        binding.rvTabla.adapter = adaptador
        binding.rvTabla.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onResume() {
        super.onResume()
        addData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}