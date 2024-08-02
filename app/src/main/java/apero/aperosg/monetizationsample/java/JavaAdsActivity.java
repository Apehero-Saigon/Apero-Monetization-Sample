package apero.aperosg.monetizationsample.java;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ads.control.ads.wrapper.ApAdError;
import com.ads.control.ads.wrapper.ApRewardItem;
import apero.aperosg.monetizationsample.AdsProvider;
import apero.aperosg.monetizationsample.kotlin.KotlinIncreaseImpression1Activity;
import apero.aperosg.monetizationsample.kotlin.KotlinNativeDupActivity;
import apero.aperosg.monetizationsample.kotlin.KotlinNativeDupInplaceActivity;
import com.google.android.gms.ads.AdError;

import apero.aperosg.monetization.adgroup.BannerAdGroup;
import apero.aperosg.monetization.adgroup.InterstitialAdGroup;
import apero.aperosg.monetization.adgroup.NativeAdGroup;
import apero.aperosg.monetization.adgroup.RewardAdGroup;
import apero.aperosg.monetization.java.InterstitialShowAdCallback;
import apero.aperosg.monetization.java.RewardShowAdCallback;
import apero.aperosg.monetization.util.AdsExtensionKt;
import apero.aperosg.monetizationsample.R;
import apero.aperosg.monetizationsample.databinding.ActivityAdsBinding;


/**
 * This Activity class demonstrate showing showing regular native ads, banner ads, interstitial ads and reward ads
 * and reload them when user click ads/goes back from background
 */
public class JavaAdsActivity extends AppCompatActivity {
    private ActivityAdsBinding binding;

    private final InterstitialAdGroup inter2Floors = AdsProvider.INSTANCE.getInter2Floors();
    private final RewardAdGroup reward1Floor = AdsProvider.INSTANCE.getReward1Floor();
    private final RewardAdGroup rewardInter1Floor = AdsProvider.INSTANCE.getRewardInter1Floor();
    private final NativeAdGroup native3Floors = AdsProvider.INSTANCE.getNative3Floors();
    private final BannerAdGroup banner = AdsProvider.INSTANCE.getBanner();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        showNativeAds();
        showBannerAds();
    }

    private void initViews() {
        binding.interBtn.setOnClickListener(v -> {
            showInterstitialAds();
        });

        binding.rewardBtn.setOnClickListener(v -> {
            showRewardAds();
        });

        binding.rewardInterBtn.setOnClickListener(v -> {
            showRewardInterAds();
        });

        binding.nativeDupBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, KotlinNativeDupActivity.class));
        });

        binding.nativeDupInplaceBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, KotlinNativeDupInplaceActivity.class));
        });

        binding.increaseImpressionBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, KotlinIncreaseImpression1Activity.class));
        });
    }

    /**
     *  Refer to documentation for full parameters of the function
     *  Additional parameters:
     *  - facebookAdLayout: layout to show for facebook meditation, null if only use normal layout id
     *  - keepAdsWhenLoading: keep showing old ads when loading for new ads
     *  - onMediationCallback: callback mediation adapter name of showing native
     **/
    private void showNativeAds() {
        // Only call this function once in a screen,
        // This will create a coroutine job and react to native ad's status
        // When another ad is ready, it will be shown automatically
        // Calling this multiple times may cause undefined behaviour
        AdsExtensionKt.showNativeAd(
                this, native3Floors, binding.nativeFr, R.layout.native_ads
        );
    }

    private void showBannerAds() {
        // Only call this function once in a screen,
        // This will create a coroutine job and react to banner ad's status
        // When another ad is ready, it will be shown automatically
        // Calling this multiple times may cause undefined behaviour
        AdsExtensionKt.showBannerAd(
                this, banner, binding.bannerFr
        );
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
                            // This is called right after calling show ads regardless even if inter ad is shown or not
                            // You should move to next screen here
                            // You can check if ad shown with the boolean value in this callback

                        }

                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();
                            // This is called when user close interstitial
                            // Normally we don't do anything here
                            // This override can be deleted if you don't use it
                        }

                        @Override
                        public void onAdShowed(String adId) {
                            super.onAdShowed(adId);
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
                        public void onAdImpression(String adId) {
                            super.onAdImpression(adId);
                            // This is called when ad is counted as impression
                            // This can be deleted if you don't use it
                        }

                        // This can be deleted if you don't use it
                        @Override
                        public void onAdClicked(String adId) {
                            super.onAdClicked(adId);
                            // This is called when ad is clicked
                            // This can be deleted if you don't use it
                        }
                    }
            );
        }
    }

    private void showRewardAds() {
        if (!reward1Floor.isAdReady()) {
            Toast.makeText(this, "Ads is not ready", Toast.LENGTH_SHORT).show();
        } else {
            reward1Floor.showAds(
                    this,
                    new RewardShowAdCallback() {

                        @Override
                        public void onUserEarnedReward(ApRewardItem reward) {
                            // This is called when user finished watch the ad
                            // You should move to next screen or give user reward here
                        }

                        @Override
                        public void onNextAction() {
                            // This is called right after calling show ads regardless even if reward ad is shown or not
                            // Normally we don't do anything here

                        }

                        @Override
                        public void onAdShowed(String adId) {
                            super.onAdShowed(adId);
                            // This is called when reward starts to show
                            // This override can be deleted if you don't use it
                        }

                        @Override
                        public void onAdFailedToShow(ApAdError adError) {
                            super.onAdFailedToShow(adError);
                            // This is called when reward failed to show
                            // This override can be deleted if you don't use it
                        }

                        @Override
                        public void onAdImpression(String adId) {
                            super.onAdImpression(adId);
                            // This is called when ad is counted as impression
                            // This can be deleted if you don't use it
                        }

                        @Override
                        public void onAdClicked(String adId) {
                            super.onAdClicked(adId);
                            // This is called when ad is clicked
                            // This can be deleted if you don't use it
                        }
                    }
            );
        }
    }

    private void showRewardInterAds() {
        if (!rewardInter1Floor.isAdReady()) {
            Toast.makeText(this, "Ads is not ready", Toast.LENGTH_SHORT).show();
        } else {
            rewardInter1Floor.showAds(
                    this,
                    new RewardShowAdCallback() {

                        @Override
                        public void onUserEarnedReward(ApRewardItem reward) {
                            // This is called when user finished watch the ad
                            // You should move to next screen or give user reward here
                        }

                        @Override
                        public void onNextAction() {
                            // This is called right after calling show ads regardless even if reward ad is shown or not
                            // Normally we don't do anything here

                        }

                        @Override
                        public void onAdShowed(String adId) {
                            super.onAdShowed(adId);
                            // This is called when reward starts to show
                            // This override can be deleted if you don't use it
                        }

                        @Override
                        public void onAdFailedToShow(ApAdError adError) {
                            super.onAdFailedToShow(adError);
                            // This is called when reward failed to show
                            // This override can be deleted if you don't use it
                        }

                        @Override
                        public void onAdImpression(String adId) {
                            super.onAdImpression(adId);
                            // This is called when ad is counted as impression
                            // This can be deleted if you don't use it
                        }

                        @Override
                        public void onAdClicked(String adId) {
                            super.onAdClicked(adId);
                            // This is called when ad is clicked
                            // This can be deleted if you don't use it
                        }
                    }
            );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        inter2Floors.loadAds(this);
        reward1Floor.loadAds(this);
        rewardInter1Floor.loadAds(this);
        native3Floors.loadAds(this);
        banner.loadAds(this);
    }
}
