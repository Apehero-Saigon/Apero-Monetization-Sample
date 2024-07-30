package apero.aperosg.monetizationsample.java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import apero.aperosg.monetization.adgroup.NativeAdGroup;
import apero.aperosg.monetization.util.AdsExtensionKt;
import apero.aperosg.monetizationsample.R;
import apero.aperosg.monetizationsample.databinding.ActivityNativeDupBinding;

import apero.aperosg.monetizationsample.AdsProvider;
import apero.aperosg.monetizationsample.kotlin.KotlinNativeDupActivity;

/**
 * This Activity class demonstrates how to show duplicate native ads by going to another screen
 * that looks exactly like previous screen, the difference is the showing native ad.
 * We also preload native ads for duplicate screen when in non-duplicate screen.
 **/
public class JavaNativeDupActivity extends AppCompatActivity {
    private ActivityNativeDupBinding binding;
    private Boolean isDupScreen = false;

    private final NativeAdGroup native3Floors = AdsProvider.INSTANCE.getNative3Floors();
    private final NativeAdGroup nativeDup2Floors = AdsProvider.INSTANCE.getNativeDup2Floors();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNativeDupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        isDupScreen = getIntent().getBooleanExtra("isDupScreen", false);

        // Preload native ads for dup screen
        if (!isDupScreen) nativeDup2Floors.loadAds(this);

        initViews();
        showNativeAds();
    }

    private void initViews() {
        if (isDupScreen) binding.showNativeDupBtn.setVisibility(View.GONE);

        binding.showNativeDupBtn.setOnClickListener(v -> {
            // Go to duplicate screen
            startActivity(new Intent(this, KotlinNativeDupActivity.class).putExtra("isDupScreen", true));
            finish();
            // Remove transition animation
            overridePendingTransition(0, 0);
        });
    }

    private void showNativeAds() {
        NativeAdGroup nativeToShow;
        if (isDupScreen) nativeToShow = nativeDup2Floors;
        else nativeToShow = native3Floors;

        // Only call this function once in a screen,
        // This will create a coroutine job and react to native ad's status
        // When another ad is ready, it will be shown automatically
        // Calling this multiple times may cause undefined behaviour
        AdsExtensionKt.showNativeAd(
            this,
            nativeToShow,
            binding.nativeFr,
            R.layout.native_ads,
            0
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Load native ads and reload native ads when user goes back from background
        if (!isDupScreen) native3Floors.loadAds(this);
        else nativeDup2Floors.loadAds(this);
    }
}
