package apero.aperosg.monetizationsample.kotlinfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import apero.aperosg.monetization.util.showBannerAd
import apero.aperosg.monetization.util.showNativeAd
import apero.aperosg.monetizationsample.R
import apero.aperosg.monetizationsample.databinding.FragmentAdsBinding
import apero.aperosg.monetizationsample.AdsProvider

/**
 * This Fragment class demonstrate showing showing regular native ads, banner ads in Kotlin Fragments
 **/
class KotlinAdsFragment: Fragment() {
    private lateinit var binding: FragmentAdsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAdsBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showNativeAds()
        showBannerAds()
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
            onMediationCallback = { mediationClassName ->

            }
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


    override fun onResume() {
        super.onResume()

        AdsProvider.native3Floors.loadAds(requireContext())
        AdsProvider.banner.loadAds(requireContext())
    }
}