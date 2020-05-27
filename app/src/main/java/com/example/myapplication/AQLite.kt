package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class AQLite(context: Context): SQLiteOpenHelper(context, name, null, Version ) {
    companion object{
        val name = "TESTDATABASE"
        val Version = 1

        val TABLE_NAME = "TEST"
        val VALUEX = "VALUEX"
        val VALUEY = "VALUEY"
        fun insertDATAPOINT(mindOrks: MindOrks, db: SQLiteDatabase){
            var values = ContentValues()
            values.put(VALUEX, mindOrks.x)
            values.put(VALUEY, mindOrks.y)
            db.insert(TABLE_NAME, null, values)
            Log.d("NEWDATA", mindOrks.toString())
        }
        fun readDATAPOINT(db: SQLiteDatabase):MutableList<MindOrks>{
            var data = db.query(TABLE_NAME, null, null, null, null, null, null)
            var answer = mutableListOf<MindOrks>()
            while (data.moveToNext()){
                answer.add(MindOrks(data.getDouble(1), data.getDouble(2)))
            }
            data.close()
            return answer
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val DATABASEMO
                = "CREATE TABLE $TABLE_NAME (KEY_ID INTEGER PRIMARY KEY, " +
                "$VALUEX DOUBLE," +
                "$VALUEY DOUBLE );"
        db?.execSQL(DATABASEMO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}