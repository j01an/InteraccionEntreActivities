package com.example.interaccionentreactivities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.interaccionentreactivities.Model.UserModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class registroActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etFullname: EditText=findViewById(R.id.etFullName)
        val etCountry: EditText=findViewById(R.id.etCountry)
        val etEmailRegister: EditText=findViewById(R.id.etEmailRegister)
        val etPasswordRegister: EditText=findViewById(R.id.etPasswordRegister)
        val btnSaveRegister: Button=findViewById(R.id.btnSaveRegister)

        val auth=FirebaseAuth.getInstance()
        val db=FirebaseFirestore.getInstance()
        val collectionRef=db.collection("users")


        btnSaveRegister.setOnClickListener {
            val fullName = etFullname.text.toString()
            val country = etCountry.text.toString()
            val email = etEmailRegister.text.toString()
            val password = etPasswordRegister.text.toString()
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){task ->

                if (task.isSuccessful){
                    Log.d("Firestore", "Registro Exitoso")
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        "Registro Exitoso",
                        Snackbar.LENGTH_LONG
                    ).show()

                    if(email.isNotEmpty() && password.isNotEmpty() && fullName.isNotEmpty() && country.isNotEmpty()){

                        var user: FirebaseUser?= auth.currentUser
                        var uid = user?.uid
                        val nuevoRegistro = UserModel(
                            email, password, fullName, country,  uid.toString()
                        )
                        collectionRef.add(nuevoRegistro).addOnSuccessListener {

                            Log.d("FirestoreNuevo", "Se agrega collection")
                            Snackbar.make(findViewById(android.R.id.content),"Se agrega collection", Snackbar.LENGTH_LONG).show()

                        }.addOnFailureListener {
                            Log.d("FirestoreNuevo", "No agrega collection")
                            Snackbar.make(findViewById(android.R.id.content),"No agrega collection",Snackbar.LENGTH_LONG).show()
                        }

                    }else{
                        Snackbar.make(findViewById(android.R.id.content),"Por favor completa los campos",
                            Snackbar.LENGTH_LONG).show()
                    }


                }else{
                    Snackbar.make(findViewById(android.R.id.content),"Ocurrio un error al registrarse",
                        Snackbar.LENGTH_LONG).show()

                }

            }
            startActivity(Intent(this, loginActivity::class.java))

        }




    }

}