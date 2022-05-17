package com.veldan.veldanslots.game.screens.shop

import com.android.billingclient.api.*
import com.badlogic.gdx.utils.Disposable
import com.veldan.veldanslots.game.game
import com.veldan.veldanslots.game.manager.GameDataStoreManager
import com.veldan.veldanslots.game.utils.controller.ScreenController
import com.veldan.veldanslots.utils.billing.BillingUtil
import com.veldan.veldanslots.utils.cancelCoroutinesAll
import com.veldan.veldanslots.utils.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShopScreenController(
    override val screen: ShopScreen
) : ScreenController, Disposable {

    companion object {
        const val MINI_COINS  = 100_000L
        const val SUPER_COINS = 1000_000L
        const val MEGA_COINS  = 1000_000_000L

        const val miniProductId  = "id_mini_100.000"
        const val superProductId = "id_super_1000.000"
        const val megaProductId  = "id_mega_1000.000.000"
    }

    val coroutineMain = CoroutineScope(Dispatchers.Default)

    val miniPriceFlow  = MutableStateFlow("")
    val superPriceFlow = MutableStateFlow("")
    val megaPriceFlow  = MutableStateFlow("")

    var miniProductDetails : ProductDetails? = null
    var superProductDetails: ProductDetails? = null
    var megaProductDetails : ProductDetails? = null



    init {
        BillingUtil.handlePurchasesBlock = ::handlePurchases
    }

    override fun dispose() {
        cancelCoroutinesAll(coroutineMain)
    }



    private fun handlePurchases(purchases: List<Purchase>) {
        CoroutineScope(Dispatchers.IO).launch {
            purchases.onEach { purchase ->
                var result = 0L

                when (purchase.products.first()) {
                    miniProductId -> {
                        BillingUtil.consumePurchase(purchase) { consumeResult ->
                            result = if (consumeResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                                MINI_COINS * purchase.quantity
                            } else 0L
                        }
                    }
                    superProductId -> {
                        BillingUtil.consumePurchase(purchase) { consumeResult ->
                            result = if (consumeResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                                SUPER_COINS * purchase.quantity
                            } else 0L
                        }
                    }
                    megaProductId  -> {
                        BillingUtil.acknowledgePurchase(purchase) { billingResult ->
                            result = if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                                MEGA_COINS
                            } else 0L
                        }
                    }
                }

                GameDataStoreManager.Balance.update { it + result }
            }
        }

    }



    suspend fun initProductDetails() {
        log("Billing start")
        BillingUtil.startConnection()
        log("Billing finish")

        val productList = listOf<QueryProductDetailsParams.Product>(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(miniProductId)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(superProductId)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(megaProductId)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),
        )

        val productDetailsList = BillingUtil.getProductDetailsList(productList)

        productDetailsList?.onEach { productDetails ->
            when (productDetails.productId) {
                miniProductId  -> miniProductDetails = productDetails
                superProductId -> superProductDetails = productDetails
                megaProductId  -> megaProductDetails = productDetails
            }
        }

    }

    fun updateProducts() {
        miniPriceFlow.tryEmit(miniProductDetails?.oneTimePurchaseOfferDetails?.formattedPrice ?: "--")
        superPriceFlow.tryEmit(superProductDetails?.oneTimePurchaseOfferDetails?.formattedPrice ?: "--")
        megaPriceFlow.tryEmit(megaProductDetails?.oneTimePurchaseOfferDetails?.formattedPrice ?: "--")
    }

    fun launchBillingFlow(productDetails: ProductDetails) {
        val productDetailsParamsList = listOf(
            BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(productDetails).build(),
        )

        BillingUtil.billingClient.launchBillingFlow(
            game.activity,
            BillingFlowParams.newBuilder().setProductDetailsParamsList(productDetailsParamsList).build()
        )
    }

}