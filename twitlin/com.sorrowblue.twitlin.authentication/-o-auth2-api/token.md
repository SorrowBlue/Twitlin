//[twitlin](../../index.md)/[com.sorrowblue.twitlin.authentication](../index.md)/[OAuth2Api](index.md)/[token](token.md)



# token  
[common]  
Content  
abstract suspend fun [token](token.md)(): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[BearerToken](../-bearer-token/index.md)>  
More info  


Allows a registered application to obtain an OAuth 2 Bearer Token, which can be used to make API requests on an application's own behalf, without a user context. This is called [Application-only authentication](https://developer.twitter.com/en/docs/basics/authentication/overview/application-only).



A Bearer Token may be invalidated using oauth2/invalidate_token. Once a Bearer Token has been invalidated, new creation attempts will yield a different Bearer Token and usage of the previous token will no longer be allowed.



Only one bearer token may exist outstanding for an application, and repeated requests to this method will yield the same already-existent token until it has been invalidated.



Successful responses include a JSON-structure describing the awarded Bearer Token.



Tokens received by this method should be cached. If attempted too frequently, requests will be rejected with a HTTP 403 with code 99.



#### Return  
  



