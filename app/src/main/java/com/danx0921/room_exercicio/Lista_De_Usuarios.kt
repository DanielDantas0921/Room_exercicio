package com.danx0921.room_exercicio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danx0921.room_exercicio.model.AppDatabase
import com.danx0921.room_exercicio.model.Cadastro
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread


class Lista_De_Usuarios : AppCompatActivity(), OnListClickListener {
    private lateinit var adapter: ListCadastroAdapter
    private lateinit var listaDeUsuarios: MutableList<Cadastro>
    private lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_usuarios)
        rv = findViewById(R.id.rv_lista_de_usuarios)

        val bt_voltar: Button = findViewById(R.id.bt_lista_de_usuarios_voltar)





        bt_voltar.setOnClickListener {
            finish()
        }


        listaDeUsuarios = mutableListOf<Cadastro>()
        adapter = ListCadastroAdapter(listaDeUsuarios,this)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        Thread {

            val dbteste = AppDatabase.getDatabase(this).cadastroDao()
            var buscadobanco = dbteste.getRegisterAll()


            runOnUiThread {
                listaDeUsuarios.addAll(buscadobanco)
                adapter.notifyDataSetChanged()
            }
        }.start()


    }

    override fun onClick(id: Int) {


        val i = Intent(this, Editar_UsuarioActivity::class.java)
        i.putExtra("id", id)
        startActivity(i)



    }

    override fun onLongClick(position: Int, cadastro: Cadastro) {

        AlertDialog.Builder(this)
            .setMessage("Você quer realmente excluir esse item ?")
            .setNegativeButton(android.R.string.cancel) { dialog, which ->
            }
            .setPositiveButton(android.R.string.ok) { dialog, which ->
                Thread {


                    val dbchamada = AppDatabase.getDatabase(this).cadastroDao()

                    val delete = dbchamada.delete(cadastro)



                    if (delete > 0) {
                        runOnUiThread {

                            listaDeUsuarios.removeAt(position)
                            adapter.notifyItemRemoved(position)
                        }
                    }
                }.start()

            }
            .create()
            .show()


    }


    private inner class ListCadastroAdapter(
        private val listCadastro: List<Cadastro>,
        private val listener: OnListClickListener

    ) : RecyclerView.Adapter<ListCadastroAdapter.ListCadastroViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCadastroViewHolder {
            val view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
            return ListCadastroViewHolder(view)
        }

        override fun onBindViewHolder(holder: ListCadastroViewHolder, position: Int) {
            val itemCurrent = listCadastro[position]
            holder.bind(itemCurrent)
        }

        override fun getItemCount(): Int {
            return listCadastro.size
        }

        private inner class ListCadastroViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            fun bind(item: Cadastro) {

                val tv = itemView as TextView
                val username = item.Username
                val password = item.Password

                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))
                val data = sdf.format(item.createdDate)


                tv.text = "Username: $username ,Password: $password ,Data: $data"


                tv.setOnClickListener {
                    listener.onClick(item.id)
                }

                tv.setOnLongClickListener {

                    listener.onLongClick(adapterPosition, item)
                    true
                }


            }
        }

    }



}