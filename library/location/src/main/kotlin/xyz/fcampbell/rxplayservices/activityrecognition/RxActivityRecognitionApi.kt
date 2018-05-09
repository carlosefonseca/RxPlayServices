package xyz.fcampbell.rxplayservices.activityrecognition

import android.content.Context
import com.google.android.gms.common.api.Api
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.ActivityRecognitionResult
import io.reactivex.Observable
import xyz.fcampbell.rxplayservices.activityrecognition.action.ActivityUpdates
import xyz.fcampbell.rxplayservices.base.ApiClientDescriptor
import xyz.fcampbell.rxplayservices.base.ApiDescriptor
import xyz.fcampbell.rxplayservices.base.RxPlayServicesApi

/**
 * Wraps [ActivityRecognition.ActivityRecognitionApi]
 */
@Suppress("unused")
class RxActivityRecognitionApi(
        apiClientDescriptor: ApiClientDescriptor
) : RxPlayServicesApi<Unit, Api.ApiOptions.NoOptions>(
        apiClientDescriptor,
        ApiDescriptor(ActivityRecognition.API, Unit)
) {
    constructor(
            context: Context
    ) : this(ApiClientDescriptor(context))

    /**
     * Observable that can be used to observe activity provided by Actity Recognition mechanism.

     * @param detectionIntervalMilliseconds detection interval
     * *
     * @return observable that provides activity recognition
     */
    fun requestActivityUpdates(detectionIntervalMilliseconds: Int): Observable<ActivityRecognitionResult> {
        return create { ActivityUpdates(it, detectionIntervalMilliseconds) }
    }
}
