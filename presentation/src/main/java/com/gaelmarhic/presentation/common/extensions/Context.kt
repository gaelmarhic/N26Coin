package com.gaelmarhic.presentation.common.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by Gaël Marhic on 07/01/2019.
 */

/**
 * Extension function used to send a toast from a context.
 * @param message The message to display in the toast.
 */
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
