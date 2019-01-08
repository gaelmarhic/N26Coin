package com.gaelmarhic.presentation.common.streams

/**
 * Created by Gaël Marhic on 08/01/2019.
 */
sealed class StreamState {
    object Getting: StreamState()
    object Failed: StreamState()
    class Retrieved(val content: Any): StreamState()
}
