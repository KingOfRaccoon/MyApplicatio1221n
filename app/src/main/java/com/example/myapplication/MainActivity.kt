package com.example.myapplication

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var dataPoint = MutableList<MindOrks>(5000)
    {
        MindOrks(
            Random.nextDouble() * it,
            Random.nextDouble() * it)
    }
    var sqldb = AQLite(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var startTime = System.currentTimeMillis()

        var finishTime = System.currentTimeMillis()
        var differentTime = (finishTime - startTime) / 1000
//        Log.d("TIMEMY", differentTime.toString())

        startTime = System.currentTimeMillis()

        var opebDB = sqldb.writableDatabase

        var first = MutableList(dataPoint.size / 2,{dataPoint[it]})
        var second = MutableList(dataPoint.size / 2,{dataPoint[dataPoint.size  / 2 - 1 + it]})

        var threadWrite = DatabaseWriter(opebDB, second)
        var handler = Handler()
                handler.post(threadWrite)

        for (i in first){
            AQLite.insertDATAPOINT(i, opebDB)
        }

        while (!threadWrite.finish) {

        }
        opebDB.close()

        finishTime = System.currentTimeMillis()
        differentTime = (finishTime - startTime) / 1000
        Log.d("TIMEMY", differentTime.toString())

    }

    class DatabaseWriter(var opebDB: SQLiteDatabase, var date : MutableList<MindOrks>) : Thread(){
        var finish = false
        override fun run() {
            super.run()
            for (i in date){
                AQLite.insertDATAPOINT(i, opebDB)
            }
            finish = true
        }
    }
}
