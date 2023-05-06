package com.example.anew

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OilBillAdaptor : RecyclerView.Adapter<OilBillAdaptor.BillViewHolder>(){
    private var elcList: ArrayList<OilModel> = ArrayList()
    private var onClickItem:((OilModel) -> Unit)? = null
    private var onClickDeleteItem:((OilModel) -> Unit)? = null

    fun addItems(items: ArrayList<OilModel>){
        this.elcList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (OilModel)-> Unit){
        this.onClickItem = callback
    }

    fun setOnClickDeleteItem(callback: (OilModel) -> Unit){
        this.onClickDeleteItem = callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BillViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.display_del, parent,false)
        )

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        val elc = elcList[position]
        holder.bindView(elc)
        holder.itemView.setOnClickListener{onClickItem?.invoke(elc)}
        holder.btnDelete.setOnClickListener{onClickDeleteItem?.invoke(elc)}
    }

    override fun getItemCount(): Int {
        return elcList.size
    }


    class BillViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.id)
        private var nd = view.findViewById<TextView>(R.id.nd)
        private var un = view.findViewById<TextView>(R.id.un)
        private var tot = view.findViewById<TextView>(R.id.tot)
        var btnDelete = view.findViewById<TextView>(R.id.btnDelete)

        fun bindView(elc: OilModel){
            id.text = elc.id.toString()
            nd.text = elc.nd.toString()
            un.text = elc.un.toString()
            tot.text = elc.tot.toString()
        }
    }

}