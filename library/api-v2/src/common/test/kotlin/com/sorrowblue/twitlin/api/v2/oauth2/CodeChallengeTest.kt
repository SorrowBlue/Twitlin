package com.sorrowblue.twitlin.api.v2.oauth2

import com.sorrowblue.twitlin.api.v2.test.NonApiTest
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.util.InternalAPI

@InternalAPI
class CodeChallengeTest : FunSpec({

    tags(NonApiTest)

    test("\"abc\", the bit string (0x)616263 of length 24 bits.") {
        stringToSHA256Hex("abc") shouldBe "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad"
    }

    test("the empty string \"\", the bit string of length 0.") {
        stringToSHA256Hex("") shouldBe "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"
    }

    test("\"abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq\" (length 448 bits).") {
        stringToSHA256Hex("abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq") shouldBe "248d6a61d20638b8e5c026930c3e6039a33ce45964ff2167f6ecedd419db06c1"
    }

    test("\"abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmnhijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu\" (length 896 bits).") {
        stringToSHA256Hex("abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmnhijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu") shouldBe "cf5b16a778af8380036ce59e7b0492370b249b11e8f07a51afac45037afee9d1"
    }

    test("one million (1,000,000) repetitions of the character \"a\" (0x61).") {
        stringToSHA256Hex(List(1000000) { "a" }.joinToString("")) shouldBe "cdc76e5c9914fb9281a1c7e284d73e67f1809a48a497200e046d39ccc7112cd0"
    }
})

private suspend fun stringToSHA256Hex(value: String) =
    sha256(value.encodeToByteArray()).joinToString(separator = "") { eachByte -> eachByte.percentEncode() }

private fun Byte.percentEncode() = buildString(3) {
    val code = toInt() and 0xff
    append(hexDigitToChar(code shr 4))
    append(hexDigitToChar(code and 0x0f))
}

private fun hexDigitToChar(digit: Int) = when (digit) {
    in 0..9 -> '0' + digit
    else -> 'a' + digit - 10
}
