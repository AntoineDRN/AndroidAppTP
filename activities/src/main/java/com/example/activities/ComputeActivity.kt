package com.example.activities

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class ComputeActivity : AppCompatActivity(), View.OnClickListener, TextWatcher {
    private lateinit var nombre1: EditText
    private lateinit var nombre2: EditText
    private lateinit var textRes: TextView
    private lateinit var calcButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compute_activity)
        textRes = findViewById(R.id.resultat)
        nombre1 = findViewById(R.id.field_1)
        nombre2 = findViewById(R.id.field_2)
        calcButton = findViewById(R.id.btn_calculer)
        calcButton.isEnabled = false
        nombre1.addTextChangedListener(this)
        nombre2.addTextChangedListener(this)
        calcButton.setOnClickListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
        if( nombre1.text.isNotEmpty() || nombre2.text.isNotEmpty()){
            calcButton.isEnabled = true
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun onClick(v: View?) {
        val res = (nombre1.text.toString().toDouble() + nombre2.text.toString().toDouble()).toString()
        textRes.text = res
    }
}