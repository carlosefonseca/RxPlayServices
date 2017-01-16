package xyz.fcampbell.rxgms.drive

import android.content.Context
import com.google.android.gms.common.api.Api
import com.google.android.gms.common.api.Scope
import com.google.android.gms.common.api.Status
import com.google.android.gms.drive.CreateFileActivityBuilder
import com.google.android.gms.drive.Drive
import com.google.android.gms.drive.DriveApi.MetadataBufferResult
import com.google.android.gms.drive.OpenFileActivityBuilder
import com.google.android.gms.drive.query.Query
import rx.Observable
import xyz.fcampbell.rxgms.common.ApiClientDescriptor
import xyz.fcampbell.rxgms.common.ApiDescriptor
import xyz.fcampbell.rxgms.common.RxGmsApi
import xyz.fcampbell.rxgms.common.util.toObservable

/**
 * Created by francois on 2016-12-22.
 */
@Suppress("unused")
class RxDriveApi(
        apiClientDescriptor: ApiClientDescriptor,
        vararg scopes: Scope
) : RxGmsApi<Api.ApiOptions.NoOptions>(
        apiClientDescriptor,
        ApiDescriptor(Drive.API, null, *scopes)
) {
    constructor(
            context: Context,
            vararg scopes: Scope
    ) : this(ApiClientDescriptor(context), *scopes)

    fun fetchDriveId(resourceId: String): Observable<RxDriveId> {
        return flatMap { googleApiClient ->
            Drive.DriveApi.fetchDriveId(googleApiClient, resourceId)
                    .toObservable()
                    .map { RxDriveId(googleApiClient, it.driveId) }
        }
    }

    fun getAppFolder(): Observable<RxDriveFolder> {
        return map { RxDriveFolder(it, Drive.DriveApi.getAppFolder(it)) }
    }

    fun getRootFolder(): Observable<RxDriveFolder> {
        return map { RxDriveFolder(it, Drive.DriveApi.getRootFolder(it)) }
    }

    fun newCreateFileActivityBuilder(): Observable<CreateFileActivityBuilder> {
        return just(Drive.DriveApi.newCreateFileActivityBuilder())
    }

    fun newDriveContents(): Observable<RxDriveContents> {
        return flatMap { googleApiClient ->
            Drive.DriveApi.newDriveContents(googleApiClient)
                    .toObservable()
                    .map { RxDriveContents(googleApiClient, it.driveContents) }
        }
    }

    fun newOpenFileActivityBuilder(): Observable<OpenFileActivityBuilder> {
        return just(Drive.DriveApi.newOpenFileActivityBuilder())
    }

    fun query(query: Query): Observable<MetadataBufferResult> {
        return fromPendingResult { Drive.DriveApi.query(it, query) }
    }

    fun requestSync(): Observable<Status> {
        return fromPendingResult { Drive.DriveApi.requestSync(it) }
    }
}