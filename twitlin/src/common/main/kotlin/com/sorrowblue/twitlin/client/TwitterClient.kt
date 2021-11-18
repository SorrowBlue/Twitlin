package com.sorrowblue.twitlin.client

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.Parameters
import io.ktor.http.content.PartData
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.KSerializer

/**
 * Twitter client
 *
 * @constructor Create empty Twitter client
 */
public interface TwitterClient {

    /**
     * Get
     *
     * @param T
     * @param url
     * @param serializer
     * @param block
     * @receiver
     * @return
     */
    public suspend fun <T : TwitterResponse> get(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T

    /**
     * Post
     *
     * @param T
     * @param url
     * @param serializer
     * @param block
     * @receiver
     * @return
     */
    public suspend fun <T : TwitterResponse> post(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T

    /**
     * Submit form
     *
     * @param T
     * @param url
     * @param serializer
     * @param parameters
     * @param block
     * @receiver
     * @return
     */
    public suspend fun <T : TwitterResponse> submitForm(
        url: String,
        serializer: KSerializer<T>,
        parameters: Parameters,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T

    /**
     * Submit form with binary data
     *
     * @param T
     * @param url
     * @param serializer
     * @param formBuilder
     * @param block
     * @receiver
     * @return
     */
    public suspend fun <T : TwitterResponse> submitFormWithBinaryData(
        url: String,
        serializer: KSerializer<T>,
        formBuilder: List<PartData>,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T

    /**
     * Delete
     *
     * @param T
     * @param url
     * @param serializer
     * @param block
     * @receiver
     * @return
     */
    public suspend fun <T : TwitterResponse> delete(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T

    /**
     * Put
     *
     * @param T
     * @param url
     * @param serializer
     * @param block
     * @receiver
     * @return
     */
    public suspend fun <T : TwitterResponse> put(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T

    /**
     * Streaming
     *
     * @param T
     * @param url
     * @param serializer
     * @param block
     * @receiver
     * @return
     */
    public fun <T : TwitterResponse> streaming(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit = {}
    ): Flow<T>
}
