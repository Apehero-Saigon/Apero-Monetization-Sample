package apero.aperosg.monetizationsample.kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import apero.aperosg.monetizationsample.AdsProvider
import apero.aperosg.monetizationsample.App
import apero.aperosg.monetizationsample.databinding.ActivityIncreaseImpression1Binding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * This Activity class demonstrate how to increase impression of
 * native ad of next screen when interstitial is showing.
 * This Activity is the activity to show interstitial ad
 * Open Logcat with keyword "onAdImpression" for more insight
 * @see KotlinIncreaseImpression2Activity
 **/
class KotlinIncreaseImpression1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIncreaseImpression1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncreaseImpression1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.interBtn.setOnClickListener {
            showInterstitialAds()
        }
    }

    private fun showInterstitialAds() {
        if (!AdsProvider.inter2Floors.isAdReady()) {
            Toast.makeText(this, "Ads is not ready", Toast.LENGTH_SHORT).show()
        } else {
            AdsProvider.inter2Floors.showAds(
                activity = this,
                onNextAction = { adShown ->
                    // Go to next screen
                    startActivity(Intent(this, KotlinIncreaseImpression2Activity::class.java))

                    // This is the interesting part, we delay 2 seconds before calling load ads again.
                    // When showing interstitial, the previous loaded native ad is counted as impression
                    // After 2 seconds, we load another ad so that when user close interstitial, another ad will show
                    if (adShown) {
                        GlobalScope.launch {
                            // Feels free to adjust this number, 2 seconds is the number that I picked to work best for my setup
                            delay(2000)
                            AdsProvider.nativeIncreaseImpression.loadAds(App.instance)
                        }
                    }
                },
            )
        }
    }

    override fun onResume() {
        super.onResume()

        // Preload interstitial ad
        AdsProvider.inter2Floors.loadAds(this)

        // Preload next screen native ad so that it can show when interstitial is showing
        AdsProvider.nativeIncreaseImpression.loadAds(this)
    }
}