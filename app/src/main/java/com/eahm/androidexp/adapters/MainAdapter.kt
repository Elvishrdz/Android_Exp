package com.eahm.androidexp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.eahm.androidexp.R
import com.eahm.androidexp.models.MenuElement

class MainAdapter(
    private val content : List<MenuElement>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    private lateinit var rootView : View

    inner class MainViewHolder (view : View) : RecyclerView.ViewHolder(view){
        private val root : ConstraintLayout = view.findViewById(R.id.rootLayout)
        private val title : TextView = view.findViewById(R.id.tvMenuTitle)
        private val description : TextView = view.findViewById(R.id.tvMenuDescription)

        fun bind(element: MenuElement) {
            title.text = element.title
            description.text = element.description
            root.setOnClickListener{
                rootView.context.startActivity(Intent(rootView.context, element.activity))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MainViewHolder(rootView)
    }

    override fun getItemCount(): Int  = content.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(content[position])
    }

}