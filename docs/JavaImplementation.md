Apero Monetization Java Demo
==================

Implementation for loading and showing ads if Activity/Fragment class is written in Java.
Some code will still be written in Kotlin.

Refer to demo Activities and Fragments inside package ``java`` and ``javafragment`` for more detailed code.

# Banner ads
<details>
   <summary>Show content</summary>

## Load ads
```java
// Declare a variable pointing to AdsProvider's AdGroup
private final BannerAdGroup bannerAd = AdsProvider.INSTANCE.getBannerAd();
...
// Inside activity
bannerAd.loadAds(this);
// Inside fragment
bannerAd.loadAds(requireActivity());
// Or you can provide app instance to load it anywhere
bannerAd.loadAds(App.instance);
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
   ```java
   AdsExtensionKt.showBannerAd(this, bannerAd, binding.bannerFr);
   ```
## Reload ads
We reload ads when user goes back from background or when click ads. 
In order to reload ads, just put the load function into onResume of Activities/Fragments:
```java
@Override
public void onResume() {
    super.onResume();

   bannerAd.loadAds(requireActivity());
}
```
</details>
   
# Native ads
<details>
   <summary>Show content</summary>

## Load ads
```java
// Declare a variable pointing to AdsProvider's AdGroup
private final NativeAdGroup nativeAd = AdsProvider.INSTANCE.getNativeAd();
...
// Inside activity
nativeAd.loadAds(this);
// Inside fragment
nativeAd.loadAds(requireActivity());
// Or you can provide app instance to load it anywhere
nativeAd.loadAds(App.instance);
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
   ```java
   AdsExtensionKt.showNativeAd(this, nativeAd, binding.nativeFr, R.layout.native_ads);
   ```
   ``R.layout.native_ads`` is native ads layout id in normal case. To show meta ads layout, add additional layout id into the function:
   ```java
   AdsExtensionKt.showNativeAd(
       this, nativeAd, binding.nativeFr, R.layout.native_ads, R.layout.native_ads_meta
   );
   ```
## Reload ads
We reload ads when user goes back from background or when click ads.
In order to reload ads, just put the load function into onResume of Activities/Fragments:
```java
@Override
public void onResume() {
    super.onResume();

   nativeAd.loadAds(requireActivity());
}
```
</details>

# Interstitial ads
<details>
   <summary>Show content</summary>

## Load ads
```java
// Declare a variable pointing to AdsProvider's AdGroup
private final InterstitialAdGroup interAd = AdsProvider.INSTANCE.getInterAd();
...
// Inside activity
interAd.loadAds(this);
// Inside fragment
interAd.loadAds(requireActivity());
// Or you can provide app instance to load it anywhere
interAd.loadAds(App.instance);
```
## Show ads
To show ads, call ``.showAds(context, callback)`` function with appropriate callback
```java
interAd.showAds(
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
            // This is called right after calling show ads regardless even if inter ad is shown or not
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
```
We can also check the ads status before showing if needed:
```java
if (!interAd.isAdReady()) {
   Toast.makeText(this, "Ads is not ready", Toast.LENGTH_SHORT).show();
} else {
    interAd.showAds(context, callback);
}
```

#### Important: If you use Activity and have to finish previous Activity, put the finish() inside onAdClosed instead of onNextAction.
</details>

# Reward video/interstitial ads
<details>
   <summary>Show content</summary>

## Load ads
```java
// Declare a variable pointing to AdsProvider's AdGroup
private final RewardAdGroup rewardAd = AdsProvider.INSTANCE.getRewardAd();
...
// Inside activity
rewardAd.loadAds(this);
// Inside fragment
rewardAd.loadAds(requireActivity());
// Or you can provide app instance to load it anywhere
rewardAd.loadAds(App.instance);
```
## Show ads
To show ads, call ``.showAds(context, callback)`` function with appropriate callback
```java
rewardAd.showAds(
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
```
We can also check the ads status before showing if needed:
```java
if (!rewardAd.isAdReady()) {
   Toast.makeText(this, "Ads is not ready", Toast.LENGTH_SHORT).show();
} else {
   rewardAd.showAds(context, callback);
}
```
</details>

# App open ads
<details>
   <summary>Show content</summary>

## Load ads
```java
// Declare a variable pointing to AdsProvider's AdGroup
private final AppOpenAdGroup appOpenAd = AdsProvider.INSTANCE.getAppOpenAd();
...
// Inside activity
appOpenAd.loadAds(this);
// Inside fragment
appOpenAd.loadAds(requireActivity());
// Or you can provide app instance to load it anywhere
appOpenAd.loadAds(App.instance);
```
## Show ads
To show ads, call ``.showAds(context, callback)`` function with appropriate callback
```java
appOpenAd.showAds(
    this,
    new AppOpenAdCallback() {
        @Override
        public void onNextAction(Boolean adShown) {
            // This is called right after calling show ads regardless even if app open ad is shown or not
            // You should move to next screen here
            // You can check if ad shown with the boolean value in this callback

        }

        @Override
        public void onAdClosed() {
            super.onAdClosed();
            // This is called right after calling show ads regardless even if app open ad is shown or not
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
```

</details>
