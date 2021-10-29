package com.example.activities

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.text.TextWatcher
import com.example.activities.databinding.ComputeActivityBinding


class ComputeActivity : AppCompatActivity(), View.OnClickListener, TextWatcher {
    private lateinit var binding: ComputeActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ComputeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with (binding){
            btnCalculer.isEnabled = false
            field1.addTextChangedListener(this@ComputeActivity)
            field2.addTextChangedListener(this@ComputeActivity)
            btnCalculer.setOnClickListener(this@ComputeActivity)
        }
    }

    override fun afterTextChanged(s: Editable?) {
            binding.btnCalculer.isEnabled = ( binding.field1.text.isNotEmpty() || binding.field2.text.isNotEmpty())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun onClick(v: View?) {
        val res = (binding.field1.text.toString().toDouble() + binding.field2.text.toString().toDouble()).toString()
        binding.resultat.text = res
    }
}