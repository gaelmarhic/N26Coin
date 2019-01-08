package com.gaelmarhic.app.testutils.utilities

import android.content.Context
import java.io.IOException

/**
 * Created by Gaël Marhic on 08/01/2019.
 */

/**
 * Function that reads JSON files from the assets folder.
 *
 * @param context Context used to access the assets.
 * @param path Path where the JSON file can be found.
 * @return Returns the JSON in a String format.
 */
fun loadJSONFromAsset(context: Context, path: String): String? {
    return try {
        val inputStream = context.assets.open(path)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    } catch (e: IOException) { null }
}
