package com.sorrowblue.twitlin

import android.os.Parcelable

actual typealias AndroidParcelize = kotlinx.android.parcel.Parcelize

actual interface AndroidParcel: Parcelable
