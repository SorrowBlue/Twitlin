/*
 * (c) 2021 SorrowBlue.
 */

package test

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import org.junit.Test

internal class ParcelableTest {

    @Test
    fun testDateTime() {
        val data = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val bundle = Bundle()
        bundle.putParcelable("key", TestObject(data))
        bundle.getParcelable<TestObject>("key")
    }
}

@Parcelize
@TypeParceler<LocalDateTime, LocalDateTimeParceler>
public data class TestObject(val dateTime: LocalDateTime) :
    Parcelable

public object LocalDateTimeParceler : Parceler<LocalDateTime> {
    override fun create(parcel: Parcel): LocalDateTime {
        return Instant.fromEpochMilliseconds(parcel.readLong())
            .toLocalDateTime(TimeZone.currentSystemDefault())
    }

    override fun LocalDateTime.write(parcel: Parcel, flags: Int) {
        parcel.writeLong(toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds())
    }
}
