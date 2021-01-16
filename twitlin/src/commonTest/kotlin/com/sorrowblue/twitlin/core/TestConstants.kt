/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.core

internal const val CONSUMER_KEY = "xvz1evFS4wEEPTGEFPHBog"
internal const val NONCE = "kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg"
internal const val TIMESTAMP = "1318622958"
internal const val CONSUMER_SECRET = "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw"
internal const val OAUTH_TOKEN = "370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb"
internal const val OAUTH_TOKEN_SECRET = "LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE"
internal const val URL = "https://api.twitter.com/1.1/statuses/update.json"
internal const val METHOD = "POST"
internal const val SIGNATURE = "tnnArxj06cWHq44gCs1OSKk/jLY="

internal val PARAMETERS = listOf(
    "status" to "Hello Ladies + Gentlemen, a signed OAuth request!",
    "include_entities" to "true"
)
