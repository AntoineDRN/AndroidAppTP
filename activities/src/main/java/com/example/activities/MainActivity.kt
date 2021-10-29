package com.example.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var nbClick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnClickMe.setOnClickListener {
            if(nbClick < 6 ) {
                binding.btnClickMe.isEnabled = true
                val newText = getString(R.string.clic_encore, nbClick)
                nbClick++
                binding.textCount.text = newText
            } else if (nbClick == 0){
                binding.textCount.text = ""
            } else {
                binding.btnClickMe.isEnabled = false
            }
        }
        binding.btnCompute.setOnClickListener {
            val intent = Intent(baseContext, ComputeActivity::class.java)
            startActivity(intent)
        }
    }



}