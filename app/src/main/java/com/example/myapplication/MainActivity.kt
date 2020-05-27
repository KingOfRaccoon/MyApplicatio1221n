package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        for (i in dataPoint){
            var opebDB = sqldb.writableDatabase
            AQLite.insertDATAPOINT(i, opebDB)
        }
        var finishTime = System.currentTimeMillis()
        var differentTime = (finishTime - startTime) / 1000
        Log.d("TIMEMY", differentTime.toString())

        startTime = System.currentTimeMillis()

        var opebDB = sqldb.writableDatabase
        for (i in dataPoint){
            AQLite.insertDATAPOINT(i, opebDB, false)
        }
        opebDB.close()

        finishTime = System.currentTimeMillis()
        differentTime = (finishTime - startTime) / 1000
        Log.d("TIMEMY", differentTime.toString())




//        val fm = supportFragmentManager
//        val ft = fm.beginTransaction()
//        var f = AllMindFragment().apply { ddata =  dataPoint}
//        NameMindAdapter.fragmentManager = supportFragmentManager
//        ft.replace(R.id.dostalo, f)
//        ft.commit()
    }
}
