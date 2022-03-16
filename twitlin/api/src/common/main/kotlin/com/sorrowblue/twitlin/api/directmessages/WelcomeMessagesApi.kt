package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.api.media.MediaResult
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.objects.MediaId

/**
 * Welcome Messages provide the ability to display a message to people who are entering a Direct
 * Message conversation. Welcome messages can be customized for different referral paths. For
 * example, users who click on Direct Message links in a Tweet or if a user enters a Direct Message
 * view for the first time with no prior context. Welcome Messages can contain any content that a
 * Direct Message would, including media, Quick Replies, and more.
 *
 * Welcome Messages can be presented to users in two ways:
 * * Deeplinking to a Welcome Message >
 *
 *     Create a URL that can be used anywhere to link a user to a specific message in the Direct
 *     Message view.
 * * Setting a Default Welcome Message >
 *
 *     Set the default message a user will see when they enter the Direct Message view.
 */
public interface WelcomeMessagesApi {

    /**
     * Deletes a Welcome Message by the given id.
     *
     * @param id The id of the Welcome Message that should be deleted.
     * @return
     */
    public suspend fun destroy(id: String): Response<Unit>

    /**
     * Deletes a Welcome Message Rule by the given id.
     *
     * @param id The id of the Welcome Message Rule that should be deleted.
     * @return
     */
    public suspend fun destroyRule(id: String): Response<Unit>

    /**
     * Returns a Welcome Message by the given id.
     *
     * @param id The id of the Welcome Message that should be returned.
     * @return
     */
    public suspend fun show(id: String): Response<WelcomeMessageData>

    /**
     * Returns a Welcome Message Rule by the given id.
     *
     * @param id The id of the Welcome Message Rule that should be returned.
     * @return
     */
    public suspend fun showRule(id: String): Response<WelcomeMessageRule>

    /**
     * Returns a list of Welcome Message Rules.
     *
     * @param count Number of welcome message rules to be returned. Max of `50`. Default is `20`.
     * @param cursor For paging through result sets greater than 1 page, use the “next_cursor” property from the previous request.
     * @return
     */
    public suspend fun listRule(
        count: Int = 20,
        cursor: String? = null
    ): Response<PagingWelcomeMessageRule>

    /**
     * Returns a list of Welcome Messages.
     *
     * @param count Number of welcome messages to be returned. Max of `50`. Default is `20`.
     * @param cursor For paging through result sets greater than 1 page, use the “next_cursor”
     * property from the previous request.
     * @return
     */
    public suspend fun list(count: Int = 20, cursor: String? = null): Response<PagingWelcomeMessage>

    /**
     * Creates a new Welcome Message that will be stored and sent in the future from the
     * authenticating user in defined circumstances. Returns the message template if successful.
     * Supports publishing with the same elements as Direct Messages (e.g. Quick Replies, media
     * attachments).
     *
     * See the [Welcome Messages overview](https://developer.twitter.com/en/docs/direct-messages/welcome-messages/overview)
     * to learn how to work with Welcome Messages.
     *
     * @param name A human readable name for the Welcome Message. This is not displayed to the user.
     * Max length of 100 alpha numeric characters including hyphens, underscores, spaces, hashes and
     * at signs.
     * @param text The text of your Direct Message. URL encode as necessary. Max length of `10,000`
     * characters. Max length of `9,990` characters if used as a Welcome Message.
     * @param mediaId  A media id to associate with the message. A Direct Message may only reference
     * a single [MediaResult.mediaId]. See
     * [Uploading Media](https://developer.twitter.com/en/docs/direct-messages/message-attachments/guides/attaching-media)
     * for further details on uploading media.
     * @param quickReply The Quick Reply type to present to the user.
     * @return
     */
    public suspend fun new(
        name: String,
        text: String,
        mediaId: MediaId? = null,
        quickReply: QuickReply? = null
    ): Response<WelcomeMessageData>

    /**
     * Creates a new Welcome Message Rule that determines which Welcome Message will be shown in a
     * given conversation. Returns the created rule if successful.
     *
     * Additional rule configurations are forthcoming. For the initial beta release, the most
     * recently created Rule will always take precedence, and the assigned Welcome Message will be
     * displayed in the conversation.
     *
     * See the [Welcome Messages overview](https://developer.twitter.com/en/docs/direct-messages/welcome-messages/overview)
     * to learn how to work with Welcome Messages.
     *
     * @param welcomeMessageId The ID of the Welcome Message that will be sent when this Rule is
     * triggered.
     * @return
     */
    public suspend fun newRule(welcomeMessageId: String): Response<WelcomeMessageRule>

    /**
     * Updates a Welcome Message by the given ID. Updates to the [WelcomeMessage] are atomic. For
     * example, if the Welcome Message currently has [MessageData.quick_reply] defined and you only
     * provde text, the [MessageData.quick_reply] object will be removed from the Welcome Message.
     *
     * @param id The id of the Welcome Message that should be updated.
     * @param text The text of your Direct Message. URL encode as necessary. Max length of `10,000`
     * characters. Max length of `9,990` characters if used as a Welcome Message.
     * @param mediaId  A media id to associate with the message. A Direct Message may only reference
     * a single [MediaResult.mediaId]. See
     * [Uploading Media](https://developer.twitter.com/en/docs/direct-messages/message-attachments/guides/attaching-media)
     * for further details on uploading media.
     * @param quickReply The Quick Reply type to present to the user.
     * @return
     */
    public suspend fun update(
        id: String,
        text: String,
        mediaId: MediaId? = null,
        quickReply: QuickReply? = null
    ): Response<WelcomeMessageData>
}
