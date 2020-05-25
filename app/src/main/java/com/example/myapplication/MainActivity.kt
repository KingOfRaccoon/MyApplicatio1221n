package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var qwerty = mutableListOf<MindOrks>()
        for (i in 0..9){
            qwerty.add(MindOrks(i.toDouble(), Math.pow(i.toDouble(), 2.0)))
        }
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        var f = AllMindFragment().apply { ddata =  qwerty}
        ft.replace(R.id.dostalo, f)
        ft.commit()
    }
}
