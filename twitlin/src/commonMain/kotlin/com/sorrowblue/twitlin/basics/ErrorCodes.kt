package com.sorrowblue.twitlin.basics

object ErrorCodes {
	// Corresponds with HTTP 400.
	// The coordinates provided as parameters were not valid for the request.
	const val INVALID_COORDINATES = 3

	// 	Corresponds with HTTP 404.
	// 	It was not possible to derive a location for the IP address provided as a parameter on the geo search request.
	const val NO_LOCATION_ASSOCIATED_WITH_IP_ADDRESS = 13

	// Corresponds with HTTP 404.
	// It was not possible to find a user profile matching the parameters specified.
	const val NO_USER_MATCHES_FOR_SPECOFIED_TERMS = 17

	// Corresponds with HTTP 401.
	// There was an issue with the authentication data for the request.
	const val COULD_NOT_AUTHENTICATE_YOU = 32

	// Corresponds with HTTP 404.
	// The specified resource was not found.
	const val THAT_PAGE_DOES_NOT_EXIST = 34

	// Corresponds with HTTP 403.
	// You cannot use your own user ID in a report spam call.
	const val YOU_CANNOT_REPORT_YOURSELF_FOR_SPAM = 36

	// Corresponds with HTTP 403.
	// The request is missing the <named> parameter (such as media, text, etc.) in the request.
	const val NAMED_PARAMETER_IS_MISSING38 = 38

	// Corresponds with HTTP 400.
	// The URL value provided is not a URL that can be attached to this Tweet.
	const val ATTACHMENT_URL_PARAMETER_IS_INVALID = 44

	// Corresponds with HTTP 404.
	// The user is not found.
	const val USER_NOT_FOUND = 50

	// Corresponds with HTTP 403.
	// The user account has been suspended and information cannot be retrieved.
	const val USER_HAS_BEEN_SUSPENDED = 63

	// Corresponds with HTTP 403.
	// The access token being used belongs to a suspended user.
	const val ACCOUNT_HAS_BEEN_SUSPENDED = 64

	// Corresponds with HTTP 410.
	// The request was made to a retired v1-era URL.
	const val SHOULD_MIGRATE_TO_API_V1_1 = 68

	// Corresponds with HTTP 403.
	// The endpoint called is not a permitted URL.
	const val NOT_ALLOWED_TO_PERFORM_ACTION = 87

	// Corresponds with HTTP 429.
	// The request limit for this resource has been reached for the current rate limit window.
	const val RATE_LIMIT_EXCEEDED = 88

	// Corresponds with HTTP 403.
	// The access token used in the request is incorrect or has expired.
	const val INVALID_OR_EXPIRED_TOKEN = 89

	// Corresponds with HTTP 403.
	// Only TLS v1.2 connections are allowed in the API.
	// Update the request to a secure connection.
	// See how to connect using TLS
	const val SSL_IS_REQUIRED = 92

	// Corresponds with HTTP 403.
	// The OAuth token does not provide access to Direct Messages.
	const val CAN_NOT_ACCESS_DIRECT_MESSAGES = 93

	// Corresponds with HTTP 403.
	// The OAuth credentials cannot be validated. Check that the token is still valid.
	const val UNABLE_TO_VERIFY_YOUR_CREDENTIALS = 99

	// Corresponds with HTTP 403.
	// Thrown when one of the values passed to the update_profile.json endpoint exceeds the maximum value currently permitted for that field.
	// The error message will specify the allowable maximum number of nn characters.
	const val ACCOUNT_UPDATE_FAILED = 120

	// Corresponds with HTTP 503.
	// Twitter is temporarily over capacity.
	const val OVER_CAPACITY = 130

	// Corresponds with HTTP 500.
	// An unknown internal error occurred.
	const val INTERNAL_ERROR = 131

	// Corresponds with HTTP 401.
	// Timestamp out of bounds (often caused by a clock drift when authenticating - check your system clock)
	const val COULD_NOT_AUTHENTICATE_YOU_TIMESTAMP = 135

	/*
	139    You have already favorited this status.    Corresponds with HTTP 403. A Tweet cannot be favorited (liked) more than once.
	144    No status found with that ID.    Corresponds with HTTP 404. The requested Tweet ID is not found (if it existed, it was probably deleted)
	150    You cannot send messages to users who are not following you.    Corresponds with HTTP 403. Sending a Direct Message failed.
	151    There was an error sending your message: reason    Corresponds with HTTP 403. Sending a Direct Message failed. The reason value will provide more information.
	160    You've already requested to follow user.
	Corresponds with HTTP 403. This was a duplicated follow request and a previous request was not yet acknowleged.
	161    You are unable to follow more people at this time    Corresponds with HTTP 403. Thrown when a user cannot follow another user due to reaching the limit. This limit is applied to each user individually, independent of the applications they use to access the Twitter platform.
	179    Sorry, you are not authorized to see this status    Corresponds with HTTP 403. Thrown when a Tweet cannot be viewed by the authenticating user, usually due to the Tweet’s author having protected their Tweets.
	185    User is over daily status update limit    Corresponds with HTTP 403. Thrown when a Tweet cannot be posted due to the user having no allowance remaining to post. Despite the text in the error message indicating that this error is only thrown when a daily limit is reached, this error will be thrown whenever a posting limitation has been reached. Posting allowances have roaming windows of time of unspecified duration.
	186    Tweet needs to be a bit shorter.    Corresponds with HTTP 403. The status text is too long.
	187    Status is a duplicate    Corresponds with HTTP 403. The status text has already been Tweeted by the authenticated account.
	195    Missing or invalid url parameter    Corresponds with HTTP 403.  The request needs to have a valid url parameter.
	205    You are over the limit for spam reports.    Corresponds with HTTP 403. The account limit for reporting spam has been reached. Try again later.
	214    Owner must allow dms from anyone.    Corresponsds with HTTP 403.  The user is not set up to have open Direct Messages when trying to set up a welcome message.
	215    Bad authentication data Corresponds with HTTP 400. The method requires authentication but it was not presented or was wholly invalid.
	220    Your credentials do not allow access to this resource.    Corresponds with HTTP 403. The authentication token in use is restricted and cannot access the requested resource.
	226    This request looks like it might be automated. To protect our users from spam and other malicious activity, we can’t complete this action right now.    Corresponds with HTTP 403. We constantly monitor and adjust our filters to block spam and malicious activity on the Twitter platform. These systems are tuned in real-time. If you get this response our systems have flagged the Tweet or Direct Message as possibly fitting this profile. If you believe that the Tweet or DM you attempted to create was flagged in error, report the details by filing a ticket at https://help.twitter.com/forms/platform.
	251    This endpoint has been retired and should not be used.    Corresponds with HTTP 410. The app made a request to a retired URL.
	261    Application cannot perform write actions.    Corresponds with HTTP 403. Caused by the app being restricted from POST, PUT, or DELETE actions. Check the information on your app dashboard. You may also file a ticket at https://help.twitter.com/forms/platform.
	271    You can’t mute yourself.    Corresponds with HTTP 403. The authenticated user account cannot mute itself.
	272    You are not muting the specified user.    Corresponds with HTTP 403. The authenticated user account is not muting the account a call is attempting to unmute.
	323    Animated GIFs are not allowed when uploading multiple images.    Corresponds with HTTP 400. Only one animated GIF may be attached to a single Tweet.
	324    The validation of media ids failed.    Corresponds with HTTP 400. There was a problem with the media ID submitted with the Tweet.
	325    A media id was not found.    Corresponds with HTTP 400. The media ID attached to the Tweet was not found.
	326    To protect our users from spam and other malicious activity, this account is temporarily locked.    Corresponds with HTTP 403. The user should log in to https://twitter.com to unlock their account before the user token can be used.
	327    You have already retweeted this Tweet.
	Corresponds with HTTP 403. The user cannot retweet the same Tweet more than once.
	349    You cannot send messages to this user.    Corresponds with HTTP 403. The sender does not have privileges to Direct Message the recipient.
	354    The text of your direct message is over the max character limit.    Corresponds with HTTP 403. The message size exceeds the number of characters permitted in a Direct Message.
	355    Subscription already exists.    Corresponds with HTTP 409 Conflict. Related to Account Activity API request to add a new subscription for an authenticated user.
	385    You attempted to reply to a Tweet that is deleted or not visible to you.    Corresponds with HTTP 403. A reply can only be sent with reference to an existing public Tweet.
	386    The Tweet exceeds the number of allowed attachment types.    Corresponds with HTTP 403. A Tweet is limited to a single attachment resource (media, Quote Tweet, etc.)
	407    The given URL is invalid.    Corresponds with HTTP 400. A URL included in the Tweet could not be handled. This may be because a non-ASCII URL could not be converted, or for other reasons.
	415    Callback URL not approved for this client application. Approved callback URLs can be adjusted in your application settings
	Corresponds with HTTP 403. The app callback URLs must be whitelisted via the app details page in the developer portal. Only approved callback URLs may be used by the Twitter app. See the Callback URL documentation.
	416    Invalid / suspended application
	Corresponds with HTTP 401. The app has been suspended and cannot be used with Sign-in with Twitter.
	417    Desktop applications only support the oauth_callback value 'oob'
	Corresponds with HTTP 401. The application is attempting to use out -of-band PIN-based OAuth, but a callback URL has been specified in the app settings.
	421    This Tweet is no longer available    Corresponds with HTTP 404. The Tweet cannot be retrieved. This may be for a number of reasons. Read about the Twitter Rules.
	422    This Tweet is no longer available because it violated the Twitter Rules.    Corresponds with HTTP 404. The Tweet is not available in the API. Read about the Twitter Rules.
	433    The original Tweet author restricted who can reply to this Tweet.
	*/
}