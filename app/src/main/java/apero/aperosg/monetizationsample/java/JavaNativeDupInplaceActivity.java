package apero.aperosg.monetizationsample.java;

import static apero.aperosg.monetization.util.AdsExtensionKt.showNativeAd;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import apero.aperosg.monetizationsample.AdsProvider;

import apero.aperosg.monetization.adgroup.NativeAdGroup;
import apero.aperosg.monetizationsample.R;
import apero.aperosg.monetizationsample.databinding.ActivityNativeDupBinding;
import kotlinx.coroutines.Job;

/**
 * This Activity class demonstrates how to show duplicate native ads in-place
 * (without going to another screen)
 **/
public class JavaNativeDupInplaceActivity extends AppCompatActivity {
    private ActivityNativeDupBinding binding;

    private final NativeAdGroup native3Floors = AdsProvider.INSTANCE.getNative3Floors();
    private final NativeAdGroup nativeDup2Floors = AdsProvider.INSTANCE.getNativeDup2Floors();

    // Variable indicating if current screen is duplicate
    private Boolean isDupAds = false;

    private Job nativeAdJob = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNativeDupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        showNativeAds();
    }

    private void initViews() {
        binding.showNativeDupBtn.setOnClickListener(v -> {
            isDupAds = true;
            binding.showNativeDupBtn.setVisibility(View.GONE);
            showNativeAds();
        });
    }


    private void showNativeAds() {
        NativeAdGroup nativeToShow;
        if (isDupAds) nativeToShow = nativeDup2Floors;
        else nativeToShow = native3Floors;

        // This will create a coroutine job and react to native ad's status
        // When another ad is ready, it will be shown automatically
        // Calling this multiple times may cause undefined behaviour
        // To show duplicate ad, we cancel previous coroutine job and start a new coroutine with duplicate ad
        if (nativeAdJob != null) nativeAdJob.cancel(null);
        nativeAdJob = showNativeAd(
                this,
                nativeToShow,
                binding.nativeFr,
                R.layout.native_ads
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Load native ads and reload native ads when user goes back from background
        if (!isDupAds) native3Floors.loadAds(this);
        // Preload native ads dup or reload if already showing dup
        nativeDup2Floors.loadAds(this);
    }
}
