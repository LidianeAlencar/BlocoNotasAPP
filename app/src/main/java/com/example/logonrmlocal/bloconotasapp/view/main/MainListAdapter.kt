package com.example.logonrmlocal.bloconotasapp.view.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.logonrmlocal.bloconotasapp.R
import com.example.logonrmlocal.bloconotasapp.model.Nota
import kotlinx.android.synthetic.main.nota_item.view.*


class MainListAdapter(
        val context: Context,
        val notas: List<Nota>,
        val listener: (Nota) -> Unit,
        val listenerDelete: (Nota) -> Unit) : RecyclerView.Adapter<MainListAdapter.NotaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val itemView = LayoutInflater.from(context)
                .inflate(R.layout.nota_item, parent, false)
        return NotaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder?.let {
            holder.bindView(nota, listener, listenerDelete)
        }
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(nota: Nota,
                     listener: (Nota) -> Unit,
                     listenerDelete: (Nota) -> Unit) = with(itemView) {
            tvTitulo.text = nota.titulo
            tvDescricao.text = nota.descricao

            /*ivDelete.setOnClickListener {
                listenerDelete(jogo)
            }*/
            setOnClickListener { listener(nota) }
        }
    }

}