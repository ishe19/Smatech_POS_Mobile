package tech.ishe.smatechpos.views.utils

import android.content.res.Configuration

object ScreenDimensions {
    var screenHeightDp: Int = 0
    var screenWidthDp: Int = 0

    fun initialize(configuration: Configuration) {
        screenHeightDp = configuration.screenHeightDp
        screenWidthDp = configuration.screenWidthDp
    }
}
