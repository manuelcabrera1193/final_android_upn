package com.upn.examenfinal.util

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.upn.examenfinal.MainActivity
import com.upn.examenfinal.R
import com.upn.examenfinal.dao.TablaDAO
import com.upn.examenfinal.entities.Tabla
import com.upn.examenfinal.ui.add.AddActivity


class AdaptadorPersonalizado(private val context: Context, listaTabla: List<Tabla>) :
    RecyclerView.Adapter<AdaptadorPersonalizado.MiViewHolder>() {
    private var listaTabla: List<Tabla> = ArrayList<Tabla>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val inflater = LayoutInflater.from(context)
        val vista: View = inflater.inflate(R.layout.fila, parent, false)
        return MiViewHolder(vista)
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        holder.filaCorreo.setText(listaTabla[position].correo.toString() + "")
        holder.filaUsuario.setText(listaTabla[position].usuario.toString() + "")
        holder.filaTicket.setText(listaTabla[position].ticket.toString() + "")
        holder.filaCelular.setText(listaTabla[position].celular.toString() + "")
        holder.filaEditar.setOnClickListener { v: View? ->
            val intent = Intent(context, AddActivity::class.java)
            intent.putExtra("pid", listaTabla[position].id.toString() + "")
            intent.putExtra("pcorreo", listaTabla[position].correo.toString() + "")
            intent.putExtra("pusuario", listaTabla[position].usuario.toString() + "")
            intent.putExtra("pticket", listaTabla[position].ticket.toString() + "")
            intent.putExtra("pcelular", listaTabla[position].celular.toString() + "")
            context.startActivity(intent)
        }
        holder.filaEliminar.setOnClickListener { v: View? ->
            val ventana =
                AlertDialog.Builder(
                    context
                )
            ventana.setTitle("Mensaje informativo")
            ventana.setMessage("¿Desea eliminar la información?")
            ventana.setNegativeButton("NO", null)
            ventana.setPositiveButton(
                "SI"
            ) { dialog: DialogInterface?, which: Int ->
                val tablaDAO = TablaDAO(context)
                tablaDAO.abrirBD()
                val mensaje: String =
                    tablaDAO.eliminarArchivo(listaTabla[position].id)
                val v1 =
                    AlertDialog.Builder(
                        context
                    )
                v1.setTitle("Mensaje informativo")
                v1.setMessage(mensaje)
                v1.setPositiveButton(
                    "Aceptar"
                ) { dialog1: DialogInterface?, which1: Int ->
                    val intent = Intent(
                        context,
                        MainActivity::class.java
                    )
                    context.startActivity(intent)
                }
                v1.create().show()
            }
            ventana.create().show()
        }
    }

    override fun getItemCount(): Int {
        return listaTabla.size
    }

    inner class MiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var filaCorreo: TextView
        var filaUsuario: TextView
        var filaTicket: TextView
        var filaCelular: TextView
        var filaEditar: ImageButton
        var filaEliminar: ImageButton

        init {
            filaCorreo = itemView.findViewById(R.id.filaCorreo)
            filaUsuario = itemView.findViewById(R.id.filaUsuario)
            filaTicket = itemView.findViewById(R.id.filaTicket)
            filaCelular = itemView.findViewById(R.id.filaCelular)
            filaEditar = itemView.findViewById(R.id.filaEditar)
            filaEliminar = itemView.findViewById(R.id.filaEliminar)
        }
    }

    init {
        this.listaTabla = listaTabla
    }
}