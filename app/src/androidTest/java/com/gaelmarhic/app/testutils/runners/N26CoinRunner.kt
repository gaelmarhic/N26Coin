package com.gaelmarhic.app.testutils.runners

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.setThreadPolicy
import android.support.test.runner.AndroidJUnitRunner
import com.gaelmarhic.app.testutils.application.N26CoinTestApplication

/**
 * Created by Gaël Marhic on 08/01/2019.
 */
class N26CoinRunner: AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle) {
        setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        super.onCreate(arguments)
    }

    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application =
            super.newApplication(cl, N26CoinTestApplication::class.java.name, context)
}
