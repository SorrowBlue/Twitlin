/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.annotation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

public actual typealias AndroidParcelize = Parcelize

//public actual typealias KotlinTypeParceler2<T, P : KotlinParceler<T>> = TypeParceler<T, P>
//
//public actual interface KotlinParceler<T: Any> : Parceler<T>

public actual interface AndroidParcelable : Parcelable
