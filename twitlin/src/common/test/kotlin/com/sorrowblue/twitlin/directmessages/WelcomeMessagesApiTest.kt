package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.Twitlin
import kotlin.test.Test
import test.AbstractTest
import test.resultLog

class WelcomeMessagesApiTest : AbstractTest {
    val welcomeMessagesApi = Twitlin.getApi<WelcomeMessagesApi>(oauth1aClient)

    @Test
    fun listTest() = runBlocking {
        welcomeMessagesApi.list().resultLog()
    }

    @Test
    fun listRuleTest() = runBlocking {
        welcomeMessagesApi.listRule().resultLog()
    }

    @Test
    fun newTest() = runBlocking {
        welcomeMessagesApi.new(
            "Welcome message name",
            "text",
            quickReply = QuickReply(
                QuickReply.Type.OPTIONS,
                (1..4).map { QuickReply.Option("label$it", "description$it", "metadata$it") }
            )
        ).resultLog()
    }

    @Test
    fun newRuleTest() = runBlocking {
        welcomeMessagesApi.newRule("1345260745752133639").resultLog()
    }

    @Test
    fun updateTest() = runBlocking {
        welcomeMessagesApi.update("1345260617335115782", "update text").resultLog()
    }

    @Test
    fun destroyTest() = runBlocking {
        welcomeMessagesApi.destroy("1345229369879396357").resultLog()
    }

    @Test
    fun destroyRuleTest() = runBlocking {
        welcomeMessagesApi.destroyRule("1345651167691374592").resultLog()
    }

    @Test
    fun showTest() = runBlocking {
        welcomeMessagesApi.show("1345260745752133639").resultLog()
    }

    @Test
    fun showRule() = runBlocking {
        welcomeMessagesApi.showRule("1345317235041075200").resultLog()
    }
}
