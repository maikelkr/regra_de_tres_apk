package com.example.regradetres

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.regradetres.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit val result = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {
            calculate()
        }
        binding.clearButton.setOnClickListener {
            clear()
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
    private fun calculate() {
        val referenceNumber = binding.referenceNumber.text.toString().toDouble()
        val equivalentNumber = binding.equivalentNumber.text.toString().toDouble()
        val comparisonNumber = binding.comparisonNumber.text.toString().toDouble()

        binding.inverseProportional.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                result = (comparisonNumber * equivalentNumber) / (referenceNumber)

            } else {
                result = (comparisonNumber * referenceNumber) / (equivalentNumber)
            }
        }
        binding.resultNumber.setText(result.toString())
    }
    private fun clear() {
        binding.referenceNumber.setText("")
        binding.equivalentNumber.setText("")
        binding.comparisonNumber.setText("")
        binding.resultNumber.setText("")
    }
}