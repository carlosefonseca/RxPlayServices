package xyz.fcampbell.rxgms.location

import android.content.Context
import com.google.android.gms.location.places.*
import com.google.android.gms.maps.model.LatLngBounds
import rx.Single
import xyz.fcampbell.rxgms.common.RxGmsApi
import xyz.fcampbell.rxgms.common.util.flatMapSingle
import xyz.fcampbell.rxgms.location.action.location.*

/**
 * Reactive way to access Google Play Location APIs
 */
@Suppress("unused")
class RxPlacesApi internal constructor(
        context: Context
) : RxGmsApi(context, Places.GEO_DATA_API, Places.PLACE_DETECTION_API) {
    /**
     * Returns observable that fetches current place from Places API. To flatmap and auto release
     * buffer to [com.google.android.gms.location.places.PlaceLikelihood] observable use
     * [DataBufferObservable].

     * @param placeFilter filter
     * *
     * @return observable that emits current places buffer and completes
     */
    fun getCurrentPlace(placeFilter: PlaceFilter?): Single<PlaceLikelihoodBuffer> {
        return rxApiClient.flatMapSingle { Single.create(GetCurrentPlace(it, placeFilter)) }
    }

    /**
     * Returns observable that fetches a place from the Places API using the place ID.

     * @param placeId id for place
     * *
     * @return observable that emits places buffer and completes
     */
    fun getPlaceById(vararg placeIds: String): Single<PlaceBuffer> {
        return rxApiClient.flatMapSingle { Single.create(GetPlaceById(it, *placeIds)) }
    }

    /**
     * Returns observable that fetches autocomplete predictions from Places API. To flatmap and autorelease
     * [com.google.android.gms.location.places.AutocompletePredictionBuffer] you can use
     * [DataBufferObservable].

     * @param query  search query
     * *
     * @param bounds bounds where to fetch suggestions from
     * *
     * @param filter filter
     * *
     * @return observable with suggestions buffer and completes
     */
    fun getPlaceAutocompletePredictions(query: String, bounds: LatLngBounds, filter: AutocompleteFilter?): Single<AutocompletePredictionBuffer> {
        return rxApiClient.flatMapSingle { Single.create(GetAutocompletePredictions(it, query, bounds, filter)) }
    }

    /**
     * Returns observable that fetches photo metadata from the Places API using the place ID.

     * @param placeId id for place
     * *
     * @return observable that emits metadata buffer and completes
     */
    fun getPlacePhotos(placeId: String): Single<PlacePhotoMetadataResult> {
        return rxApiClient.flatMapSingle { Single.create(GetPlacePhotos(it, placeId)) }
    }

    /**
     * Returns observable that fetches a placePhotoMetadata from the Places API using the place placePhotoMetadata metadata.
     * Use after fetching the place placePhotoMetadata metadata with [RxGms.getPhotoMetadataById]

     * @param placePhotoMetadata the place photo meta data
     * *
     * @return observable that emits the photo result and completes
     */
    fun getPlacePhoto(placePhotoMetadata: PlacePhotoMetadata): Single<PlacePhotoResult> {
        return rxApiClient.flatMapSingle { Single.create(GetPlacePhoto(it, placePhotoMetadata)) }
    }
}