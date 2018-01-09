package com.cui.activityresult

import activityresult.cui.com.library.RxActivityForResult
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val REQUEST_CODE_RXACT = 20

    companion object {
        val EXTRA_TEXT = "text"
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rxActivityForResult = RxActivityForResult(this)
        textview.text = "Click Me no intent"
        textview2.text = "CLick Me has intent"
        textview.setOnClickListener {
            rxActivityForResult
                    .startActivityForResult(DetailActivity::class.java, REQUEST_CODE_RXACT)
                    .filter { it.requestCode == REQUEST_CODE_RXACT && it.resultCode == Activity.RESULT_OK }
                    .subscribe { Log.e(MainActivity::class.java.name, it.resultCode.toString()) }
        }
        textview2.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            rxActivityForResult.startActivityForResult(intent, REQUEST_CODE_RXACT)
                    .filter { it.requestCode == REQUEST_CODE_RXACT && it.resultCode == Activity.RESULT_OK && it.data != null }
                    .subscribe { textview2.text = it.data.getStringExtra(EXTRA_TEXT) }
        }
    }
}
