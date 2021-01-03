/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.media.MediaResult

/**
 * ## Sending message events
 * To send a new Direct Message use [DirectMessagesApi.new] (message_create). Optionally, you may
 * also attach Quick Replies or media (image, GIF or video).
 *
 * ## Receiving messages events
 * * You can retrieve Direct Messages from up to the past `30` days with [DirectMessagesApi.list].
 * * Consuming Direct Messages in real-time can be accomplished via webhooks with the
 * `Account Activity API`.
 */
public interface DirectMessagesApi {

    /**
     * Publishes a new `message_create` event resulting in a Direct Message sent to a specified user
     * from the authenticating user. Returns an event if successful. Supports publishing Direct
     * Messages with optional Quick Reply and media attachment.
     *
     * @param recipientId The ID of the user who should receive the direct message.
     * @param text The text of your Direct Message. URL encode as necessary. Max length of `10,000`
     * characters. Max length of `9,990` characters if used as a
     * [Welcome Message](https://developer.twitter.com/en/docs/direct-messages/welcome-messages/api-reference/new-welcome-message).
     * @param quickReply The Quick Reply to present to the user.
     * @param mediaId A media id to associate with the message. A Direct Message may only reference
     * a single [MediaResult.mediaId]. See
     * [Uploading Media](https://developer.twitter.com/en/docs/direct-messages/message-attachments/guides/attaching-media)
     * for further details on uploading media.
     * @return TODO
     */
    public suspend fun new(
        recipientId: String,
        text: String,
        quickReply: QuickReply? = null,
        mediaId: String? = null
    ): Response<DirectMessage>

    /**
     * Returns all Direct Message events (both sent and received) within the last 30 days. Sorted in
     * reverse-chronological order.
     *
     * @param count Max number of events to be returned. 20 default. 50 max.
     * @param cursor For paging through result sets greater than 1 page, use the
     * [PagingDirectMessage.nextCursor] property from the previous request.
     * @return TODO
     */
    public suspend fun list(
        count: Int? = null,
        cursor: String? = null
    ): Response<PagingDirectMessage>

    /**
     * Returns a single Direct Message event by the given id.
     *
     * @param id The id of the Direct Message event that should be returned.
     * @return TODO
     */
    public suspend fun show(id: String): Response<DirectMessage>

    /**
     * Deletes the direct message specified in the required ID parameter. The authenticating user
     * must be the recipient of the specified direct message. Direct Messages are only removed from
     * the interface of the user context provided. Other members of the conversation can still
     * access the Direct Messages. A successful delete will return a 204 http response code with no
     * body content.
     *
     * ***Important***: This method requires an access token with RWD (read, write & direct message)
     * permissions.
     *
     * @param id The id of the Direct Message event that should be deleted.
     * @return TODO
     */
    public suspend fun destroy(id: String): Response<DirectMessage>
}
