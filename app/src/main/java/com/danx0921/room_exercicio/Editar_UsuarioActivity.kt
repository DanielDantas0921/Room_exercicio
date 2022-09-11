package com.danx0921.room_exercicio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.danx0921.room_exercicio.model.AppDatabase
import com.danx0921.room_exercicio.model.Cadastro
import kotlin.concurrent.thread

class Editar_UsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_usuario)
        val et_username: EditText = findViewById(R.id.et_editar_username)
        val et_password: EditText = findViewById(R.id.et_editar_password)
        val bt_voltar: Button = findViewById(R.id.bt_editar_voltar)
        val bt_modificar: Button = findViewById(R.id.bt_editar_modificar)

        val buscarid = intent.extras?.getInt("id")

       thread {
          val  buscardb = AppDatabase.getDatabase(this).cadastroDao()


           if (buscarid != null){
               val buscarusername = buscardb.getUsername(buscarid)
               val buscarpassword = buscardb.getPassword(buscarid)
               Log.i("username_buscado", "Username: $buscarusername")
               et_username.setText(buscarusername)
               et_password.setText(buscarpassword)



           }

       }



        bt_voltar.setOnClickListener { finish() }



        bt_modificar.setOnClickListener {

            Thread{
                val  buscardb = AppDatabase.getDatabase(this).cadastroDao()
                val username = et_username.text.toString()
                val password = et_password.text.toString()



                if (buscarid != null) {
                    buscardb.update(Cadastro(id = buscarid, Username = username, Password = password))
                }

                runOnUiThread {

                    val i = Intent(this, Lista_De_Usuarios::class.java)
                    startActivity(i)
                }

            }.start()




        }

    }











}