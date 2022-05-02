@file:Suppress("SpellCheckingInspection")

package com.sorrowblue.twitlin.api

import com.sorrowblue.twitlin.api.ktx.urlEncode
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class StringKtTest : FunSpec({

    test("UrlEncodeTwitterAPI_1") {
        "Ladies + Gentlemen".urlEncode() shouldBe "Ladies%20%2B%20Gentlemen"
    }

    test("UrlEncodeTwitterAPI_2") {
        "An encoded string!".urlEncode() shouldBe "An%20encoded%20string%21"
    }

    test("UrlEncodeTwitterAPI_3") {
        "Dogs, Cats & Mice".urlEncode() shouldBe "Dogs%2C%20Cats%20%26%20Mice"

    }

    test("UrlEncodeTwitterAPI_4") {
        "☃".urlEncode() shouldBe "%E2%98%83"
    }

    test("UrlEncodeUnreservedAlpha") {
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".urlEncode() shouldBe "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    }

    test("UrlEncodeUnreservedDigit") {
        "0123456789".urlEncode() shouldBe "0123456789"
    }

    test("UrlEncodeUnreservedSymbols") {
        "-._~".urlEncode() shouldBe "-._~"
    }

    test("UrlEncodeReservedCharacters") {
        """!"#$%&'()*+,/:;<=>?@[\]^`{|}""".urlEncode() shouldBe "%21%22%23%24%25%26%27%28%29%2A%2B%2C%2F%3A%3B%3C%3D%3E%3F%40%5B%5C%5D%5E%60%7B%7C%7D"
    }
})
