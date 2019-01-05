package com.gaelmarhic.data.features.bitcoin.mappers

import com.gaelmarhic.data.common.exceptions.EssentialParamMissingException
import com.gaelmarhic.data.common.constants.Constants.Companion.BLOCKCHAIN_API_OK_STATUS
import com.gaelmarhic.data.features.bitcoin.entities.BitcoinMarketPrice
import com.gaelmarhic.data.features.bitcoin.entities.BitcoinMarketPriceInformation
import com.gaelmarhic.data.features.bitcoin.entities.BitcoinMarketPriceInformationRaw
import com.gaelmarhic.data.features.bitcoin.entities.BitcoinMarketPriceRaw
import io.reactivex.functions.Function

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
class BitcoinMarketPriceInformationMapper:
        Function<BitcoinMarketPriceInformationRaw, BitcoinMarketPriceInformation> {

    companion object {

        const val statusParam = "status"
        const val nameParam = "name"
        const val unitParam = "unit"
        const val periodParam = "period"
        const val descriptionParam = "description"
        const val valuesParam = "values"
    }

    override fun apply(raw: BitcoinMarketPriceInformationRaw) = assertEssentialParams(raw)

    /**
     * Function used to check the essential parameters and to instantiate the mapped object in case
     * that everything went ok.
     *
     * @param raw The raw entity to be checked and parsed.
     * @return Returns a mapped and checked [BitcoinMarketPriceInformation] in case that the
     * assertions went well.
     */
    @Suppress("UNCHECKED_CAST")
    private fun assertEssentialParams(raw: BitcoinMarketPriceInformationRaw): BitcoinMarketPriceInformation {

        val checkedParamsMap = mutableMapOf<String, Any>()
        val missingParamsList = mutableListOf<String>()

        with(raw) {

            // Status
            if (status != null && status.equals(BLOCKCHAIN_API_OK_STATUS, true)) {
                checkedParamsMap[statusParam] = status
            } else {
                missingParamsList.add(statusParam)
            }

            // Name
            if (name != null && name.isNotEmpty()) {
                checkedParamsMap[nameParam] = name
            } else {
                missingParamsList.add(nameParam)
            }

            // Unit
            if (unit != null && unit.isNotEmpty()) {
                checkedParamsMap[unitParam] = unit
            } else {
                missingParamsList.add(unitParam)
            }

            // Period
            if (period != null && period.isNotEmpty()) {
                checkedParamsMap[periodParam] = period
            } else {
                missingParamsList.add(periodParam)
            }

            // Description
            if (description != null && description.isNotEmpty()) {
                checkedParamsMap[descriptionParam] = description
            } else {
                missingParamsList.add(descriptionParam)
            }

            // Values list
            val filteredValueList = values.mapAndFilter()
            if (filteredValueList.isNotEmpty()) {
                checkedParamsMap[valuesParam] = filteredValueList
            } else {
                missingParamsList.add(valuesParam)
            }

            // We check whether everything went ok, and we take the necessary action.
            if (missingParamsList.isNotEmpty()) {
                throw EssentialParamMissingException(missingParamsList.toString(), raw)
            } else {
                return BitcoinMarketPriceInformation(
                        status = checkedParamsMap[statusParam] as String,
                        name = checkedParamsMap[nameParam] as String,
                        unit = checkedParamsMap[unitParam] as String,
                        period = checkedParamsMap[periodParam] as String,
                        description = checkedParamsMap[descriptionParam] as String,
                        values = checkedParamsMap[valuesParam] as List<BitcoinMarketPrice>)
            }
        }
    }

    /**
     * Function that will map a list of nullable [BitcoinMarketPriceRaw] to a safe list of
     * [BitcoinMarketPrice].
     * IMPORTANT: For the sake of this sample app, we will just get rid of any entry that has
     * parameter issues. In a real production app, that behaviour would probably be different.
     *
     * @return Returns the safe list of [BitcoinMarketPrice].
     */
    private fun List<BitcoinMarketPriceRaw?>?.mapAndFilter(): List<BitcoinMarketPrice> =
            this?.mapNotNull {
                if (it?.timestamp != null && it.amount != null) {
                    BitcoinMarketPrice(it.timestamp, it.amount)
                } else { null }
            } ?: emptyList()
}
