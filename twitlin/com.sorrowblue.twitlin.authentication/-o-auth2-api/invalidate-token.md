//[twitlin](../../index.md)/[com.sorrowblue.twitlin.authentication](../index.md)/[OAuth2Api](index.md)/[invalidateToken](invalidate-token.md)



# invalidateToken  
[common]  
Content  
abstract suspend fun [invalidateToken](invalidate-token.md)(): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[InvalidateToken](../-invalidate-token/index.md)>  
More info  


Allows a registered application to revoke an issued oAuth 2.0 Bearer Token by presenting its client credentials. Once a Bearer Token has been invalidated, new creation attempts will yield a different Bearer Token and usage of the invalidated token will no longer be allowed



Successful responses include a JSON-structure describing the revoked Bearer Token.



#### Return  


TODO

  



