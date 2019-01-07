package com.gaelmarhic.presentation.common.providers

import android.annotation.SuppressLint
import java.lang.Exception
import java.sql.Date
import java.sql.Timestamp
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by Gaël Marhic on 07/01/2019.
 */
class FormattedDateProvider @Inject constructor() {

    /**
     * Function used to get a formatted date from the timestamp passed in as a parameter,
     * depending on the date pattern also passed in as a parameter.
     *
     * @param timestampLong The timestamp to be formatted.
     * @param datePattern The date pattern to apply to the timestamp.
     * @return Returns the formatted date in case it goes well. Otherwise, returns a default
     * formatted date.
     */
    @SuppressLint("SimpleDateFormat")
    fun getFormattedDateFromTimestamp(timestampLong: Long, datePattern: String): String {

        val timestamp = Timestamp(timestampLong * 1000)
        val date = Date(timestamp.time)
        val englishLocale = Locale("en", "us")

        return try {
            val dateFormat = SimpleDateFormat(datePattern, englishLocale)
            dateFormat.format(date)
        } catch (e: Exception) {
            val calendar = Calendar.getInstance()
            calendar.time = date
            val month = calendar.get(Calendar.MONTH)
            val monthName = DateFormatSymbols(englishLocale).shortMonths[month]
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            "$dayOfMonth. $monthName" // Default format, in case there is an issue applying the pattern.
        }
    }
}
