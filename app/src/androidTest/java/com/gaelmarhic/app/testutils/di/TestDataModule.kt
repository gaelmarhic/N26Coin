package com.gaelmarhic.app.testutils.di

import com.gaelmarhic.app.di.modules.DataModule
import com.gaelmarhic.app.testutils.constants.Constants.Companion.MOCK_WEB_SERVER_BASE_URL

/**
 * Created by Gaël Marhic on 08/01/2019.
 */
class TestDataModule: DataModule() {

    override fun provideBlockchainBaseUrl() = MOCK_WEB_SERVER_BASE_URL
}
