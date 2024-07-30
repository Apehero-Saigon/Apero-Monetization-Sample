package apero.aperosg.monetizationsample.java;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import apero.aperosg.monetizationsample.AdsProvider;
import apero.aperosg.monetizationsample.App;

import apero.aperosg.monetization.adgroup.InterstitialAdGroup;
import apero.aperosg.monetization.adgroup.NativeAdGroup;
import apero.aperosg.monetization.java.InterstitialShowAdCallback;
import apero.aperosg.monetizationsample.databinding.ActivityIncreaseImpression1Binding;

/**
 * This Activity class demonstrate how to increase impression of
 * native ad of next screen when interstitial is showing.
 * This Activity is the activity to show interstitial ad
 * Open Logcat with keyword "onAdImpression" for more insight
 * @see JavaIncreaseImpression2Activity
 **/
public class JavaIncreaseImpression1Activity extends AppCompatActivity {
    private ActivityIncreaseImpression1Binding binding;

    private final InterstitialAdGroup inter2Floors = AdsProvider.INSTANCE.getInter2Floors();
    private final NativeAdGroup nativeIncreaseImpression = AdsProvider.INSTANCE.getNativeIncreaseImpression();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncreaseImpression1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.interBtn.setOnClickListener(v -> {
            showInterstitialAds();
        });
    }

    private void showInterstitialAds() {
        if (!inter2Floors.isAdReady()) {
            Toast.makeText(this, "Ads is not ready", Toast.LENGTH_SHORT).show();
        } else {
            inter2Floors.showAds(
                this,
                new InterstitialShowAdCallback() {
                    @Override
                    public void onNextAction(Boolean adShown) {
                        // Go to next screen
                        startActivity(new Intent(JavaIncreaseImpression1Activity.this, JavaIncreaseImpression2Activity.class));

                        // This is the interesting part, we delay 2 seconds before calling load ads again.
                        // When showing interstitial, the previous loaded native ad is counted as impression
                        // After 2 seconds, we load another ad so that when user close interstitial, another ad will show
                        // Feels free to adjust this number, 2 seconds is the number that I picked to work best for my setup
                        if (adShown) {
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(() -> {
                                nativeIncreaseImpression.loadAds(App.instance);
                            }, 2000);
                        }
                    }
                }
            );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Preload interstitial ad
        inter2Floors.loadAds(this);

        // Preload next screen native ad so that it can show when interstitial is showing
        nativeIncreaseImpression.loadAds(this);
    }
}
