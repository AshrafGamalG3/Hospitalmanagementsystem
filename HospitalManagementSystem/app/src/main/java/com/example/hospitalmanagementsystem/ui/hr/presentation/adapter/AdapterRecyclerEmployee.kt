package com.example.hospitalmanagementsystem.ui.hr.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.ui.reception.data.model.AllUserData



class AdapterRecyclerEmployee : RecyclerView.Adapter<AdapterRecyclerEmployee.Holder>() {


    var list : ArrayList<AllUserData> ?= null
    var onUserClick : OnUserClick?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       val view = LayoutInflater.from(parent.context)
               .inflate(R.layout.item_recycler_employee, parent , false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list?.get(position)

        holder.apply {

            holder.textName.text = data?.first_name
            holder.textType.text = data?.type
        }

    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class Holder (view : View) : RecyclerView.ViewHolder(view){

        val textType = view.findViewById<TextView>(R.id.text_type);
        val textName = view.findViewById<TextView>(R.id.text_user_name);

        init {
            itemView.setOnClickListener {
                onUserClick?.onClick(list?.get(layoutPosition)?.id!!)
            }


        }

    }
    interface OnUserClick {
        fun onClick (id : Int)

    }

}