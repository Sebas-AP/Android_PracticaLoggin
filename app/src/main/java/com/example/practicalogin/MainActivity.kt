package com.example.practicalogin

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usuario = findViewById<EditText>(R.id.usuario)
        val contraseña = findViewById<EditText>(R.id.contraseña)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        btnIngresar.setOnClickListener {
            val user = usuario.text.toString()
            val pass = contraseña.text.toString()
            //Define el archivo de preferencias que va a usar y el objeto JSON
            val prefs = getSharedPreferences("usuarios", Context.MODE_PRIVATE)
            val usuariosJson = prefs.getString("usuarios", "{}")
            val usuariosObj = JSONObject(usuariosJson ?: "{}")
            //Compureba que lo datos del usuario sean correctos
            if (usuariosObj.has(user) && usuariosObj.getString(user) == pass) {
                val intent = Intent(this, contenido::class.java)
                startActivity(intent)
                finish()
            } else { // Si los datos son incorrectos le avisa con un toast
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
        //Envia a la pantalla de registro
        btnRegistrar.setOnClickListener {
            val intent = Intent(this, activityRegistro::class.java)
            startActivity(intent)
        }
    }
}
