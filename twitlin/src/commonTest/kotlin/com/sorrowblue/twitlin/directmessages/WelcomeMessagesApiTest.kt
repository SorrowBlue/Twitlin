/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Test

class WelcomeMessagesApiTest : AbstractTest {

    @Test
    fun listTest() = runTest {
        TwitterAPI.welcomeMessagesApi.list().testResult()
    }

    @Test
    fun listRuleTest() = runTest {
        TwitterAPI.welcomeMessagesApi.listRule().testResult()
    }

    @Test
    fun newTest() = runTest {
        TwitterAPI.welcomeMessagesApi.new(
            "Welcome message name",
            "text",
            quickReply = QuickReply(
                QuickReply.Type.OPTIONS,
                (1..4).map { QuickReply.Option("label$it", "description$it", "metadata$it") })
        ).testResult()
    }

    @Test
    fun newRuleTest() = runTest {
        TwitterAPI.welcomeMessagesApi.newRule("1345260745752133639").testResult()
    }

    @Test
    fun updateTest() = runTest {
        TwitterAPI.welcomeMessagesApi.update("1345260617335115782", "update text").testResult()
    }

    @Test
    fun destroyTest() = runTest {
        TwitterAPI.welcomeMessagesApi.destroy("1345229369879396357").testResult()
    }

    @Test
    fun showTest() = runTest {
        TwitterAPI.welcomeMessagesApi.show("1345260745752133639").testResult()
    }

    @Test
    fun showRule() = runTest {
        TwitterAPI.welcomeMessagesApi.showRule("1345317235041075200").testResult()
    }
}
