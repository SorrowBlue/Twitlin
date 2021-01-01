//[twitlin](../index.md)/[com.sorrowblue.twitlin.directmessages](index.md)



# Package com.sorrowblue.twitlin.directmessages  


## Types  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.directmessages/DirectMessage///PointingToDeclaration/"></a>[DirectMessage](-direct-message/index.md)| <a name="com.sorrowblue.twitlin.directmessages/DirectMessage///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [DirectMessage](-direct-message/index.md)(**event**: [Event](-event/index.md))  <br><br><br>
| <a name="com.sorrowblue.twitlin.directmessages/DirectMessageEvent///PointingToDeclaration/"></a>[DirectMessageEvent](-direct-message-event/index.md)| <a name="com.sorrowblue.twitlin.directmessages/DirectMessageEvent///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [DirectMessageEvent](-direct-message-event/index.md)(**type**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **messageCreate**: [DirectMessageEvent.MessageCreate](-direct-message-event/-message-create/index.md))  <br><br><br>
| <a name="com.sorrowblue.twitlin.directmessages/DirectMessageRequest///PointingToDeclaration/"></a>[DirectMessageRequest](-direct-message-request/index.md)| <a name="com.sorrowblue.twitlin.directmessages/DirectMessageRequest///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [DirectMessageRequest](-direct-message-request/index.md)(**event**: [DirectMessageEvent](-direct-message-event/index.md))  <br><br><br>
| <a name="com.sorrowblue.twitlin.directmessages/DirectMessagesApi///PointingToDeclaration/"></a>[DirectMessagesApi](-direct-messages-api/index.md)| <a name="com.sorrowblue.twitlin.directmessages/DirectMessagesApi///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [DirectMessagesApi](-direct-messages-api/index.md)  <br><br><br>
| <a name="com.sorrowblue.twitlin.directmessages/Event///PointingToDeclaration/"></a>[Event](-event/index.md)| <a name="com.sorrowblue.twitlin.directmessages/Event///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Event](-event/index.md)(**type**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **id**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **created_timestamp**: LocalDateTime, **message_create**: [Event.MessageCreate](-event/-message-create/index.md))  <br><br><br>
| <a name="com.sorrowblue.twitlin.directmessages/PagingDirectMessage///PointingToDeclaration/"></a>[PagingDirectMessage](-paging-direct-message/index.md)| <a name="com.sorrowblue.twitlin.directmessages/PagingDirectMessage///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [PagingDirectMessage](-paging-direct-message/index.md)(**events**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Event](-event/index.md)>, **apps**: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [PagingDirectMessage.App](-paging-direct-message/-app/index.md)>)  <br><br><br>
| <a name="com.sorrowblue.twitlin.directmessages/QuickReply///PointingToDeclaration/"></a>[QuickReply](-quick-reply/index.md)| <a name="com.sorrowblue.twitlin.directmessages/QuickReply///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [QuickReply](-quick-reply/index.md)(**type**: [QuickReply.Type](-quick-reply/-type/index.md), **options**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[QuickReply.Option](-quick-reply/-option/index.md)>?)  <br>More info  <br>TODO  <br><br><br>

