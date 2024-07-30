plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "apero.aperosg.monetizationsample"
    compileSdk = 34

    defaultConfig {
        applicationId = "apero.aperosg.monetizationsample"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val testInterId = "\"ca-app-pub-3940256099942544/1033173712\""
        val testAppOpenId = "\"ca-app-pub-3940256099942544/9257395921\""
        val testNativeId = "\"ca-app-pub-3940256099942544/2247696110\""
        val testBannerId = "\"ca-app-pub-3940256099942544/6300978111\""
        val testCollapsibleBannerId = "\"ca-app-pub-3940256099942544/2014213617\""
        val testRewardId = "\"ca-app-pub-3940256099942544/5224354917\""
        val testRewardInterId = "\"ca-app-pub-3940256099942544/5354046379\""

        buildConfigField("String", "interstitial_ad_high", testInterId)
        buildConfigField("String", "interstitial_ad_medium", testInterId)
        buildConfigField("String", "interstitial_ad", testInterId)

        buildConfigField("String", "appopen_ad_high", testAppOpenId)
        buildConfigField("String", "appopen_ad_medium", testAppOpenId)
        buildConfigField("String", "appopen_ad", testAppOpenId)

        buildConfigField("String", "native_ad_high", testNativeId)
        buildConfigField("String", "native_ad_medium", testNativeId)
        buildConfigField("String", "native_ad", testNativeId)

        buildConfigField("String", "native_ad_dup_high", testNativeId)
        buildConfigField("String", "native_ad_dup_medium", testNativeId)
        buildConfigField("String", "native_ad_dup", testNativeId)

        buildConfigField("String", "banner_ad_high", testBannerId)
        buildConfigField("String", "banner_ad_medium", testBannerId)
        buildConfigField("String", "banner_ad", testBannerId)

        buildConfigField("String", "collap_banner_ad_high", testCollapsibleBannerId)
        buildConfigField("String", "collap_banner_ad_medium", testCollapsibleBannerId)
        buildConfigField("String", "collap_banner_ad", testCollapsibleBannerId)

        buildConfigField("String", "reward_ad_high", testRewardId)
        buildConfigField("String", "reward_ad_medium", testRewardId)
        buildConfigField("String", "reward_ad", testRewardId)

        buildConfigField("String", "reward_inter_ad_high", testRewardInterId)
        buildConfigField("String", "reward_inter_ad_medium", testRewardInterId)
        buildConfigField("String", "reward_inter_ad", testRewardInterId)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("apero.aperosg.monetization:monetization:1.0.0")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-ktx:1.9.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}

