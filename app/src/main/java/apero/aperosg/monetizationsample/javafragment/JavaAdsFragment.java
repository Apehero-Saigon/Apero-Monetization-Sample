package apero.aperosg.monetizationsample.javafragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import apero.aperosg.monetization.adgroup.BannerAdGroup;
import apero.aperosg.monetization.adgroup.NativeAdGroup;
import apero.aperosg.monetization.util.AdsExtensionKt;
import apero.aperosg.monetizationsample.R;
import apero.aperosg.monetizationsample.databinding.FragmentAdsBinding;

import apero.aperosg.monetizationsample.AdsProvider;

/**
 * This Fragment class demonstrate showing showing regular native ads, banner ads in Java Fragments
 **/
public class JavaAdsFragment extends Fragment {
    private FragmentAdsBinding binding;
    private final NativeAdGroup native3Floors = AdsProvider.INSTANCE.getNative3Floors();
    private final BannerAdGroup banner = AdsProvider.INSTANCE.getBanner();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAdsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showNativeAds();
        showBannerAds();
    }

    private void showNativeAds() {
        // Only call this function once in a screen,
        // This will create a coroutine job and react to native ad's status
        // When another ad is ready, it will be shown automatically
        // Calling this multiple times may cause undefined behaviour
        AdsExtensionKt.showNativeAd(
                this, native3Floors, binding.nativeFr,
                R.layout.native_ads
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


    @Override
    public void onResume() {
        super.onResume();

        native3Floors.loadAds(requireContext());
        banner.loadAds(requireContext());
    }
}
