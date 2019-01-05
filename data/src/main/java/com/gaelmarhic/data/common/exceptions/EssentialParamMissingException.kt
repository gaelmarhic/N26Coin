package com.gaelmarhic.data.common.exceptions

import java.lang.RuntimeException

/**
 * Created by Gaël Marhic on 05/01/2019.
 *
 * Exception thrown when an essential parameter is missing in the backend/network response.
 * THANK YOU GUYS FOR THE INSPIRATION :)
 */
class EssentialParamMissingException(missingParams: String,
                                     rawObject: Any):
        RuntimeException("The params: $missingParams are missing in received object: $rawObject")
