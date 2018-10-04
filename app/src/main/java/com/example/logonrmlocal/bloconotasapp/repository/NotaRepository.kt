package com.example.logonrmlocal.bloconotasapp.repository

import com.example.logonrmlocal.bloconotasapp.api.getNotaAPI
import com.example.logonrmlocal.bloconotasapp.model.Nota
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotaRepository {

    fun buscarTodos(
            onComplete: (List<Nota>?) -> Unit,
            onError: (Throwable?) -> Unit
    ){

        getNotaAPI()
                .buscarTodos()
                .enqueue(object : Callback<List<Nota>> {

                    override fun onFailure(call: Call<List<Nota>>?, t: Throwable?) {
                        onError(t)
                    }

                    override fun onResponse(call: Call<List<Nota>>?, response: Response<List<Nota>>?) {
                       onComplete(response?.body())
                    }


                })
    }


    fun salvar(nota: Nota,
               onComplete: (Nota?) -> Unit,
               onError: (Throwable?) -> Unit ) {

        getNotaAPI()
                .salvar(nota)
                .enqueue(object : Callback<Nota>{
                    override fun onFailure(call: Call<Nota>?, t: Throwable?) {
                        onError(t)
                    }

                    override fun onResponse(call: Call<Nota>?,
                                            response: Response<Nota>?) {
                        onComplete(response?.body())
                    }
                })
    }

}