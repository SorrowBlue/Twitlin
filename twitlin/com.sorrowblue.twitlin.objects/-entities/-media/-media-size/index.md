//[twitlin](../../../../index.md)/[com.sorrowblue.twitlin.objects](../../../index.md)/[Entities](../../index.md)/[Media](../index.md)/[MediaSize](index.md)



# MediaSize  
 [common] data class [MediaSize](index.md)(**thumb**: [Entities.Media.MediaSize.Size](-size/index.md), **large**: [Entities.Media.MediaSize.Size](-size/index.md), **medium**: [Entities.Media.MediaSize.Size](-size/index.md), **small**: [Entities.Media.MediaSize.Size](-size/index.md)) : [JvmSerializable](../../../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)

All Tweets with native media (photos, video, and GIFs) will include a set of [thumb](thumb.md), [small](small.md), [medium](medium.md), and [large](large.md) sizes with height and width pixel sizes. For photos and preview image media URLs, [Photo Media URL formatting](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/entities-object#photo_format) specifies how to construct different URLs for loading different sized photo media.

   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/MediaSize/#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size/PointingToDeclaration/"></a>[MediaSize](-media-size.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/MediaSize/#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size/PointingToDeclaration/"></a> [common] fun [MediaSize](-media-size.md)(thumb: [Entities.Media.MediaSize.Size](-size/index.md), large: [Entities.Media.MediaSize.Size](-size/index.md), medium: [Entities.Media.MediaSize.Size](-size/index.md), small: [Entities.Media.MediaSize.Size](-size/index.md))   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize.Size///PointingToDeclaration/"></a>[Size](-size/index.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize.Size///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Size](-size/index.md)(**w**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **h**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **resize**: [Entities.Media.MediaSize.Size.Resize](-size/-resize/index.md)) : [JvmSerializable](../../../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)  <br>More info  <br>Size object  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/component1/#/PointingToDeclaration/"></a>[component1](component1.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/component1/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component1](component1.md)(): [Entities.Media.MediaSize.Size](-size/index.md)  <br><br><br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/component2/#/PointingToDeclaration/"></a>[component2](component2.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/component2/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component2](component2.md)(): [Entities.Media.MediaSize.Size](-size/index.md)  <br><br><br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/component3/#/PointingToDeclaration/"></a>[component3](component3.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/component3/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component3](component3.md)(): [Entities.Media.MediaSize.Size](-size/index.md)  <br><br><br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/component4/#/PointingToDeclaration/"></a>[component4](component4.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/component4/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component4](component4.md)(): [Entities.Media.MediaSize.Size](-size/index.md)  <br><br><br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/copy/#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size/PointingToDeclaration/"></a>[copy](copy.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/copy/#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size#com.sorrowblue.twitlin.objects.Entities.Media.MediaSize.Size/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [copy](copy.md)(thumb: [Entities.Media.MediaSize.Size](-size/index.md), large: [Entities.Media.MediaSize.Size](-size/index.md), medium: [Entities.Media.MediaSize.Size](-size/index.md), small: [Entities.Media.MediaSize.Size](-size/index.md)): [Entities.Media.MediaSize](index.md)  <br><br><br>
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator override fun [equals](../../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [hashCode](../../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [toString](../../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/large/#/PointingToDeclaration/"></a>[large](large.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/large/#/PointingToDeclaration/"></a> [common] val [large](large.md): [Entities.Media.MediaSize.Size](-size/index.md)Information for a large-sized version of the media.   <br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/medium/#/PointingToDeclaration/"></a>[medium](medium.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/medium/#/PointingToDeclaration/"></a> [common] val [medium](medium.md): [Entities.Media.MediaSize.Size](-size/index.md)Information for a medium-sized version of the media.   <br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/small/#/PointingToDeclaration/"></a>[small](small.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/small/#/PointingToDeclaration/"></a> [common] val [small](small.md): [Entities.Media.MediaSize.Size](-size/index.md)Information for a small-sized version of the media.   <br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/thumb/#/PointingToDeclaration/"></a>[thumb](thumb.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Media.MediaSize/thumb/#/PointingToDeclaration/"></a> [common] val [thumb](thumb.md): [Entities.Media.MediaSize.Size](-size/index.md)Information for a thumbnail-sized version of the media.   <br>

