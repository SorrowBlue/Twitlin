package com.sorrowblue.twitlin.api.v2.tweets.search

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.objects.UUID
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

/**
 * Rule id
 *
 * @property id
 * @constructor Create empty Rule id
 */
@AndroidParcelize
@Serializable
@JvmInline
public value class RuleId(override val id: String) : UUID, AndroidParcelable
