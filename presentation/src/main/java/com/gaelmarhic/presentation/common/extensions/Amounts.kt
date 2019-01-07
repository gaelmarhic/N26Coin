package com.gaelmarhic.presentation.common.extensions

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * Created by Gaël Marhic on 07/01/2019.
 */

/**
 * Extension function used to apply a thousand separator on a [Long].
 *
 * @param separator The separator to apply.
 * @return Returns the formatted number.
 */
fun Long.applyThousandsSeparator(separator: Char): String {
    val formatter = NumberFormat.getInstance() as DecimalFormat
    val symbols = formatter.decimalFormatSymbols.apply {
        groupingSeparator = separator
    }
    formatter.decimalFormatSymbols = symbols
    return formatter.format(this)
}
