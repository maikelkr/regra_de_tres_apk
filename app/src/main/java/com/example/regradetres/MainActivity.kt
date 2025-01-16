package com.example.regradetres

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.regradetres.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {
            calculate() }
        binding.clearButton.setOnClickListener {
            clear() }
    }
    private fun calculate() {
        val referenceNumber = binding.referenceNumber.text.toString().toDouble()
        val equivalentNumber = binding.equivalentNumber.text.toString().toDouble()
        val comparisonNumber = binding.comparisonNumber.text.toString().toDouble()

        val result = (comparisonNumber * equivalentNumber) / (referenceNumber)
        binding.resultNumber.setText(result.toString())

    }
    private fun clear() {
        binding.referenceNumber.setText("")
        binding.equivalentNumber.setText("")
        binding.comparisonNumber.setText("")
        binding.resultNumber.setText("")
    }
}