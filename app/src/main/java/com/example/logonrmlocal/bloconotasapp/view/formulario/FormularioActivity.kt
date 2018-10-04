package com.example.logonrmlocal.bloconotasapp.view.formulario

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.logonrmlocal.bloconotasapp.R
import com.example.logonrmlocal.bloconotasapp.model.ResponseStatus
import kotlinx.android.synthetic.main.activity_formulario.*

class FormularioActivity : AppCompatActivity() {

    lateinit var formularioViewModel : FormularioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)


        formularioViewModel = ViewModelProviders.of(this)
                .get(FormularioViewModel::class.java)


        formularioViewModel.responseStatus.observe(this, responseStatusObserver)
        btSalvar.setOnClickListener {
            formularioViewModel.salvar(
                    etTitulo.text.toString(),
                    etDescricao.text.toString()
            )
        }

    }

    private var responseStatusObserver = Observer<ResponseStatus>{
        if (it?.successo == true){

        }else{

        }
    }



}
