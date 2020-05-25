package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class MindOrks(xx:Double, yy:Double){
    var name = "123"
    var x = xx
    var y = yy
    fun draw(): MutableList<MindOrks> {
        var qwerty = mutableListOf<MindOrks>()
        for (i in 0..9){
            qwerty.add(MindOrks(i.toDouble(), Math.pow(i.toDouble(), 2.0)))
        }
        return qwerty
    }
}
class MindAdapter(ddata: MutableList<MindOrks>):RecyclerView.Adapter<MindAdapter.Companion.MindViewHolder>(){
    var data = ddata
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MindViewHolder {
        var qqq = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view, parent, false)
        return MindViewHolder(qqq)
    }
    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: MindViewHolder, position: Int) = holder.bind(data[position])

    companion object{
        class MindViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            var x: TextView = itemView.findViewById(R.id.textx)
            var y: TextView = itemView.findViewById(R.id.texty)
            fun bind(mindOrks: MindOrks){
                x.setText(mindOrks.x.toString())
                y.setText(mindOrks.y.toString())

            }
        }
    }
}
class NameMindAdapter(ddata: MutableList<MindOrks>):RecyclerView.Adapter<NameMindAdapter.Companion.NameMindViewHolder>(){
    var data = ddata
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameMindViewHolder {
        var qqq = LayoutInflater.from(parent.context).inflate(R.layout.name_view, parent, false)
        return NameMindViewHolder(qqq)
    }
    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: NameMindViewHolder, position: Int) = holder.bind(data[position])

    companion object{
        var fragmentManager : FragmentManager? = null
        class NameMindViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            var text : TextView = itemView.findViewById(R.id.name)
            var cardView : CardView = itemView.findViewById(R.id.cardview1)
            fun bind(mindOrks: MindOrks){
                text.setText(mindOrks.name)
                cardView.setOnClickListener { v ->
                    val fm = fragmentManager
                    val ft = fm?.beginTransaction()
                    var f = MindFragment().apply { ddata = mindOrks.draw() }
                    ft?.replace(R.id.dostalo, f)
                    ft?.commit()
                }
            }
        }
    }
}