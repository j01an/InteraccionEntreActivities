package com.example.interaccionentreactivities.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interaccionentreactivities.Adapter.ProductAdapter
import com.example.interaccionentreactivities.Model.ProductModel
import com.example.interaccionentreactivities.R
import com.google.firebase.firestore.FirebaseFirestore

class listaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_lista, container, false)

        val db=FirebaseFirestore.getInstance()
        var lstProducts: List<ProductModel>
        val rvProducto: RecyclerView = view.findViewById(R.id.rvProducto)

        db.collection("products").addSnapshotListener { snap, error ->
            if(error!=null){
                return@addSnapshotListener
            }
            lstProducts=snap!!.documents.map { document ->
                ProductModel(document["description"].toString(),document["price"].toString(),document["imageUrl"].toString())
            }

            rvProducto.adapter=ProductAdapter(lstProducts)
            rvProducto.layoutManager=LinearLayoutManager(requireContext())
        }

        return view
    }

}