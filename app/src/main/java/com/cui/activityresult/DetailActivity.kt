package com.cui.activityresult

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textview.text = "Click Me back no intent"
        textview2.text = "Click Me back has intent"
        textview.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
        textview2.setOnClickListener {
            val intent = Intent()
            intent.putExtra(MainActivity.EXTRA_TEXT, "i'm back")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
