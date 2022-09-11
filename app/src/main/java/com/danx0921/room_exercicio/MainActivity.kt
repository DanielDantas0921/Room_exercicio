package com.danx0921.room_exercicio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bt_registrar: Button = findViewById(R.id.bt_main_registrar)
        val bt_lista_de_usuarios: Button = findViewById(R.id.bt_main_lista_de_usuarios)




        bt_registrar.setOnClickListener {
            val i = Intent(this@MainActivity, Registro::class.java)
            startActivity(i)

        }


        bt_lista_de_usuarios.setOnClickListener {
            val i = Intent(this@MainActivity, Lista_De_Usuarios::class.java)
            startActivity(i)
        }

    }
}