package apero.aperosg.monetizationsample.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import apero.aperosg.monetization.util.showNativeAd
import apero.aperosg.monetizationsample.R
import apero.aperosg.monetizationsample.databinding.ActivityIncreaseImpression2Binding
import apero.aperosg.monetizationsample.AdsProvider

/**
 * This Activity class demonstrate how to increase impression of
 * native ad of next screen when interstitial is showing.
 * This Activity is the activity to show native ad.
 * Open Logcat with keyword "onAdImpression" for more insight
 * @see KotlinIncreaseImpression1Activity
 **/
class KotlinIncreaseImpression2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIncreaseImpression2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncreaseImpression2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        showNativeAd(
            adGroup = AdsProvider.nativeIncreaseImpression,
            frameLayout = binding.nativeFr,
            adLayout = R.layout.native_ads,
            facebookAdLayout = null,
            keepAdsWhenLoading = true,
        )
    }

    override fun onResume() {
        super.onResume()

        // Load native ads
        AdsProvider.nativeIncreaseImpression.loadAds(this)
    }
}