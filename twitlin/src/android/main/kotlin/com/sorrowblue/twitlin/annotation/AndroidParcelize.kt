/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.annotation

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.WriteWith

public actual typealias AndroidParcelize = Parcelize

public actual typealias KotlinIgnoredOnParcel = IgnoredOnParcel

public actual interface AndroidParcelable : Parcelable
