package com.example.interaccionentreactivities.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.interaccionentreactivities.Model.ProductModel
import com.example.interaccionentreactivities.R
import com.squareup.picasso.Picasso

class ProductAdapter(private var lstProducts: List<ProductModel>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvProductDescrip: TextView = itemView.findViewById(R.id.tvProductDescrip)
        val tvProductPriece: TextView = itemView.findViewById(R.id.tvProductPriece)
        val ivProduct: ImageView = itemView.findViewById(R.id.ivProduct)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater= LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_product,parent,false))
    }

    override fun getItemCount(): Int {
        return lstProducts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemProduct= lstProducts[position]
        holder.tvProductDescrip.text=itemProduct.description
        holder.tvProductPriece.text=itemProduct.price
        Picasso.get().load(itemProduct.imageUrl).into(holder.ivProduct)
    }


}