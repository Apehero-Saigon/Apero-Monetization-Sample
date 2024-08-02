package apero.aperosg.monetizationsample.kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import apero.aperosg.monetization.screenflow.registerSplashAdsListener
import apero.aperosg.monetization.util.showBannerAd
import apero.aperosg.monetizationsample.AdsProvider
import apero.aperosg.monetizationsample.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class KotlinSplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        // Reset ads state (if needed)
        AdsProvider.interSplash.releaseAll()
        AdsProvider.bannerSplash.releaseAll()

        // Load inter splash and banner splash
        AdsProvider.interSplash.loadAds(this)
        AdsProvider.bannerSplash.loadAds(this)

        // Show banner splash
        showBannerAd(AdsProvider.bannerSplash, binding.bannerFrame)

        // Register splash ads listener
        this.registerSplashAdsListener(
            interSplash = AdsProvider.interSplash,
            bannerSplash = AdsProvider.bannerSplash,
            onNextAction = {
                // Move to next screen here
                finish()
            },
            onAdClosed = {
                // This is called when user close interstitial
                // Normally we don't do anything here
                // This override can be deleted if you don't use it

            },
            onAdFailedToShow = {
                // This is called when interstitial failed to show
                // This override can be deleted if you don't use it
            },
            onAdImpression = {
                // This is called when ad is counted as impression
                // This can be deleted if you don't use it
            },
            onAdClicked = {
                // This is called when ad is clicked
                // This can be deleted if you don't use it
            }
        )
    }
}