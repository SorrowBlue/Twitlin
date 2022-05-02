package com.sorrowblue.twitlin.core.annotation

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.Test
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.WriteWith
import org.junit.runner.RunWith

@Parcelize
public data class AccessToken(internal val data: @WriteWith<InstantParceler> Instant) : Parcelable

@RunWith(AndroidJUnit4::class)
public class AndroidParcelableTest {

    @Test
    public fun test() {
        val value = AccessToken(Clock.System.now())
        val key = "key"
        val p = Parcel.obtain()
        try {
            val bundle = Bundle()
            bundle.putParcelable(key, value)
            p.writeBundle(bundle)
            p.setDataPosition(0)
            val restoredBundle = p.readBundle(value.javaClass.classLoader)
            restoredBundle!!.getParcelable<Parcelable>(key)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            p.recycle()
        }
    }
}

public object InstantParceler : Parceler<Instant> {
    override fun create(parcel: Parcel): Instant {
        val str = parcel.readString()
        require(str != null)
        return Instant.parse(str)
    }

    override fun Instant.write(parcel: Parcel, flags: Int) {
        parcel.writeString(this.toString())
    }
}
