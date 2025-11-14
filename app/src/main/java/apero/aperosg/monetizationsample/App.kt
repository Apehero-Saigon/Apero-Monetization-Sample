package apero.aperosg.monetizationsample

import android.app.Application
import com.astronex.monetization.AstronexMonetization
import com.astronex.monetization.BuildConfig
import com.astronex.monetization.analytics.adjust.AstronexAdjustConfig
import com.astronex.monetization.analytics.appsflyer.AstronexAppsFlyerConfig
import com.astronex.monetization.billing.AppBilling
import com.astronex.monetization.billing.model.SubscriptionBillingItem

class App: Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        initAds()
        initBilling()
    }

    private fun initAds() {
        AstronexMonetization.init(
            context = this,
            testDeviceIds = listOf(
                "List of test device ids"
            ),
            adjustConfig = AstronexAdjustConfig(
                adjustToken = "Adjust token here",
                adImpressionEvent = "Ad Impression Event Key here",
                isSandbox = BuildConfig.DEBUG
            ),
            appsflyerConfig = AstronexAppsFlyerConfig(
                apiKey = "Appsflyer key here",
                isSandbox = BuildConfig.DEBUG
            ),
        ) {
            // On complete
        }
    }

    private fun initBilling() {
        AppBilling.init(context = this, billingItems = BillingItemProvider.all, BuildConfig.DEBUG)
    }

}

// Purchase Plan - equals BillingItemInfo
data class BillingItemInfo(
    val productId: String,
    val basePlanId: String,
    val activeDays: Int,
    val offerId: String?,
    val name: String,
)

// Purchase Plan Provider
object BillingItemProvider {

    // -------- Weekly Purchase Plan --------
    private val weeklyInfo = SubscriptionBillingItem(
        productId =  "quin.ai.weekly.pro",
        basePlanId = "quin-ai-monthly-pro-notrial",
        offerId = null,
        activeDays = 7
    )
    val weeklyOffer: BillingItemInfo = BillingItemInfo(
        productId = weeklyInfo.productId,
        basePlanId = weeklyInfo.basePlanId,
        activeDays = weeklyInfo.activeDays,
        offerId = weeklyInfo.offerId,
        name = "Weekly Offer",
    )


    // ------ Monthly Purchase Plan --------
    private val monthlyInfo = SubscriptionBillingItem(
        productId = "quin.ai.monthly.pro.notrial",
        basePlanId = "quin-ai-monthly-pro-notrial",
        offerId = "quin-ai-monthly-pro-notrial-off50",
        activeDays = 30
    )
    val monthlyOffer: BillingItemInfo = BillingItemInfo(
        productId = monthlyInfo.productId,
        basePlanId = monthlyInfo.basePlanId,
        activeDays = monthlyInfo.activeDays,
        offerId = monthlyInfo.offerId,
        name = "Monthly Offer",
    )

    //-------- monthly with discount 50% info --------
    private val monthlyWithDiscount50PercentInfo = SubscriptionBillingItem(
        productId = "quin.ai.monthly.pro.trial",
        basePlanId = "quin-ai-monthly-pro-trial",
        offerId = "quin-ai-monthly-pro-trial-off50",
        activeDays = 30,
    )
    val monthlyWithDiscount50PercentOffer = BillingItemInfo(
        productId = monthlyWithDiscount50PercentInfo.productId,
        basePlanId = monthlyWithDiscount50PercentInfo.basePlanId,
        activeDays = monthlyWithDiscount50PercentInfo.activeDays,
        offerId = monthlyWithDiscount50PercentInfo.offerId,
        name = "Monthly With Discount 50% Offer",
    )

    val all = listOf(weeklyInfo, monthlyInfo, monthlyWithDiscount50PercentInfo)
}