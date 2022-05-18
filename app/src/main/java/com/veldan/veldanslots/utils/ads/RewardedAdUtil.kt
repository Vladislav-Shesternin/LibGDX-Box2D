package com.veldan.veldanslots.utils.ads

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.veldan.veldanslots.R
import com.veldan.veldanslots.appContext
import com.veldan.veldanslots.utils.log
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object RewardedAdUtil {

    private var rewardedAd: RewardedAd? = null

    private var isUserEarnedReward = false

    private val fullScreenContentCallback by lazy {
        object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
               if (isUserEarnedReward.not()) onDismissBlock()
            }
        }
    }

    var onDismissBlock: () -> Unit = {}



    suspend fun load(unitRewardedAd: UnitRewardedAd) = CompletableDeferred<Boolean>().also { continuation ->
        val rewardedAdLoadCallback = object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                rewardedAd = null
                continuation.complete(false)
            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                this@RewardedAdUtil.rewardedAd = rewardedAd.apply {
                    fullScreenContentCallback = this@RewardedAdUtil.fullScreenContentCallback
                }
                continuation.complete(true)
            }
        }

        withContext(Dispatchers.Main) {
            RewardedAd.load(
                appContext,
                unitRewardedAd.adUnitId,
                AdRequest.Builder().build(),
                rewardedAdLoadCallback,
            )
        }
    }.await()

    suspend fun show(activity: Activity, onUserEarnedReward: (RewardItem) -> Unit) {
        isUserEarnedReward = false
        withContext(Dispatchers.Main) {
            rewardedAd?.let {
                it.show(activity) { item ->
                    onUserEarnedReward(item)
                    isUserEarnedReward = true
                }
            }
        }
    }



    enum class UnitRewardedAd(val adUnitId: String) {
        FREE_1000_COINS(appContext.resources.getString(R.string.ad_free_1000_coins_id))
    }

}