package com.maikelkruger.regradetres

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.maikelkruger.regradetres.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var result = ""
    private lateinit var adView: AdView
    private var toggled : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        adView = findViewById(R.id.adView)
        adView.loadAd(AdRequest.Builder().build())
        binding()
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
        binding.settingsBtn.setOnClickListener {
            if (!toggled){
                toggleMenu(true)
                toggled
            }else{
                toggleMenu(false)
                !toggled
            }
        }
        binding.frameMenuExit.setOnClickListener {
            toggleMenu(false)
            toggled = false
        }
        binding.buttonExit.setOnClickListener {
            toggleMenu(false)
            toggled = false
        }
        binding.calculateButton.setOnClickListener {
            if(binding.comparisonNumber.text.toString() != "" && binding.referenceNumber.text.toString() != "" && binding.equivalentNumber.text.toString() != "") {
                calculate()
            }
        }
        binding.clearButton.setOnClickListener {
            clear()
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
    private fun toggleMenu(toggled : Boolean){
        if (toggled){
            binding.frameMenu.visibility = View.VISIBLE
            binding.frameMenuExit.visibility = View.VISIBLE
        }else{
            binding.frameMenu.visibility = View.GONE
            binding.frameMenuExit.visibility = View.GONE
        }
    }
    private fun calculate() {
        val referenceNumber = binding.referenceNumber.text.toString().toDouble()
        val equivalentNumber = binding.equivalentNumber.text.toString().toDouble()
        val comparisonNumber = binding.comparisonNumber.text.toString().toDouble()

        if (binding.inverseProportional.isChecked) {
           result = ((comparisonNumber * referenceNumber) / (equivalentNumber)).toString()
        } else {
           result = ((comparisonNumber * equivalentNumber) / (referenceNumber)).toString()
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