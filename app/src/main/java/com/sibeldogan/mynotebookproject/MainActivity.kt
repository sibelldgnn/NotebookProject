package com.sibeldogan.mynotebookproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sibeldogan.mynotebookproject.databinding.ActivityMainBinding
import com.sibeldogan.notebookproject.NotebookAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var notesList = ArrayList<Notes>()
    lateinit var binding: ActivityMainBinding
    lateinit var gson: Gson
    lateinit var type: Type

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        notesList = ArrayList<Notes>()
        sharedPreferences = getSharedPreferences("com.sibeldogan.mynotebookproject", MODE_PRIVATE)
        gson = Gson()

    }

    override fun onResume() {

        super.onResume()
        val json = sharedPreferences.getString("set", "")
        if (json != "") {
            type = object :
                TypeToken<ArrayList<Notes>>() {}.type //istediğimiz listeye çeviriyoruz.
            notesList =
                gson.fromJson(json, type) //string veriyi jsondan doğru objeye çeviriyoruz

            val array: Array<String?> =
                arrayOfNulls<String>(notesList.size) //boş bir array tanımladık. objeyi liste atadık listelemek için
            notesList.reverse() //en son kaydı en üstte gösteriyo.Listeyi tersine çeviriyo
            notesList.forEachIndexed { index, properties ->
                array[index] = properties.note + " " + properties.noteDetail

            }
            val manager = LinearLayoutManager(this)
            recyclerView.layoutManager = manager
            recyclerView.setHasFixedSize(true)
            val adapter = NotebookAdapter(notesList)
            recyclerView.adapter = adapter

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.example_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item1) {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            startActivity(intent)

        }

        return super.onOptionsItemSelected(item)
    }


}

