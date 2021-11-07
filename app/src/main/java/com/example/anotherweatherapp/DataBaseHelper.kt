package com.example.anotherweatherapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    object bbdd {
        // Table contents are grouped together in an anonymous object.
        object baseObject : BaseColumns {
            const val TABLE_NAME = "Ciudades"
            const val CITY_NAME = "Nombre"

        }
    }

    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${bbdd.baseObject.TABLE_NAME} (" +
                "id INTEGER PRIMARY KEY," +
                "${bbdd.baseObject.CITY_NAME} TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${bbdd.baseObject.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
/*
    override fun onOpen(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(SQL_DELETE_ENTRIES)
            db.execSQL(SQL_CREATE_ENTRIES)
        }
    }

 */

    companion object {
        // Common object for all the instances
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "DataBase"
    }
}