package apero.aperosg.monetizationsample.kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import apero.aperosg.monetization.util.showNativeAd
import apero.aperosg.monetizationsample.R
import apero.aperosg.monetizationsample.databinding.ActivityNativeDupBinding
import apero.aperosg.monetizationsample.AdsProvider
import kotlinx.coroutines.Job

/**
 * This Activity class demonstrates how to show duplicate native ads in-place
 * (without going to another screen)
 **/
class KotlinNativeDupInplaceActivity: AppCompatActivity() {
    private lateinit var binding: ActivityNativeDupBinding

    // Variable indicating if current screen is duplicate
    private var isDupAds: Boolean = false

    private var nativeAdJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeDupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        showNativeAds()
    }

    private fun initViews() {
        binding.showNativeDupBtn.setOnClickListener {
            isDupAds = true
            binding.showNativeDupBtn.visibility = View.GONE
            showNativeAds()
        }
    }

    private fun showNativeAds() {
        // This will create a coroutine job and react to native ad's status
        // When another ad is ready, it will be shown automatically
        // Calling this multiple times may cause undefined behaviour
        // To show duplicate ad, we cancel previous coroutine job and start a new coroutine with duplicate ad
        nativeAdJob?.cancel()
        nativeAdJob = showNativeAd(
            adGroup = if (!isDupAds) AdsProvider.native3Floors else AdsProvider.nativeDup2Floors,
            frameLayout = binding.nativeFr,
            adLayout = R.layout.native_ads,
            facebookAdLayout = null,
            keepAdsWhenLoading = true,
        )
    }

    override fun onResume() {
        super.onResume()

        // Load native ads and reload native ads when user goes back from background
        if (!isDupAds) AdsProvider.native3Floors.loadAds(this)
        // Preload native ads dup or reload if already showing dup
        AdsProvider.nativeDup2Floors.loadAds(this)
    }
}