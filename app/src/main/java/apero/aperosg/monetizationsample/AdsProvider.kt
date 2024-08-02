package apero.aperosg.monetizationsample

import apero.aperosg.monetization.adgroup.BannerAdGroup
import apero.aperosg.monetization.adgroup.InterstitialAdGroup
import apero.aperosg.monetization.adgroup.NativeAdGroup
import apero.aperosg.monetization.adgroup.RewardAdGroup
import apero.aperosg.monetization.adunit.RewardAdUnit

/**
 * Singleton class holding all ads objects
 **/
object AdsProvider {
    val native3Floors = NativeAdGroup(
        BuildConfig.native_ad_high to "native_ad_high",
        BuildConfig.native_ad_medium to "native_ad_medium",
        BuildConfig.native_ad to "native_ad",
        name = "native_3_floors",
    )
    val nativeDup2Floors = NativeAdGroup(
        BuildConfig.native_ad_dup_high to "native_ad_dup_high",
        BuildConfig.native_ad_dup to "native_ad_dup",
        name = "native_dup_2_floors",
    )

    val inter2Floors = InterstitialAdGroup(
        BuildConfig.interstitial_ad_high to "interstitial_ad_high",
        BuildConfig.interstitial_ad to "interstitial_ad",
        name = "inter_3_floors",
    )

    val reward1Floor = RewardAdGroup(
        BuildConfig.reward_ad to "reward_ad",
        name = "reward_1_floor",
        rewardType = RewardAdUnit.RewardAdType.RewardVideo,
    )

    val rewardInter1Floor = RewardAdGroup(
        BuildConfig.reward_inter_ad to "reward_inter_ad",
        name = "reward_inter_1_floor",
        rewardType = RewardAdUnit.RewardAdType.RewardInterstitial,
    )

    val nativeIncreaseImpression = NativeAdGroup(
        BuildConfig.native_ad to "native_increase_impression",
        name = "native_increase_impression",
    )

    val banner = BannerAdGroup(
        BuildConfig.banner_ad to "banner",
        name = "banner",
    )

    val interSplash = InterstitialAdGroup(
        BuildConfig.interstitial_ad to "inter_splash_high",
        BuildConfig.interstitial_ad to "inter_splash",
        name = "inter_splash",
    )

    val bannerSplash = BannerAdGroup(
        BuildConfig.banner_ad to "banner_splash",
        name = "banner_splash",
    )
}