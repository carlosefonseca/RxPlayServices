package xyz.fcampbell.rxgms.location

import android.content.Context
import com.google.android.gms.common.api.Api
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.ActivityRecognitionResult
import rx.Observable
import xyz.fcampbell.rxgms.activityrecognition.action.ActivityUpdates
import xyz.fcampbell.rxgms.common.ApiClientDescriptor
import xyz.fcampbell.rxgms.common.ApiDescriptor
import xyz.fcampbell.rxgms.common.RxGmsApi

@Suppress("unused")
class RxActivityRecognitionApi(
        apiClientDescriptor: ApiClientDescriptor
) : RxGmsApi<Unit, Api.ApiOptions.NoOptions>(
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
        return fromEmitterLatest { ActivityUpdates(it, detectionIntervalMilliseconds) }
    }
}