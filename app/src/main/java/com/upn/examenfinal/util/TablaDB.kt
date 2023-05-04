package com.upn.examenfinal.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class TablaDB(context: Context?) :
    SQLiteOpenHelper(context, "tabla1.db", null, 1) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val query = "CREATE TABLE tabla1" +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " correo TEXT NOT NULL," +
                " usuario TEXT NOT NULL," +
                " ticket TEXT NOT NULL," +
                " celular INTEGER NOT NULL);"
        sqLiteDatabase.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}
