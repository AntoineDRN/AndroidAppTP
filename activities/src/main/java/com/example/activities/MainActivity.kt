package com.example.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var clickButton: Button
    private lateinit var calcButton: Button
    private lateinit var textView: TextView
    private var nbClick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickButton = findViewById(R.id.btn_click_me)
        calcButton = findViewById(R.id.btn_compute)
        textView = findViewById(R.id.text_count)
        clickButton.setOnClickListener {
            if(nbClick < 5 ) {
                clickButton.isEnabled = true
                nbClick++
                val newText = "Cliquez moi encore, je n'ai que $nbClick clics!"
                textView.text = newText
            } else if (nbClick == 0){
                textView.text = ""
            } else {
                clickButton.isEnabled = false
            }
        }
        calcButton.setOnClickListener {
            val intent = Intent(baseContext, ComputeActivity::class.java)
            startActivity(intent)
        }
    }



}