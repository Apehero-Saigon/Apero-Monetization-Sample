Apero Monetization Kotlin Demo
==================

Implementation for loading and showing ads if Activity/Fragment class is written in Kotlin.

Refer to demo Activities and Fragments inside package ``kotlin`` and ``kotlinfragment`` for more detailed code.

# Banner ads
<details>
   <summary>Show content</summary>

## Load ads
```kotlin
// Inside activity
AdsProvider.bannerAd.loadAds(this)
// Inside fragment
AdsProvider.bannerAd.loadAds(requireActivity())
// Or you can provide app instance to load it anywhere
AdsProvider.bannerAd.loadAds(App.instance)
```
## Show ads
1. Add a FrameLayout to screen layout
    ```xml
    <FrameLayout
        android:id="@+id/bannerFr"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
    ```
2. Call show ads function inside activity/fragment
   ```kotlin
   showBannerAd(this, AdsProvider.bannerAd, binding.bannerFr)
   ```
## Reload ads
We reload ads when user goes back from background or when click ads.
In order to reload ads, just put the load function into onResume of Activities/Fragments:
```kotlin
override fun onResume() 
    super.onResume()

   AdsProvider.bannerAd.loadAds(requireActivity())
}
```
</details>

# Native ads
<details>
   <summary>Show content</summary>

## Load ads
```kotlin
...
// Inside activity
AdsProvider.nativeAd.loadAds(this)
// Inside fragment
AdsProvider.nativeAd.loadAds(requireActivity())
// Or you can provide app instance to load it anywhere
AdsProvider.nativeAd.loadAds(App.instance)
```
## Show ads
1. Add a FrameLayout to screen layout
    ```xml
    <FrameLayout
        android:id="@+id/nativeFr"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
    ```
2. Call show ads function inside activity/fragment
   ```kotlin
   showNativeAd(this, AdsProvider.nativeAd, binding.nativeFr, R.layout.native_ads)
   ```
   ``R.layout.native_ads`` is native ads layout id in normal case. To show meta ads layout, add additional layout id into the function:
   ```kotlin
   showNativeAd(
       this, AdsProvider.nativeAd, binding.nativeFr, R.layout.native_ads, R.layout.native_ads_meta
   )
   ```
## Reload ads
We reload ads when user goes back from background or when click ads.
In order to reload ads, just put the load function into onResume of Activities/Fragments:
```kotlin
override fun onResume() 
    super.onResume()

   AdsProvider.nativeAd.loadAds(requireActivity())
}
```
</details>

# Interstitial ads
<details>
   <summary>Show content</summary>

## Load ads
```kotlin
...
// Inside activity
AdsProvider.interAd.loadAds(this)
// Inside fragment
AdsProvider.interAd.loadAds(requireActivity())
// Or you can provide app instance to load it anywhere
AdsProvider.interAd.loadAds(App.instance)
```
## Show ads
To show ads, call ``.showAds(context, callback)`` function with appropriate callback
```kotlin
AdsProvider.interAd.showAds(
   activity = this,
   onNextAction = { adShown ->
      // This is called right after calling show ads regardless even if inter ad is shown or not
      // You should move to next screen here
      // You can check if ad shown with the boolean value in this callback
   },
   onAdClosed = {
      // This is called when user close the ad
      // Normally we don't do anything here because we already move screen in onNextAction
      // This callback can be deleted if you don't use it
   },
   onAdShowed = { adId ->
      // This is called when interstitial starts to show
      // This callback can be deleted if you don't use it
   },
   onAdFailedToShow = {
      // This is called when interstitial failed to show
      // This callback can be deleted if you don't use it
   },
   onAdImpression = { adId ->
      // This is called when ad is counted as impression
      // This can be deleted if you don't use it
   },
   onAdClicked = { adId ->
      // This is called when ad is clicked
      // This can be deleted if you don't use it
   }
)
```
We can also check the ads status before showing if needed:
```kotlin
if (!AdsProvider.interAd.isAdReady()) {
   Toast.makeText(this, "Ads is not ready", Toast.LENGTH_SHORT).show()
} else {
    AdsProvider.interAd.showAds(context, ...)
}
```
#### Important: If you use Activity and have to finish previous Activity, put the finish() inside onAdClosed instead of onNextAction.
</details>

# Reward video/interstitial ads
<details>
   <summary>Show content</summary>

## Load ads
```kotlin
// Inside activity
AdsProvider.rewardAd.loadAds(this)
// Inside fragment
AdsProvider.rewardAd.loadAds(requireActivity())
// Or you can provide app instance to load it anywhere
AdsProvider.rewardAd.loadAds(App.instance)
```
## Show ads
To show ads, call ``.showAds(context, callback)`` function with appropriate callback
```kotlin
AdsProvider.rewardAd.showAds(
   activity = this,
   onNextAction = {
      // This is called right after calling show ads regardless even if reward ad is shown or not
      // Normally we don't do anything here
   },
   onUserEarnedReward = {
      // This is called when user finished watch the ad
      // You should move to next screen here
   },
   onAdShowed = { adId ->
      // This is called when reward starts to show
      // This override can be deleted if you don't use it
   },
   onAdFailedToShow = {
      // This is called when reward failed to show
      // This override can be deleted if you don't use it
   },
   onAdImpression = { adId ->
      // This is called when ad is counted as impression
      // This can be deleted if you don't use it
   },
   onAdClicked = { adId ->
      // This is called when ad is clicked
      // This can be deleted if you don't use it
   }
)
```
We can also check the ads status before showing if needed:
```kotlin
if (!AdsProvider.rewardAd.isAdReady()) {
   Toast.makeText(this, "Ads is not ready", Toast.LENGTH_SHORT).show()
} else {
   AdsProvider.rewardAd.showAds(context, callback)
}
```
</details>

# App open ads
<details>
   <summary>Show content</summary>

## Load ads
```kotlin
// Inside activity
AdsProvider.appOpenAd.loadAds(this)
// Inside fragment
AdsProvider.appOpenAd.loadAds(requireActivity())
// Or you can provide app instance to load it anywhere
AdsProvider.appOpenAd.loadAds(App.instance)
```
## Show ads
To show ads, call ``.showAds(context, callback)`` function with appropriate callback
```kotlin
AdsProvider.appOpenAd.showAds(
   activity = this,
   onNextAction = { adShown ->
      // This is called right after calling show ads regardless even if inter ad is shown or not
      // You should move to next screen here
      // You can check if ad shown with the boolean value in this callback
   },
   onAdClosed = {
      // This is called when user close the ad
      // Normally we don't do anything here because we already move screen in onNextAction
      // This callback can be deleted if you don't use it
   },
   onAdShowed = { adId ->
      // This is called when interstitial starts to show
      // This callback can be deleted if you don't use it
   },
   onAdFailedToShow = {
      // This is called when interstitial failed to show
      // This callback can be deleted if you don't use it
   },
   onAdImpression = { adId ->
      // This is called when ad is counted as impression
      // This can be deleted if you don't use it
   },
   onAdClicked = { adId ->
      // This is called when ad is clicked
      // This can be deleted if you don't use it
   }
)
```
</details>
