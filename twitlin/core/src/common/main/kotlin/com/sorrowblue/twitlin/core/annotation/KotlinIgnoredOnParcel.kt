package com.sorrowblue.twitlin.core.annotation

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
public expect annotation class KotlinIgnoredOnParcel()
