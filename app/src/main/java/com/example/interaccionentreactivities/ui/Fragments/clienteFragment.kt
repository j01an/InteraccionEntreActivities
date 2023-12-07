package com.example.interaccionentreactivities.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.interaccionentreactivities.Model.ProductModel
import com.example.interaccionentreactivities.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore


class clienteFragment : Fragment() {

    private lateinit var etAddDesc: EditText
    private lateinit var etAddPrecio: EditText
    private lateinit var etAddUrl: EditText
    private lateinit var btnAddAgregar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_cliente, container, false)

        etAddDesc=view.findViewById(R.id.etAddDesc)
        etAddPrecio=view.findViewById(R.id.etAddPrecio)
        etAddUrl=view.findViewById(R.id.etAddUrl)
        btnAddAgregar=view.findViewById(R.id.btnAddAgregar)

        val db= FirebaseFirestore.getInstance()
        val collectionRef=db.collection("products")

        btnAddAgregar.setOnClickListener {
            val description = etAddDesc.text.toString()
            val precio = etAddPrecio.text.toString()
            val imgurl = etAddUrl.text.toString()
            val productmodel = ProductModel(
                description, precio, imgurl
            )

            if(description.isNotEmpty() && precio.isNotEmpty() && imgurl.isNotEmpty()){
                collectionRef.add(productmodel).addOnSuccessListener {
                    Log.d("FirestoreNewProduct", "Se agrega producto")
                    //Este snackbar es necesario para fragment
                    Snackbar.make(requireView(),"Se agrega producto", Snackbar.LENGTH_LONG).show()

                }.addOnFailureListener {
                    Log.d("FirestoreNewProduct", "No agrega producto")
                    Snackbar.make(requireView(),"No agrega producto", Snackbar.LENGTH_LONG).show()
                }

            }else{
                Snackbar.make(requireView(),"Porfavor completa los campos", Snackbar.LENGTH_LONG).show()
            }

            limpiarDatosDelFormulario()





        }

        return  view
    }

    private fun limpiarDatosDelFormulario() {

        etAddDesc.text.clear()
        etAddPrecio.text.clear()
        etAddUrl.text.clear()
    }

}