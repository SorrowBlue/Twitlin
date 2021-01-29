/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.annotation

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
public expect annotation class AndroidParcelize()

//@OptIn(ExperimentalMultiplatform::class)
//@OptionalExpectation
//@Repeatable
//@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
//@Retention(AnnotationRetention.SOURCE)
//public expect annotation class KotlinTypeParceler<T : Any, P : KotlinParceler<in T>>

//public expect interface KotlinParceler<T : Any>

public expect interface AndroidParcelable
