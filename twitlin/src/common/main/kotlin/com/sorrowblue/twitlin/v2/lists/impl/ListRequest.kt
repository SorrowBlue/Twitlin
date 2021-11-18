package com.sorrowblue.twitlin.v2.lists.impl

import kotlinx.serialization.Serializable

@Serializable
internal data class ListRequest(val name: String?, val description: String?, val private: Boolean?)
