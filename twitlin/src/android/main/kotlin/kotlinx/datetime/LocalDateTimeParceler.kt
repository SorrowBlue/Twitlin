/*
 * (c) 2020-2021 SorrowBlue.
 */

package kotlinx.datetime

import android.os.Parcel
import com.sorrowblue.twitlin.annotation.KotlinParceler

public actual object LocalDateTimeParceler : KotlinParceler<LocalDateTime> {
    override fun create(parcel: Parcel): LocalDateTime {
        return Instant.fromEpochMilliseconds(parcel.readLong())
            .toLocalDateTime(TimeZone.UTC)
    }

    override fun LocalDateTime.write(parcel: Parcel, flags: Int) {
        parcel.writeLong(toInstant(TimeZone.UTC).toEpochMilliseconds())
    }
}
