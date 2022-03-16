package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.api.TwitlinApi
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class WelcomeMessagesApiTest : AbstractTest {

    private val welcomeMessagesApi = TwitlinApi.getApi<WelcomeMessagesApi>(oauth1aClient)

    @Test
    fun listTest() = runTest { welcomeMessagesApi.list().resultLog() }

    @Test
    fun listRuleTest() = runTest { welcomeMessagesApi.listRule().resultLog() }

    @Test
    fun newTest() = runTest {
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
    fun newRuleTest() = runTest { welcomeMessagesApi.newRule("1345260745752133639").resultLog() }

    @Test
    fun updateTest() = runTest { welcomeMessagesApi.update("1345260617335115782", "update text").resultLog() }

    @Test
    fun destroyTest() = runTest { welcomeMessagesApi.destroy("1345229369879396357").resultLog() }

    @Test
    fun destroyRuleTest() = runTest { welcomeMessagesApi.destroyRule("1345651167691374592").resultLog() }

    @Test
    fun showTest() = runTest { welcomeMessagesApi.show("1345260745752133639").resultLog() }

    @Test
    fun showRule() = runTest { welcomeMessagesApi.showRule("1345317235041075200").resultLog() }
}
