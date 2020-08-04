package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size.Resize
import com.sorrowblue.twitlin.objects.Entities.URL.Unwound
import com.sorrowblue.twitlin.serializers.DateTimeTzSerializer
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The [TwitterTweet.entities] and [TwitterTweet.extendedEntities] sections are both made up of arrays of entity objects.
 * Below you will find descriptions for each of these entity objects, including data dictionaries that describe the object attribute names,
 * types, and short description. We’ll also indicate which PowerTrack Operators match these attributes,
 * and include some sample JSON payloads.
 *
 * A collection of common entities found in Tweets, including hashtags, links, and user mentions.
 * This [TwitterTweet.entities] object does include a [media] attribute,
 * but its implementation in the [TwitterTweet.entities] section is only completely accurate for Tweets with a single photo.
 * For all Tweets with more than one photo, a video, or animated GIF, the reader is directed to the [TwitterTweet.extendedEntities] section.
 *
 * @property hashtags Represents hashtags which have been parsed out of the Tweet text.
 * @property media Represents media elements uploaded with the Tweet.
 * @property urls Represents URLs included in the text of a Tweet.
 * @property userMentions Represents other Twitter users mentioned in the text of the Tweet.
 * @property symbols Represents symbols, i.e. $cashtags, included in the text of the Tweet.
 * @property polls Represents Twitter Polls included in the Tweet.
 */
@Parcelize
@Serializable
data class Entities(
	val hashtags: List<Hashtag> = emptyList(),
	val media: List<Media> = emptyList(),
	val urls: List<URL> = emptyList(),
	@SerialName("user_mentions")
	val userMentions: List<UserMention> = emptyList(),
	val symbols: List<Symbol> = emptyList(),
	val polls: List<Poll> = emptyList()

) : Parcelable {

	/**
	 * The [TwitterTweet.entities] section will contain a [Entities.hashtags] array containing an object for every
	 * hashtag included in the Tweet body, and include an empty array if no hashtags are present.
	 * The PowerTrack `#` Operator is used to match on the [text] attribute. The `has:hashtags` Operator will match
	 * if there is at least one item in the array.
	 *
	 * @property indices An array of integers indicating the offsets within the Tweet text where the hashtag begins
	 * and ends. The first integer represents the location of the # character in the Tweet text string. The second
	 * integer represents the location of the first character after the hashtag. Therefore the difference between
	 * the two numbers will be the length of the hashtag name plus one (for the ‘#’ character).
	 * @property text Name of the hashtag, minus the leading ‘#’ character.
	 */
	@Parcelize
	@Serializable
	data class Hashtag(
		val indices: List<Int>,
		val text: String
	) : Parcelable

	/**
	 * The [TwitterTweet.entities] section will contain a [Entities.media] array containing a single media object
	 * if any media object has been ‘attached’ to the Tweet. If no native media has been attached, there will be
	 * no [Entities.media] array in the entities. For the following reasons the [TwitterTweet.extendedEntities] section
	 * should be used to process Tweet native media:
	 * <ul>
	 * <li>Media [type] will always indicate ‘photo’ even in cases of a video and GIF being attached to Tweet.</li>
	 * <li>Even though up to four photos can be attached, only the first one will be listed in the [TwitterTweet.entities] section.</li>
	 * </ul>
	 * The `has:media` Operator will match if this array is populated.
	 *
	 * @property displayUrl  URL of the media to display to clients.
	 * @property expandedUrl An expanded version of display_url. Links to the media display page.
	 * @property id          ID of the media expressed as a 64-bit integer.
	 * @property idStr ID of the media expressed as a string.
	 * @property indices An array of integers indicating the offsets within the Tweet text where the URL begins and ends.
	 * The first integer represents the location of the first character of the URL in the Tweet text.
	 * The second integer represents the location of the first non-URL character occurring after the URL
	 * (or the end of the string if the URL is the last part of the Tweet text).
	 * @property mediaUrl An `http://` URL pointing directly to the uploaded media file.
	 *
	 * For media in direct messages, [mediaUrl] is the same https URL as [mediaUrlHttps] and must be accessed by signing
	 * a request with the user’s access token using OAuth 1.0A.
	 * It is not possible to access images via an authenticated twitter.com session. Please visit
	 * [this page](https://developer.twitter.com/en/docs/direct-messages/message-attachments/guides/retrieving-media) to
	 * learn how to account for these recent change. You cannot directly embed these images in a web page.
	 * [See Photo Media URL formatting](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/entities-object#photo_format)
	 * for how to format a photo's URL, such as [mediaUrlHttps], based on the available [sizes].
	 * @property mediaUrlHttps An `https://` URL pointing directly to the uploaded media file, for embedding on https pages.
	 *
	 * For media in direct messages, [mediaUrlHttps] must be accessed by signing a request with the user’s access token
	 * using OAuth 1.0A. It is not possible to access images via an authenticated twitter.com session. Please visit
	 * [this page](https://developer.twitter.com/en/docs/direct-messages/message-attachments/guides/retrieving-media) to
	 * learn how to account for these recent change. You cannot directly embed these images in a web page.
	 * [See Photo Media URL formatting](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/entities-object#photo_format)
	 * for how to format a photo's URL, such as [mediaUrlHttps], based on the available [sizes].
	 * @property sizes An object showing available sizes for the media file.
	 *
	 * [See Photo Media URL formatting](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/entities-object#photo_format)
	 * for how to format a photo's URL, such as [mediaUrlHttps], based on the available [sizes].
	 * @property sourceStatusId Nullable. For Tweets containing media that was originally associated with
	 * a different tweet, this ID points to the original Tweet.
	 * @property sourceStatusIdStr Nullable. For Tweets containing media that was originally associated with
	 * a different tweet, this string-based ID points to the original Tweet.
	 * @property type Type of uploaded media. Possible types include photo, video, and animated_gif.
	 * @property url Wrapped URL for the media link. This corresponds with the URL embedded directly into
	 * the raw Tweet text, and the values for the [indices] parameter.
	 */
	@Parcelize
	@Serializable
	data class Media(
		@SerialName("display_url")
		val displayUrl: String,
		@SerialName("expanded_url")
		val expandedUrl: String,
		val id: Long,
		@SerialName("id_str")
		val idStr: String,
		val indices: List<Int> = emptyList(),
		@SerialName("media_url")
		val mediaUrl: String,
		@SerialName("media_url_https")
		val mediaUrlHttps: String,
		val sizes: MediaSize,
		@SerialName("source_status_id") val sourceStatusId: Long? = null,
		@SerialName("source_status_id_str") val sourceStatusIdStr: String? = null,
		val type: MediaType,
		val url: String,
		@SerialName("video_info")
		val videoInfo: VideoInfo? = null,
		val additionalMediaInfo: AdditionalMediaInfo? = null
	) : Parcelable {

		@Serializable
		enum class MediaType {
			@SerialName("photo")
			PHOTO,
			@SerialName("video")
			VIDEO,
			@SerialName("animated_gif")
			ANIMATED_GIF
		}

		/**
		 * All Tweets with native media (photos, video, and GIFs) will include a set of [thumb], [small], [medium],
		 * and [large] sizes with height and width pixel sizes. For photos and preview image media URLs,
		 * [Photo Media URL formatting](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/entities-object#photo_format)
		 * specifies how to construct different URLs for loading different sized photo media.
		 *
		 * @property thumb Information for a thumbnail-sized version of the media.
		 * @property large Information for a large-sized version of the media.
		 * @property medium Information for a medium-sized version of the media.
		 * @property small Information for a small-sized version of the media.
		 */
		@Parcelize
		@Serializable
		data class MediaSize(
			val thumb: Size,
			val large: Size,
			val medium: Size,
			val small: Size
		) : Parcelable {
			/**
			 * Size object
			 *
			 * @property w Width in pixels of this size.
			 * @property h Height in pixels of this size.
			 * @property resize Resizing method used to obtain this size. A value of [Resize.FIT] means that
			 * the media was resized to fit one dimension, keeping its native aspect ratio.
			 * A value of [Resize.CROP] means that the media was cropped in order to fit a specific resolution.
			 */
			@Parcelize
			@Serializable
			data class Size(
				val w: Int,
				val h: Int,
				val resize: Resize
			) : Parcelable {
				/**
				 * Resizing method used to obtain this size.
				 */
				@Parcelize
				@Serializable
				enum class Resize : Parcelable {
					/**
					 * the media was resized to fit one dimension
					 */
					@SerialName("fit")
					FIT,

					/**
					 * the media was cropped in order to fit a specific resolution
					 */
					@SerialName("crop")
					CROP
				}
			}
		}

		@Parcelize
		@Serializable
		data class VideoInfo(
			@SerialName("aspect_ratio")
			val aspectRatio: List<Int>,
			@SerialName("duration_millis")
			val durationMillis: Int = 0,
			val variants: List<Variant>
		) : Parcelable {
			@Parcelize
			@Serializable
			data class Variant(
				@SerialName("content_type")
				val contentType: Type,
				val url: String,
				val bitrate: Int? = null
			) : Parcelable {

				@Serializable
				enum class Type {
					@SerialName("video/mp4")
					VIDEO_MP4,

					@SerialName("application/x-mpegURL")
					APPLICATION_X_MPEG_URL
				}
			}
		}

		@Parcelize
		@Serializable
		data class AdditionalMediaInfo(
			val monetizable: Boolean
		) : Parcelable
	}

	/**
	 * The [TwitterTweet.entities] section will contain a [Entities.urls] array containing an object for every link
	 * included in the Tweet body, and include an empty array if no links are present. The `has:links` Operator will
	 * match if there is at least one item in the array. The `url:` Operator is used to match on the [expandedUrl]
	 * attribute. If you are using the [Expanded URL enrichment](http://support.gnip.com/enrichments/enhanced_urls.html),
	 * the `url:` Operator is used to match on the [Unwound.url] (fully unwound URL) attribute. If you are using the
	 * [Expanded URL enrichment](http://support.gnip.com/enrichments/enhanced_urls.html), the `url_title:` and
	 * `url_decription:` Operators are used to match on the [Unwound.title] and [Unwound.description] attributes.
	 *
	 * @property displayUrl URL pasted/typed into Tweet.
	 * @property expandedUrl Expanded version of `display_url` .
	 * @property indices An array of integers representing offsets within the Tweet text where the URL begins and ends.
	 * The first integer represents the location of the first character of the URL in the Tweet text.
	 * The second integer represents the location of the first non-URL character after the end of the URL.
	 * @property url Wrapped URL, corresponding to the value embedded directly into the raw Tweet text,
	 * and the values for the indices parameter.
	 * @property unwound If you are using the Expanded and/or Enhanced URL enrichments,
	 * the following metadata is available under the [unwound] attribute:
	 */
	@Parcelize
	@Serializable
	data class URL(
		@SerialName("display_url") val displayUrl: String,
		@SerialName("expanded_url") val expandedUrl: String,
		val indices: List<Int>,
		val url: String,
		val unwound: Unwound? = null
	) : Parcelable {

		/**
		 * If you are using the Expanded and/or Enhanced URL enrichments,
		 * the following metadata is available under the [unwound] attribute:
		 *
		 * @property url The fully unwound version of the link included in the Tweet.
		 * @property status Final HTTP status of the unwinding process, a '200' indicating success.
		 * @property title HTML title for the link.
		 * @property description HTML description for the link.
		 */
		@Parcelize
		@Serializable
		data class Unwound(
			val url: String,
			val status: Int,
			val title: String,
			val description: String
		) : Parcelable
	}

	/**
	 * The [TwitterTweet.entities] section will contain a [userMentions] array containing an object for every user
	 * mention included in the Tweet body, and include an empty array if no user mention is present.
	 * The PowerTrack `@` Operator is used to match on the [screenName] attribute. The `has:mentions` Operator will
	 * match if there is at least one item in the array.
	 *
	 * @property id ID of the mentioned user, as an integer.
	 * @property idStr If of the mentioned user, as a string.
	 * @property indices An array of integers representing the offsets within the Tweet text where the user reference
	 * begins and ends. The first integer represents the location of the ‘@’ character of the user mention. The second
	 * integer represents the location of the first non-screenname character following the user mention.
	 * @property name Display name of the referenced user.
	 * @property screenName Screen name of the referenced user.
	 */
	@Parcelize
	@Serializable
	data class UserMention(
		val id: Long,
		@SerialName("id_str")
		val idStr: String,
		val indices: List<Int>,
		val name: String,
		@SerialName("screen_name")
		val screenName: String
	) : Parcelable

	/**
	 * The [TwitterTweet.entities] section will contain a [symbols] array containing an object for every $cashtag
	 * included in the Tweet body, and include an empty array if no symbol is present. The PowerTrack `$` Operator is
	 * used to match on the [text] attribute. The `has:symbols` Operator will match if there is at least one item in the array.
	 *
	 * @property indices An array of integers indicating the offsets within the Tweet text where the symbol/cashtag
	 * begins and ends. The first integer represents the location of the $ character in the Tweet text string.
	 * The second integer represents the location of the first character after the cashtag. Therefore the difference
	 * between the two numbers will be the length of the hashtag name plus one (for the ‘$’ character).
	 * @property text Name of the cashhtag, minus the leading ‘$’ character.
	 */
	@Parcelize
	@Serializable
	data class Symbol(
		val indices: List<Int>,
		val text: String
	) : Parcelable

	/**
	 * The [TwitterTweet.entities] section will contain a [polls] array containing a single [Poll] object if the Tweet
	 * contains a poll. If no poll is included, there will be no [polls] array in the entities section.
	 *
	 * Note that these Poll metadata are only available with the following Enterprise APIs:
	 * <ul>
	 * <li>Volume streams ([Decahose](https://developer.twitter.com/en/docs/tweets/sample-realtime/overview/decahose))</li>
	 * <li>[Real-time PowerTrack](https://developer.twitter.com/en/docs/tweets/filter-realtime/overview/powertrack-api)</li>
	 * <li>[Historical PowerTrack](https://developer.twitter.com/en/docs/tweets/batch-historical/overview)</li>
	 * <li>Twitter Search APIs ([Full-Archive Search](https://developer.twitter.com/en/docs/tweets/search/overview/full-archive-search)
	 * and [30-Day Search](https://developer.twitter.com/en/docs/tweets/search/overview/30-day-search)</li>
	 *
	 * @property options An array of options, each having a poll position, and the text for that position.
	 * @property endDatetime Time stamp (UTC) of when poll ends.
	 * @property durationMinutes Duration of poll in minutes.
	 */
	@Parcelize
	@Serializable
	data class Poll(
		val options: List<Option> = emptyList(),
		@Serializable(DateTimeTzSerializer::class)
		@SerialName("end_datetime")
		val endDatetime: DateTimeTz = DateTime.EPOCH.local,
		@SerialName("duration_minutes")
		val durationMinutes: Int = -1
	) : Parcelable {

		/**
		 * An array of options, each having a poll position, and the text for that position.
		 *
		 * @property position 投票位置
		 * @property text 投票位置のテキスト
		 */
		@Parcelize
		@Serializable
		data class Option(val position: Int = -1, val text: String = "") : Parcelable
	}
}
