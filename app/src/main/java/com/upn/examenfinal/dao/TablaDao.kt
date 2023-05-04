package com.upn.examenfinal.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.upn.examenfinal.entities.Tabla
import com.upn.examenfinal.util.TablaDB


class TablaDAO(context: Context) {
    var tablaBD: TablaDB
    var db: SQLiteDatabase? = null
    var context: Context
    fun abrirBD() {
        db = tablaBD.getWritableDatabase()
    }

    fun registrarTabla(tabla: Tabla): String {
        var rpta = ""
        try {
            val values = ContentValues()
            values.put("correo", tabla.correo)
            values.put("usuario", tabla.usuario)
            values.put("ticket", tabla.ticket)
            values.put("celular", tabla.celular)
            val result = db!!.insert("tabla1", null, values)
            rpta = if (result == -1L) {
                "Ocurrio un error al insertar"
            } else {
                "Se registro correctamente"
            }
        } catch (e: Exception) {
            Log.d("==>", e.toString())
        }
        return rpta
    }

    fun modificarTabla(tabla: Tabla): String {
        var rpta = ""
        try {
            val values = ContentValues()
            values.put("correo", tabla.correo)
            values.put("usuario", tabla.usuario)
            values.put("ticket", tabla.ticket)
            values.put("celular", tabla.celular)
            val result = db!!.update("tabla1", values, "id=" + tabla.id, null).toLong()
            rpta = if (result == -1L) {
                "Ocurrio un error al actualizar"
            } else {
                "Se actualizó correctamente"
            }
        } catch (e: Exception) {
            Log.d("==>", e.toString())
        }
        return rpta
    }

    fun eliminarArchivo(id: Int): String {
        var rpta = ""
        try {
            val result = db!!.delete("tabla1", "id=$id", null).toLong()
            rpta = if (result == -1L) {
                "Ocurrio un error al eliminar"
            } else {
                "Se eliminó correctamente"
            }
        } catch (e: Exception) {
            Log.d("==>", e.toString())
        }
        return rpta
    }

    fun cargarTabla(): List<Tabla> {
        val listaTabla: MutableList<Tabla> = ArrayList<Tabla>()
        try {
            val c = db!!.rawQuery("SELECT * FROM tabla1", null)
            while (c.moveToNext()) {
                listaTabla.add(
                    Tabla(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getInt(4)
                    )
                )
            }
        } catch (e: Exception) {
            Log.d("==>", e.toString())
        }
        return listaTabla
    }

    init {
        tablaBD = TablaDB(context)
        this.context = context
    }
}
