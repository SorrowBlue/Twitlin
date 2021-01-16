/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Test

class WelcomeMessagesApiTest : AbstractTest {

    @Test
    fun listTest() = runBlocking {
        TwitterAPI.welcomeMessagesApi.list().resultLog()
    }

    @Test
    fun listRuleTest() = runBlocking {
        TwitterAPI.welcomeMessagesApi.listRule().resultLog()
    }

    @Test
    fun newTest() = runBlocking {
        TwitterAPI.welcomeMessagesApi.new(
            "Welcome message name",
            "text",
            quickReply = QuickReply(
                QuickReply.Type.OPTIONS,
                (1..4).map { QuickReply.Option("label$it", "description$it", "metadata$it") })
        ).resultLog()
    }

    @Test
    fun newRuleTest() = runBlocking {
        TwitterAPI.welcomeMessagesApi.newRule("1345260745752133639").resultLog()
    }

    @Test
    fun updateTest() = runBlocking {
        TwitterAPI.welcomeMessagesApi.update("1345260617335115782", "update text").resultLog()
    }

    @Test
    fun destroyTest() = runBlocking {
        TwitterAPI.welcomeMessagesApi.destroy("1345229369879396357").resultLog()
    }

    @Test
    fun destroyRuleTest() = runBlocking {
        TwitterAPI.welcomeMessagesApi.destroyRule("1345651167691374592").resultLog()
    }

    @Test
    fun showTest() = runBlocking {
        TwitterAPI.welcomeMessagesApi.show("1345260745752133639").resultLog()
    }

    @Test
    fun showRule() = runBlocking {
        TwitterAPI.welcomeMessagesApi.showRule("1345317235041075200").resultLog()
    }
}
