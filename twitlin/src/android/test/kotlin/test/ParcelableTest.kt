/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import android.os.Bundle
import com.sorrowblue.twitlin.v2.objects.Tweet
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.junit.Test

internal class ParcelableTest {

    @Test
    fun testDateTime() {
        val data = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val bundle = Bundle()
        val tweet = Tweet("", "", createdAt = data)
        bundle.putParcelable("key", tweet)
        bundle.getParcelable<Tweet>("key")
    }
}
