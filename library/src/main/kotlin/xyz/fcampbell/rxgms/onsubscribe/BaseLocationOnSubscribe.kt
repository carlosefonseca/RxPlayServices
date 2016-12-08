package xyz.fcampbell.rxgms.onsubscribe

import android.content.Context

import com.google.android.gms.location.LocationServices

abstract class BaseLocationOnSubscribe<T> protected constructor(
        ctx: Context
) : BaseOnSubscribe<T>(ctx, LocationServices.API)