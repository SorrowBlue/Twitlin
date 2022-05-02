package com.sorrowblue.twitlin.core.annotation

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
public expect annotation class AndroidParcelize()
