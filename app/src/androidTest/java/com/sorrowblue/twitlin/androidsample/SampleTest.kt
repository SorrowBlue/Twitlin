package com.sorrowblue.twitlin.androidsample

import android.os.Bundle
import android.os.Parcel
import com.sorrowblue.twitlin.v2.objects.User
import java.io.Serializable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.junit.Test

class SampleTest {

    @Test
    fun userTest() {
        val data = User("", "", "", _createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toString())
        saveToAndRestoreFromParcel(data)
    }

    inline fun <reified T : Serializable> saveToAndRestoreFromParcel(value: T): T? {
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
}
