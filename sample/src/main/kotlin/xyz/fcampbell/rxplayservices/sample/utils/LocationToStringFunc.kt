package xyz.fcampbell.rxplayservices.sample.utils

import android.location.Location
import io.reactivex.functions.Function

object LocationToStringFunc : Function<Location, String> {
    override fun apply(location: Location): String {
        return "${location.latitude}-${location.longitude} (${location.accuracy})"
    }
}
