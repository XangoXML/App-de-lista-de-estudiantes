package com.istea.app31

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class CategoriesActivity : AppCompatActivity() {

    private val categories = listOf("animal", "career", "celebrity", "dev", "music", "money")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val listView: ListView = findViewById(R.id.list_view_categories)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, categories)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedCategory = categories[position]
            val intent = Intent()
            intent.putExtra("category", selectedCategory)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
