package com.example.interaccionentreactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var auth =FirebaseAuth.getInstance()
        val etEmail: EditText = findViewById(R.id.etEmail)
        val etPassword: EditText= findViewById(R.id.etPassword)
        val btnAcceder: Button = findViewById(R.id.btnAcceder)
        val btnRegistrar: Button = findViewById(R.id.btnRegistrar)

        btnAcceder.setOnClickListener {
            val correo = etEmail.text.toString()
            val password = etPassword.text.toString()

            auth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(this){task ->
                if (task.isSuccessful){
                    Snackbar.make(findViewById(android.R.id.content),"Ingreso Exitoso", Snackbar.LENGTH_LONG).show()
                    startActivity(Intent(this,PrincipalActivity::class.java))

                }else{
                    Snackbar.make(findViewById(android.R.id.content),"Credenciales invalidas", Snackbar.LENGTH_LONG).show()

                }
            }

        }

        btnRegistrar.setOnClickListener {
            startActivity(Intent(this,registroActivity::class.java))
        }





    }
}