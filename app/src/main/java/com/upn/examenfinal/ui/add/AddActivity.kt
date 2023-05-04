package com.upn.examenfinal.ui.add

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.upn.examenfinal.MainActivity
import com.upn.examenfinal.dao.TablaDAO
import com.upn.examenfinal.databinding.ActivityAddBinding
import com.upn.examenfinal.entities.Tabla

class AddActivity : AppCompatActivity() {

    var tabla: Tabla? = null
    var modificar = false
    var id = 0

    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        asignarEventos()
        verificarModifica()
    }


    private fun verificarModifica() {
        if (intent.hasExtra("pid")) {
            modificar = true
            id = intent.getStringExtra("pid")!!.toInt()
            binding.txtCorreo.setText(intent.getStringExtra("pcorreo"))
            binding.txtUsuario.setText(intent.getStringExtra("pusuario"))
            binding.txtTicket.setText(intent.getStringExtra("pticket"))
            binding.txtCelular.setText(intent.getStringExtra("pcelular"))
        }
    }

    private fun asignarEventos() {
        binding.btnRegistrar.setOnClickListener { v ->
            if (capturarDatos()) {
                val tablaDAO = TablaDAO(this)
                tablaDAO.abrirBD()
                var message: String = ""
                tabla?.let {
                    message = if (!modificar) {
                        tablaDAO.registrarTabla(it)
                    } else {
                        tablaDAO.modificarTabla(it)
                    }
                }
                mostrarMensaje(message)
            }
        }
        binding.btnUPN.setOnClickListener { listener ->
            //val i = Intent(this, MapsActivity::class.java)
            //startActivity(i)
        }
    }

    private fun mostrarMensaje(mensaje: String) {
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar") { dialog: DialogInterface?, which: Int ->
            val intent = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(intent)

        }
        ventana.create().show()
    }

    private fun capturarDatos(): Boolean {
        val correo: String = binding.txtCorreo.getText().toString()
        val usuario: String = binding.txtUsuario.getText().toString()
        val ticket: String = binding.txtTicket.getText().toString()
        val celular: String = binding.txtCelular.getText().toString()
        var valida = true
        if (correo == "") {
            binding.txtCorreo.setError("Correo es requerido")
            valida = false
        }
        if (usuario == "") {
            binding.txtUsuario.setError("Usuario es requerido")
            valida = false
        }
        if (ticket == "") {
            binding.txtTicket.setError("Ticket es requerido")
            valida = false
        }
        if (celular == "") {
            binding.txtCelular.setError("Celular es requerido")
            valida = false
        }
        if (valida) {
            if (modificar == false) {
                tabla = Tabla(correo, usuario, ticket, celular.toInt())
            } else {
                tabla = Tabla(id, correo, usuario, ticket, celular.toInt())
            }
        }
        return valida
    }
}