package apero.aperosg.monetizationsample.java;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.astronex.monetization.ads.java.InterstitialShowAdCallback;
import com.astronex.monetization.ads.screenflow.SplashFlowUtilKt;
import com.google.android.gms.ads.AdError;

import com.astronex.monetization.ads.adgroup.BannerAdGroup;
import com.astronex.monetization.ads.adgroup.InterstitialAdGroup;
import com.astronex.monetization.util.AdsExtensionKt;
import apero.aperosg.monetizationsample.AdsProvider;
import apero.aperosg.monetizationsample.databinding.ActivitySplashBinding;

@SuppressLint("CustomSplashScreen")
public class JavaSplashActivity extends AppCompatActivity {
    private final InterstitialAdGroup interSplash = AdsProvider.INSTANCE.getInterSplash();
    private final BannerAdGroup bannerSplash = AdsProvider.INSTANCE.getBannerSplash();

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Reset ads state (if needed)
        interSplash.releaseAll();
        bannerSplash.releaseAll();

        // Load inter splash and banner splash
        interSplash.loadAds(this);
        bannerSplash.loadAds(this);

        // Show banner splash
        AdsExtensionKt.showBannerAd(this, bannerSplash, binding.bannerFrame, false, 15);

        // Register splash ads listener
        SplashFlowUtilKt.registerSplashAdsListener(
                this, interSplash, bannerSplash, new InterstitialShowAdCallback() {
                    @Override
                    public void onNextAction(Boolean adShown) {
                        // Move to next screen here
                        finish();
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        // This is called when user close interstitial
                        // Normally we don't do anything here
                        // This override can be deleted if you don't use it
                    }

                    @Override
                    public void onAdShown(String adId, String adName) {
                        super.onAdShown(adId, adName);
                        // This is called when interstitial starts to show
                        // This override can be deleted if you don't use it
                    }

                    @Override
                    public void onAdFailedToShow(AdError adError) {
                        super.onAdFailedToShow(adError);
                        // This is called when interstitial failed to show
                        // This override can be deleted if you don't use it
                    }

                    @Override
                    public void onAdImpression(String adId, String adName) {
                        super.onAdImpression(adId, adName);
                        // This is called when ad is counted as impression
                        // This can be deleted if you don't use it
                    }

                    // This can be deleted if you don't use it
                    @Override
                    public void onAdClicked(String adId, String adName) {
                        super.onAdClicked(adId, adName);
                        // This is called when ad is clicked
                        // This can be deleted if you don't use it
                    }
                }
        );
    }
}
