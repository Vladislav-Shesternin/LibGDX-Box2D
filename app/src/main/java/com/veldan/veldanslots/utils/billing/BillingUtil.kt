package com.veldan.veldanslots.utils.billing

import android.annotation.SuppressLint
import com.android.billingclient.api.*
import com.veldan.veldanslots.appContext
import com.veldan.veldanslots.utils.log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

object BillingUtil {

    private val connectFlow = MutableSharedFlow<Boolean>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST).apply { tryEmit(false) }

    private val purchasesUpdateListener = PurchasesUpdatedListener { billingResult, purchasesList ->
        log("UPDATE: $billingResult, $purchasesList")
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchasesList != null) {
            handlePurchasesBlock(purchasesList)
        }
    }

    @SuppressLint("StaticFieldLeak")
    val billingClient = BillingClient.newBuilder(appContext)
        .setListener(purchasesUpdateListener)
        .enablePendingPurchases()
        .build()

    var handlePurchasesBlock: (List<Purchase>) -> Unit = {}



    suspend fun startConnection() = CompletableDeferred<Boolean>().also { continuation ->
        CoroutineScope(Dispatchers.IO).launch {
            connectFlow.collect { isConnected ->
                log("Billing isConnected - $isConnected")
                if (isConnected) cancel()

                billingClient.startConnection(object : BillingClientStateListener {
                    override fun onBillingServiceDisconnected() {
                        connectFlow.tryEmit(false)
                    }

                    override fun onBillingSetupFinished(billingResult: BillingResult) {
                        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                            connectFlow.tryEmit(true)
                            continuation.complete(true)
                        } else connectFlow.tryEmit(false)
                    }
                })
            }
        }
    }.await()

    suspend fun getProductDetailsList(
        productList: List<QueryProductDetailsParams.Product>
    ) = CompletableDeferred<List<ProductDetails>?>().also { continuation ->

        val params = QueryProductDetailsParams.newBuilder().setProductList(productList).build()

        withContext(Dispatchers.IO) {
            billingClient.queryProductDetailsAsync(params) { billingResult, productDetailsList ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) continuation.complete(productDetailsList)
                else continuation.complete(null)
            }
        }
    }.await()

    suspend fun consumePurchase(purchase: Purchase, block: (consumeResult: ConsumeResult) -> Unit = {}) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            billingClient.consumePurchase(
                ConsumeParams.newBuilder().setPurchaseToken(purchase.purchaseToken).build()
            ).also { consumeResult -> block(consumeResult) }
        }
    }

    suspend fun acknowledgePurchase(purchase: Purchase, block: (billingResult: BillingResult) -> Unit = {}) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && purchase.isAcknowledged.not()) {
            billingClient.acknowledgePurchase(
                AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.purchaseToken).build()
            ).also { billingResult -> block(billingResult) }
        }
    }

}