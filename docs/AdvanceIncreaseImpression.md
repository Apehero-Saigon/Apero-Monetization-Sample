Increase native ads impression
==================

Refer to ``JavaIncreaseImpression1Activity`` and ``JavaIncreaseImpression1Activity`` for implementation in Java.

Refer to ``KotlinIncreaseImpression1Activity`` and ``KotlinIncreaseImpression1Activity`` for implementation in Kotlin.

## Principles
Right after showing interstitial ads, there's a a little amount of time that next screen counts as viewed.
During this time, if we show native ads, they will be counted as impression.

## Things to do

### Preload next screen's native ads
```java
nativeIncreaseImpression.loadAds(this);
```

### Show interstitial ads

When showing interstitial ads, put your next screen navigation into ``onNextAction`` callback.

This is an important part, when the interstitial ads shows, next screen native ads is impression, right after that we reload a new native ads.
To achieve this, we put a timer inside ``onNextAction`` to reload native ads:

#### Important: If you use Activity and have to finish previous Activity, put the finish() inside onAdClosed instead of onNextAction.

In Java:
```java
interAd.showAds(
    this,
    new InterstitialShowAdCallback() {
        @Override
        public void onNextAction(Boolean adShown) {
            // Go to next screen
            startActivity(new Intent(JavaIncreaseImpression1Activity.this, JavaIncreaseImpression2Activity.class));

            // This is the interesting part, we delay 2 seconds before calling load ads again.
            // When showing interstitial, the previous loaded native ad is counted as impression
            // After 2 seconds, we load another ad so that when user close interstitial, another ad will show
            // Feels free to adjust this number, 2 seconds is the number that I picked to work best for my setup
            if (adShown) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(() -> {
                    nativeIncreaseImpression.loadAds(App.instance);
                }, 2000);
            }
        }
    }
);
```

In Kotlin:
```kotlin
AdsProvider.inter2Floors.showAds(
    activity = this,
    onNextAction = { adShown ->
        // Go to next screen
        startActivity(Intent(this, KotlinIncreaseImpression2Activity::class.java))

        // This is the interesting part, we delay 2 seconds before calling load ads again.
        // When showing interstitial, the previous loaded native ad is counted as impression
        // After 2 seconds, we load another ad so that when user close interstitial, another ad will show
        if (adShown) {
            GlobalScope.launch {
                // Feels free to adjust this number, 2 seconds is the number that I picked to work best for my setup
                delay(2000)
                AdsProvider.nativeIncreaseImpression.loadAds(App.instance)
            }
        }
    },
)
```

### Show native ads as normal in next screen