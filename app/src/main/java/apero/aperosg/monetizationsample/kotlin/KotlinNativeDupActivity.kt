package apero.aperosg.monetizationsample.kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import apero.aperosg.monetization.util.showNativeAd
import apero.aperosg.monetizationsample.R
import apero.aperosg.monetizationsample.databinding.ActivityNativeDupBinding
import apero.aperosg.monetizationsample.AdsProvider

/**
 * This Activity class demonstrates how to show duplicate native ads by going to another screen
 * that looks exactly like previous screen, the difference is the showing native ad.
 * We also preload native ads for duplicate screen when in non-duplicate screen.
 **/
class KotlinNativeDupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNativeDupBinding

    // Variable indicating if current screen is duplicate
    private var isDupScreen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeDupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isDupScreen = intent.getBooleanExtra("isDupScreen", false)

        // Preload native ads for dup screen
        if (!isDupScreen) AdsProvider.nativeDup2Floors.loadAds(this)

        initViews()
        showNativeAds()
    }

    private fun initViews() {
        if (isDupScreen) binding.showNativeDupBtn.visibility = View.GONE

        binding.showNativeDupBtn.setOnClickListener {
            // Go to duplicate screen
            startActivity(Intent(this, KotlinNativeDupActivity::class.java).putExtra("isDupScreen", true))
            finish()
            // Remove transition animation
            overridePendingTransition(0, 0)
        }
    }

    private fun showNativeAds() {
        // Only call this function once in a screen,
        // This will create a coroutine job and react to native ad's status
        // When another ad is ready, it will be shown automatically
        // Calling this multiple times may cause undefined behaviour
        showNativeAd(
            adGroup = if (!isDupScreen) AdsProvider.native3Floors else AdsProvider.nativeDup2Floors,
            frameLayout = binding.nativeFr,
            adLayout = R.layout.native_ads,
            facebookAdLayout = null,
            keepAdsWhenLoading = true,
        )
    }

    override fun onResume() {
        super.onResume()

        // Load native ads and reload native ads when user goes back from background
        if (!isDupScreen) AdsProvider.native3Floors.loadAds(this)
        else AdsProvider.nativeDup2Floors.loadAds(this)
    }
}