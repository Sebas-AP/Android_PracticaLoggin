package com.example.practicalogin

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.json.JSONObject

class activityRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val usuario = findViewById<EditText>(R.id.usuario)
        val contraseña = findViewById<EditText>(R.id.contraseña)
        val confirmar = findViewById<EditText>(R.id.confirmar)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            val user = usuario.text.toString()
            val pass = contraseña.text.toString()
            val conf = confirmar.text.toString()
            //Verifica que los campos esten completos
            if (user.isEmpty() || pass.isEmpty() || conf.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //Verifica que las contraseñas sean iguales
            if (pass != conf) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val prefs = getSharedPreferences("usuarios", Context.MODE_PRIVATE)
            val usuariosJson = prefs.getString("usuarios", "{}")
            //Definir la estructura del objeto JSON y guardarlo
            val usuariosObj = JSONObject(usuariosJson ?: "{}")

            if (usuariosObj.has(user)) { //Verifica que el usuario ya existe
                Toast.makeText(this, "Usuario ya existe", Toast.LENGTH_SHORT).show()
            } else {
                //Guarda los datos en el JSON
                usuariosObj.put(user, pass)
                prefs.edit().putString("usuarios", usuariosObj.toString()).apply()
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
