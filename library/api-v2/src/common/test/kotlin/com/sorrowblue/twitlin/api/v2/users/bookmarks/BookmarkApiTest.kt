package com.sorrowblue.twitlin.api.v2.users.bookmarks

import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.oauth2.ProjectConfig
import com.sorrowblue.twitlin.api.v2.objects.Tweet
import com.sorrowblue.twitlin.api.v2.test.Api
import com.sorrowblue.twitlin.api.v2.test.shouldSuccess
import com.sorrowblue.twitlin.api.v2.users.bookmark.BookmarkApi
import com.sorrowblue.twitlin.core.objects.UserId
import io.kotest.core.spec.style.FunSpec

class BookmarkApiTest : FunSpec({

    tags(Api)

    val bookmarkApi = TwitlinApiV2.getApi<BookmarkApi>(ProjectConfig.oAuth2Client)

    test("bookmarkApi.get") {
        val userId = UserId("938122027231150081")
        bookmarkApi.get(UserId("938122027231150081"))
            .convertData { it.data.map(Tweet::id) }
            .onSuccess {
                bookmarkApi.remove(userId, it.data.first()).shouldSuccess()
                bookmarkApi.add(userId, it.data.first()).shouldSuccess()
            }.shouldSuccess()
    }
})
