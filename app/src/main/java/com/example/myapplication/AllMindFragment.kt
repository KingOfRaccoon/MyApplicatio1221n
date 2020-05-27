package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.jar.Attributes

class AllMindFragment(): Fragment() {
    var ddata = mutableListOf<MindOrks>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var ret =inflater.inflate(R.layout.fragment_all, container, false)
        var recycler : RecyclerView = ret.findViewById(R.id.recycler)
        recycler.adapter = NameMindAdapter(ddata).apply {  }

        var pi = LinearLayoutManager(ret.context)
        recycler.layoutManager = pi
        return ret
    }
}