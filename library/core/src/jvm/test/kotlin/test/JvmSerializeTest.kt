package test

import com.sorrowblue.twitlin.core.objects.TweetId
import io.kotest.core.spec.style.FunSpec
import io.kotest.engine.spec.tempfile
import io.kotest.matchers.types.shouldBeInstanceOf
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JvmSerializeTest : FunSpec({

    test("WriteAndReadObject") {
        val data = TweetId("")
        val file = tempfile()
        withContext(Dispatchers.IO) {
            ObjectOutputStream(file.outputStream()).use {
                it.writeObject(data)
            }
            ObjectInputStream(file.inputStream()).use {
                it.readObject().shouldBeInstanceOf<TweetId>()
            }
        }
    }
})
