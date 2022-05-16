package com.veldan.veldanslots.game.screens.shop

import androidx.annotation.MainThread
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.QueryProductDetailsParams
import com.badlogic.gdx.utils.Disposable
import com.veldan.veldanslots.game.game
import com.veldan.veldanslots.game.utils.controller.ScreenController
import com.veldan.veldanslots.utils.billing.BillingUtil
import com.veldan.veldanslots.utils.cancelCoroutinesAll
import com.veldan.veldanslots.utils.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class ShopScreenController(
    override val screen: ShopScreen
) : ScreenController, Disposable {

    val coroutineMain = CoroutineScope(Dispatchers.Default)

    val miniPriceFlow  = MutableStateFlow("")
    val superPriceFlow = MutableStateFlow("")
    val megaPriceFlow  = MutableStateFlow("")

    var miniProductDetails : ProductDetails? = null
    var superProductDetails: ProductDetails? = null
    var megaProductDetails : ProductDetails? = null



    override fun dispose() {
        cancelCoroutinesAll(coroutineMain)
    }



    suspend fun initProductDetails() {
        log("Billing start")
        BillingUtil.startConnection()
        log("Billing finish")

        val productList = listOf<QueryProductDetailsParams.Product>(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("id_mini_0.25")
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("id_super_0.5")
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("id_mega_1.0")
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),
        )

        val productDetailsList = BillingUtil.getProductDetailsList(productList)

        productDetailsList?.onEach { productDetails ->
            when (productDetails.productId) {
                "id_mini_0.25" -> miniProductDetails  = productDetails
                "id_super_0.5" -> superProductDetails = productDetails
                "id_mega_1.0"  -> megaProductDetails  = productDetails
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