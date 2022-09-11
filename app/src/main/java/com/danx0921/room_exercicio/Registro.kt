package com.danx0921.room_exercicio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.danx0921.room_exercicio.model.AppDatabase
import com.danx0921.room_exercicio.model.Cadastro


private lateinit var et_username: EditText
private lateinit var et_password: EditText
class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        et_username= findViewById(R.id.et_editar_username)
        et_password = findViewById(R.id.et_editar_password)
        val bt_voltar: Button = findViewById(R.id.bt_editar_voltar)
        val bt_registrar: Button = findViewById(R.id.bt_registro_registrar)




        bt_registrar.setOnClickListener {
            if(!validar()){
                Toast.makeText(this,"Por favor insira algo nos campos vazios", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //fazer a lógica para pegar os campos de username e password e jogar no banco de dados


            val username = et_username.text.toString()
            val password = et_password.text.toString()


            AlertDialog.Builder(this)
                .setTitle("Você deseja salvar essa conta ?")
                .setPositiveButton(android.R.string.ok) { dialog, which ->

                }
                .setNegativeButton("Salvar") { dialog, which ->
                    Thread {
//                        val app = application as App --> ta dando crash esse aqui
//                          val dao = app.db.cadastroDao()

                        val dbteste = AppDatabase.getDatabase(this).cadastroDao()

                        dbteste.insert(Cadastro(Username = username, Password = password))


                        runOnUiThread {
                            val i = Intent(this,Lista_De_Usuarios::class.java)
                            startActivity(i)
                        }
                    }.start()

                }
                .create()
                .show()



        }


        bt_voltar.setOnClickListener {
            finish()
        }


        


    }

    fun validar(): Boolean {

        return (et_username.text.toString().isNotEmpty() && et_password.text.toString().isNotEmpty())

    }

}