package com.sibeldogan.mynotebookproject

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sibeldogan.mynotebookproject.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var gson: Gson


    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        gson = Gson()
        title = "Note Add"

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_save, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        sharedPreferences = getSharedPreferences("com.sibeldogan.mynotebookproject", MODE_PRIVATE)
        when (item.itemId) {

            R.id.save_note -> {
                var arrayList: MutableList<Notes> //daha önceden veri eklenmiş mi eklenmemiş mi eklenmişse üzerine ekliyoruz
                var json = sharedPreferences.getString("set", "")
                if (json != "") {
                    val type = object : TypeToken<ArrayList<Notes>>() {}.type
                    arrayList = gson.fromJson(json, type)
                } else {
                    arrayList = ArrayList<Notes>()
                }

                val name = editText.text.toString().trim()
                val note = noteDetail.text.toString().trim()
                arrayList.add(Notes(name, note))
                json = gson.toJson(arrayList)

                editor = sharedPreferences.edit()
                editor.putString("set", json)
                editor.commit()
                onBackPressed() //bir önceki sayfaya yönlenir.

            }

        }
        return super.onOptionsItemSelected(item)
    }
}