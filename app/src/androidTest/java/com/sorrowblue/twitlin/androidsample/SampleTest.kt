package com.sorrowblue.twitlin.androidsample

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.v2.objects.User
import java.io.Serializable
import kotlinx.parcelize.Parcelize
import org.junit.Test

@JvmInline
@Parcelize
value class SpecialUuid(val uuid: Int) : Parcelable

@Parcelize
data class SomeDataClass(val uuid: SpecialUuid) : Parcelable, Serializable

class SampleTest {

    @Test
    fun userTest() {
        val data = User(UserId("513546515448"), "name", "username")
        Log.d(javaClass.simpleName, "data: ${saveToAndRestoreFromParcel(data)}")
        Log.d(javaClass.simpleName, "data: ${saveToAndRestoreFromSerial(data)}")
    }

    inline fun <reified T : Serializable> saveToAndRestoreFromSerial(value: T): T? {
        val key = "key"
        val p = Parcel.obtain()
        return try {
            val bundle = Bundle()
            bundle.putSerializable(key, value)
            p.writeBundle(bundle)
            p.setDataPosition(0)
            val restoredBundle = p.readBundle(value::class.java.classLoader)
            restoredBundle!!.getSerializable(key) as T
        } finally {
            p.recycle()
        }
    }

    inline fun <reified T : Parcelable> saveToAndRestoreFromParcel(value: T): T? {
        val key = "key"
        val p = Parcel.obtain()
        return try {
            val bundle = Bundle()
            bundle.putParcelable(key, value)
            p.writeBundle(bundle)
            p.setDataPosition(0)
            val restoredBundle = p.readBundle(value::class.java.classLoader)
            restoredBundle!!.getParcelable(key)
        } finally {
            p.recycle()
        }
    }
}
