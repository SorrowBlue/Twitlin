/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.annotation

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
public expect annotation class AndroidParcelize()

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.BINARY)
public expect annotation class AndroidWriteWith<P : KotlinParceler<*>>()

public expect interface KotlinParceler<T>

public expect interface AndroidParcelable

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
public expect annotation class KotlinIgnoredOnParcel()
