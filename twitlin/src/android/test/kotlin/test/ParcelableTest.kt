package test

import android.os.Bundle
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.v2.objects.Tweet
import io.ktor.http.formUrlEncode
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.encodeToISOString
import kotlinx.datetime.toLocalDateTime
import org.junit.Test

internal class ParcelableTest {

    @Test
    fun testDateTime() {
        val data = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val bundle = Bundle()
        val tweet = Tweet(TweetId(""), "", _createdAt = data.encodeToISOString())
        bundle.putParcelable("key", tweet)
        bundle.getParcelable<Tweet>("key")
    }

    @Test
    fun testEncodeToISOString() {
        val data = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        println(data.encodeToISOString())
        println("2021-06-26T22:19:11Z")
        println(listOf("end_time" to data.encodeToISOString(), "test" to "2021-06-26T22:19:11Z").formUrlEncode())
    }
}
