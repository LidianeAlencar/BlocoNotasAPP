package com.example.logonrmlocal.bloconotasapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.example.logonrmlocal.bloconotasapp.model.Nota
import com.example.logonrmlocal.bloconotasapp.view.main.MainListAdapter
import com.example.logonrmlocal.bloconotasapp.view.main.MainViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.loading.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private var adapter: MainListAdapter? = null

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
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
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
}