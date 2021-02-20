/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.annotation

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlinx.parcelize.WriteWith

public actual typealias AndroidParcelize = Parcelize

public typealias AndroidWriteWith<T> = WriteWith<KotlinParceler<T>>

public actual typealias KotlinIgnoredOnParcel = IgnoredOnParcel

public actual interface KotlinParceler<T> : Parceler<T>

public actual interface AndroidParcelable : Parcelable

