package com.example.logonrmlocal.bloconotasapp

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.logonrmlocal.bloconotasapp.model.Nota
import com.example.logonrmlocal.bloconotasapp.view.formulario.FormularioActivity
import com.example.logonrmlocal.bloconotasapp.view.main.MainListAdapter
import com.example.logonrmlocal.bloconotasapp.view.main.MainViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.loading.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private var adapter: MainListAdapter? = null
    val FORMULARIO_REQUEST_CODE = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel::class.java)

        mainViewModel.notas.observe(this,notasObserver)
        mainViewModel.isLoading.observe(this,loadingObserver)

        mainViewModel.buscarTodos()


        fab.setOnClickListener { view ->
            val formularioIntent = Intent(this, FormularioActivity::class.java)

            startActivityForResult(formularioIntent , FORMULARIO_REQUEST_CODE)
        }
    }



    private var loadingObserver = Observer<Boolean> {
        if (it == true){
            containerLoading.visibility = View.VISIBLE
        }else{
            containerLoading.visibility = View.GONE
        }
    }

    private var notasObserver = Observer<List<Nota>> {
        preencheALista(it!!)
    }


    private fun preencheALista(notas: List<Nota>){
        adapter = MainListAdapter(this, notas,
                {},
                {}
        )

        rvNotas.adapter = adapter
        rvNotas.layoutManager = LinearLayoutManager(this)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            FORMULARIO_REQUEST_CODE -> {
                when(resultCode){
                    Activity.RESULT_OK -> {mainViewModel.buscarTodos()}
                    Activity.RESULT_CANCELED -> {}
                }
            }
        }
    }


}