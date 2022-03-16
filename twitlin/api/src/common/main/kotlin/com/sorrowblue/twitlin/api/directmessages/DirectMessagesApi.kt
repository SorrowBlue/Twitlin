package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.api.media.MediaResult
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.objects.MediaId

/**
 * ### Sending message events
 * To send a new Direct Message use [DirectMessagesApi.new] (message_create). Optionally, you may
 * also attach Quick Replies or media (image, GIF or video).
 *
 * ### Receiving messages events
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
     * @return
     */
    public suspend fun new(
        recipientId: String,
        text: String,
        quickReply: QuickReply? = null,
        ctas: List<CallToAction>? = null,
        mediaId: MediaId? = null
    ): Response<DirectMessage>

    /**
     * Returns all Direct Message events (both sent and received) within the last 30 days. Sorted in
     * reverse-chronological order.
     *
     * @param count Max number of events to be returned. 20 default. 50 max.
     * @param cursor For paging through result sets greater than 1 page, use the
     * [PagingDirectMessage.nextCursor] property from the previous request.
     * @return
     */
    public suspend fun list(
        count: Int? = null,
        cursor: String? = null
    ): Response<PagingDirectMessage>

    /**
     * Returns a single Direct Message event by the given id.
     *
     * @param id The id of the Direct Message event that should be returned.
     * @return
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
     * @return
     */
    public suspend fun destroy(id: String): Response<DirectMessage>

    /**
     * Displays a visual typing indicator in the recipient’s Direct Message conversation view with
     * the sender. Each request triggers a typing indicator animation with a duration of ~3 seconds.
     * ### Usage
     * A rudimentary approach would be to invoke an API request on every keypress or input event,
     * however the application may quickly hit rate limits. A more sophisticated approach is to
     * capture input events, but limit API requests to a specified interval based on the behavior
     * of your users and the rate limit specified below.
     *
     * @param receiveId The user ID of the user to receive the typing indicator.
     * @return
     */
    public suspend fun indicateTyping(receiveId: String): Response<Unit>

    /**
     * Marks a message as read in the recipient’s Direct Message conversation view with the sender.
     *
     * @param lastReadEventId The message ID of the most recent message to be marked read.
     * All messages before it will be marked read as well.
     * @param recipientId The user ID of the user the message is from.
     * @return
     */
    public suspend fun markRead(lastReadEventId: String, recipientId: String): Response<Unit>
}
