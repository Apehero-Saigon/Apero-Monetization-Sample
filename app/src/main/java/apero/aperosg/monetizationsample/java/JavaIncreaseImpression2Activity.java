package apero.aperosg.monetizationsample.java;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import apero.aperosg.monetizationsample.AdsProvider;

import apero.aperosg.monetization.adgroup.NativeAdGroup;
import apero.aperosg.monetization.util.AdsExtensionKt;
import apero.aperosg.monetizationsample.R;
import apero.aperosg.monetizationsample.databinding.ActivityIncreaseImpression2Binding;
/**
 * This Activity class demonstrate how to increase impression of
 * native ad of next screen when interstitial is showing.
 * This Activity is the activity to show native ad.
 * Open Logcat with keyword "onAdImpression" for more insight
 * @see JavaIncreaseImpression1Activity
 **/
public class JavaIncreaseImpression2Activity extends AppCompatActivity {
    private ActivityIncreaseImpression2Binding binding;
    private final NativeAdGroup nativeIncreaseImpression = AdsProvider.INSTANCE.getNativeIncreaseImpression();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncreaseImpression2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        AdsExtensionKt.showNativeAd(
            this,
            nativeIncreaseImpression,
            binding.nativeFr,
            R.layout.native_ads
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Load native ads
        nativeIncreaseImpression.loadAds(this);
    }
}
