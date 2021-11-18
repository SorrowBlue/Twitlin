package com.sorrowblue.twitlin.annotation

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

public actual typealias AndroidParcelize = Parcelize

public actual typealias KotlinIgnoredOnParcel = IgnoredOnParcel

public actual interface AndroidParcelable : Parcelable
