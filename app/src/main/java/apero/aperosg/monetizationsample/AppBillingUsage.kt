package apero.aperosg.monetizationsample

import android.app.Activity
import com.astronex.monetization.billing.AppBilling
import com.astronex.monetization.billing.model.SubscriptionBillingItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppBillingUsage {

    /**
     *Check your premium status
     * - true: If you have purchased any IAP item
     * - We have handled turn off ads if true
     */
    fun isPremium(): Flow<Boolean> {
        return AppBilling.isPurchasedFlow
    }

    /**
     * Get the full price of your IAP item
     */
    fun getFullPrice(billingItemInfo: BillingItemInfo): String {
        val subscription =
            BillingItemProvider.all.find { it.productId == billingItemInfo.productId && it.offerId == billingItemInfo.offerId }
                ?: return ""
        return AppBilling.getFullPriceText(subscription)
    }

    /**
     * Check if your IAP item is available for purchase on Google Play
     */
    fun isBillingItemAvailable(billingItemInfo: BillingItemInfo): Boolean {
        val subscription =
            BillingItemProvider.all.find { it.productId == billingItemInfo.productId && it.offerId == billingItemInfo.offerId }
        if (subscription != null) {
            return AppBilling.isBillingItemAvailable(subscription)
        } else {
            return false
        }
    }

    /**
     * Get information about the IAP item that the user has successfully purchased via Google Play
     */
    fun getCurrentSubscription(): Flow<List<BillingItemInfo>> {
        return AppBilling.purchasedItemMapFlow.map { map ->
            map.values.mapNotNull { billingItem ->
                val purchasedItem = billingItem.billingItem
                if (purchasedItem is SubscriptionBillingItem) {
                    val billingList = listOf(
                        BillingItemProvider.monthlyOffer,
                        BillingItemProvider.monthlyWithDiscount50PercentOffer,
                        BillingItemProvider.weeklyOffer,
                    )

                    billingList.find { it.productId == purchasedItem.productId && it.offerId == purchasedItem.offerId }
                } else {
                    null
                }
            }
        }
    }

    /**
     * Handles the purchase of your IAP item
     * */
    fun purchase(
        activity: Activity,
        billingItemInfo: BillingItemInfo,
        onFailure: (String) -> Unit,
        onSuccess: (BillingItemInfo) -> Unit,
    ) {
        val billingItem =
            BillingItemProvider.all.find { it.productId == billingItemInfo.productId && it.offerId == billingItemInfo.offerId }
                ?: return

        AppBilling.purchase(
            activity = activity,
            billingItem = billingItem,
            onFailure = onFailure,
            onSuccess = { billingItem, purchase ->
                onSuccess(billingItemInfo)
            }
        )
    }

}