package apero.aperosg.monetizationsample.kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import apero.aperosg.monetization.util.showBannerAd
import apero.aperosg.monetization.util.showNativeAd
import apero.aperosg.monetizationsample.R
import apero.aperosg.monetizationsample.databinding.ActivityAdsBinding
import apero.aperosg.monetizationsample.AdsProvider

/**
 * This Activity class demonstrate showing showing regular native ads, banner ads, interstitial ads and reward ads
 * and reload them when user click ads/goes back from background
 * */
class KotlinAdsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        showNativeAds()
        showBannerAds()
    }

    private fun initViews() {
        binding.interBtn.setOnClickListener {
            showInterstitialAds()
        }

        binding.rewardBtn.setOnClickListener {
            showRewardAds()
        }

        binding.rewardInterBtn.setOnClickListener {
            showRewardInterAds()
        }

        binding.nativeDupBtn.setOnClickListener {
            startActivity(Intent(this, KotlinNativeDupActivity::class.java))
        }

        binding.nativeDupInplaceBtn.setOnClickListener {
            startActivity(Intent(this, KotlinNativeDupInplaceActivity::class.java))
        }

        binding.increaseImpressionBtn.setOnClickListener {
            startActivity(Intent(this, KotlinIncreaseImpression1Activity::class.java))
        }
    }

    private fun showNativeAds() {
        // Only call this function once in a screen,
        // This will create a coroutine job and react to native ad's status
        // When another ad is ready, it will be shown automatically
        // Calling this multiple times may cause undefined behaviour
        showNativeAd(
            adGroup = AdsProvider.native3Floors,
            frameLayout = binding.nativeFr,
            adLayout = R.layout.native_ads,
            facebookAdLayout = null,
            keepAdsWhenLoading = true,
        )
    }

    private fun showBannerAds() {
        // Only call this function once in a screen,
        // This will create a coroutine job and react to banner ad's status
        // When another ad is ready, it will be shown automatically
        // Calling this multiple times may cause undefined behaviour
        showBannerAd(
            adGroup = AdsProvider.banner,
            frameLayout = binding.bannerFr,
            keepAdsWhenLoading = true,
        )
    }

    private fun showInterstitialAds() {
        if (!AdsProvider.inter2Floors.isAdReady()) {
            Toast.makeText(this, "Ads is not ready", Toast.LENGTH_SHORT).show()
        } else {
            AdsProvider.inter2Floors.showAds(
                activity = this,
                onNextAction = { adShown ->
                    // This is called right after calling show ads regardless even if inter ad is shown or not
                    // You should move to next screen here
                    // You can check if ad shown with the boolean value in this callback
                },
                onAdClosed = {
                    // This is called when user close the ad
                    // Normally we don't do anything here because we already move screen in onNextAction
                },
            )
        }
    }

    private fun showRewardAds() {
        if (!AdsProvider.reward1Floor.isAdReady()) {
            Toast.makeText(this, "Ads is not ready", Toast.LENGTH_SHORT).show()
        } else {
            AdsProvider.reward1Floor.showAds(
                activity = this,
                onUserEarnedReward = {
                    // This is called when user finished watch the ad
                    // You should move to next screen here
                },
                onNextAction = {
                    // This is called right after calling show ads regardless even if reward ad is shown or not
                    // Normally we don't do anything here
                },

            )
        }
    }

    private fun showRewardInterAds() {
        if (!AdsProvider.rewardInter1Floor.isAdReady()) {
            Toast.makeText(this, "Ads is not ready", Toast.LENGTH_SHORT).show()
        } else {
            AdsProvider.rewardInter1Floor.showAds(
                activity = this,
                onNextAction = {
                    // This is called right after calling show ads regardless even if inter ad is shown or not
                    // Normally we don't do anything here
                },
                onUserEarnedReward = {
                    // This is called when user finished watch the ad
                    // You should move to next screen or give user reward here
                },
            )
        }
    }

    override fun onResume() {
        super.onResume()

        AdsProvider.inter2Floors.loadAds(this)
        AdsProvider.reward1Floor.loadAds(this)
        AdsProvider.rewardInter1Floor.loadAds(this)
        AdsProvider.native3Floors.loadAds(this)
        AdsProvider.banner.loadAds(this)
    }
}