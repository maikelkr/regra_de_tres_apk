package com.example.regradetres

import android.os.Build
import android.os.Bundle
import android.view.WindowMetrics
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.regradetres.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var result = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        /*val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            // Initialize the Google Mobile Ads SDK on a background thread.
            MobileAds.initialize(this@MainActivity) {}
        }*/
        binding.imageLogo.setImageResource(com.example.regradetres.R.drawable.logo_app_en_us)
        when (Locale.getDefault().language) {
            "en" -> {
                binding.imageLogo.setImageResource(com.example.regradetres.R.drawable.logo_app_en_us)
            }

            "pt" -> {
                binding.imageLogo.setImageResource(com.example.regradetres.R.drawable.logo_app_pt_br)
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
        /*val adView = AdView(this)
        adView.adUnitId = "ca-app-pub-3940256099942544/9214589741"
        adView.setAdSize(AdSize.BANNER)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)*/

// Replace ad container with new ad view.
    /*    binding.adView.removeAllViews()
        binding.adView.addView(adView)*/
    }
    /*private val adSize: AdSize
    get() {
        val displayMetrics = resources.displayMetrics
        val adWidthPixels =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val windowMetrics: WindowMetrics = this.windowManager.currentWindowMetrics
                windowMetrics.bounds.width()
            } else {
                displayMetrics.widthPixels
            }
        val density = displayMetrics.density
        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
    }*/
    private fun calculate() {
        val referenceNumber = binding.referenceNumber.text.toString().toDouble()
        val equivalentNumber = binding.equivalentNumber.text.toString().toDouble()
        val comparisonNumber = binding.comparisonNumber.text.toString().toDouble()

        binding.inverseProportional.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                result = ((comparisonNumber * equivalentNumber) / (referenceNumber)).toString()

            } else {
                result = ((comparisonNumber * referenceNumber) / (equivalentNumber)).toString()
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
    /*adView.adListener = object: AdListener() {
        override fun onAdClicked() {
            // Code to be executed when the user clicks on an ad.
        }

        override fun onAdClosed() {
            // Code to be executed when the user is about to return
            // to the app after tapping on an ad.
        }

        override fun onAdFailedToLoad(adError : LoadAdError) {
            // Code to be executed when an ad request fails.
        }

        override fun onAdImpression() {
            // Code to be executed when an impression is recorded
            // for an ad.
        }

        override fun onAdLoaded() {
            // Code to be executed when an ad finishes loading.
        }

        override fun onAdOpened() {
            // Code to be executed when an ad opens an overlay that
            // covers the screen.
        }
    }*/
}