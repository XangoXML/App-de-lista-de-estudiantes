package com.istea.app31

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var mSelectedCategory: String = "animal"
    private val REQUEST_CODE_CATEGORIES = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnJokes: Button = findViewById(R.id.btn_getjokes)
        val btnCategories: Button = findViewById(R.id.btn_categories)
        val tvFacts: TextView = findViewById(R.id.tv_facts)
        val tvJokes: TextView = findViewById(R.id.tv_jokess)
        val mProgressBar: ProgressBar = findViewById(R.id.idLoadingPB)

        btnJokes.text = "Frase random"
        tvFacts.text = "Chuck Norris Facts:"

        btnJokes.setOnClickListener {
            mProgressBar.visibility = View.VISIBLE

            ApiCall().getRandomJoke(mSelectedCategory) { joke ->
                tvJokes.text = joke?.value
                mProgressBar.visibility = View.GONE
            }
        }

        btnCategories.setOnClickListener {
            val intent = Intent(this, CategoriesActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_CATEGORIES)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CATEGORIES && resultCode == RESULT_OK) {
            val category = data?.getStringExtra("category")
            category?.let {
                mSelectedCategory = category

                ApiCall().getRandomJoke(mSelectedCategory) { joke ->
                    val tvJokes: TextView = findViewById(R.id.tv_jokess)
                    val mProgressBar: ProgressBar = findViewById(R.id.idLoadingPB)

                    tvJokes.text = joke?.value
                    mProgressBar.visibility = View.GONE
                }
            }
        }
    }
}
