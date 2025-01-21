package com.example.regradetres

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.regradetres.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdView
import java.util.Locale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var result = ""
    private lateinit var adView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        adView = binding.adView
        //adView.adUnitId = "ca-app-pub-3940256099942544~3347511713"
        //adView.setAdSize(AdSize.BANNER)
        val adRequest = com.google.android.gms.ads.AdRequest.Builder().build()
        adView.loadAd(adRequest)
        binding()

        val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            MobileAds.initialize(this@MainActivity) {}
        }
    }
    private fun binding(){
        binding.imageLogo.setImageResource(R.drawable.logo_app_en_us)
        when (Locale.getDefault().language) {
            "en" -> {
                binding.imageLogo.setImageResource(R.drawable.logo_app_en_us)
            }

            "pt" -> {
                binding.imageLogo.setImageResource(R.drawable.logo_app_pt_br)
            }
        }
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
            result = if (isChecked) {
                ((comparisonNumber * equivalentNumber) / (referenceNumber)).toString()

            } else {
                ((comparisonNumber * referenceNumber) / (equivalentNumber)).toString()
            }
        }
        binding.resultNumber.setText(result)
    }
    private fun clear() {
        binding.referenceNumber.setText("")
        binding.equivalentNumber.setText("")
        binding.comparisonNumber.setText("")
        binding.resultNumber.setText("")
    }
}