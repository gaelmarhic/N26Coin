package com.gaelmarhic.presentation.features.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.gaelmarhic.presentation.features.bitcoin.activities.BitcoinMarketPriceActivity

/**
 * Created by Gaël Marhic on 07/01/2019.
 */
class SplashActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({

            startActivity(Intent(this, BitcoinMarketPriceActivity::class.java))
            finish()
        }, 1500)
    }
}
