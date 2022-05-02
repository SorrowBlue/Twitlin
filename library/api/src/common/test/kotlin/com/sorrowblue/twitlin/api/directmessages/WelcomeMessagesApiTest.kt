package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.test.shouldSuccess
import com.sorrowblue.twitlin.core.TwitlinConfig
import io.kotest.core.spec.style.FunSpec
import kotlinx.datetime.Clock
import kotlinx.datetime.encodeToISOString
import kotlinx.datetime.toLocalDateTime

class WelcomeMessagesApiTest : FunSpec({

    val welcomeMessagesApi = TwitlinApi.getApi<WelcomeMessagesApi>(oauth1aClient)

    test("welcomeMessagesApi.list") {
        welcomeMessagesApi.list()
            .shouldSuccess()
    }

    test("welcomeMessagesApi.listRule") {
        welcomeMessagesApi.listRule()
            .shouldSuccess()
    }

    test("welcomeMessagesApi.new quickReply") {
        welcomeMessagesApi.listRule().onSuccess {
            it.welcomeMessageRules.forEach {
                welcomeMessagesApi.destroyRule(it.id)
            }
        }
        welcomeMessagesApi.list().onSuccess {
            it.welcomeMessages.forEach {
                welcomeMessagesApi.destroy(it.id)
            }
        }
        val quickReply = QuickReply(
            QuickReply.Type.OPTIONS,
            listOf(
                QuickReply.Option("Github", "github", "twitlin_from_github"),
                QuickReply.Option("Twitter", "twitter", "twitlin_from_twitter"),
                QuickReply.Option("Website", "www", "twitlin_from_wibsite"),
                QuickReply.Option("Other.", "other", "twitlin_from_other"),
            )
        )
        welcomeMessagesApi.new(
            "twitlin Welcome message name", """
            Welcome to Twitlin.
            This message was created by testing
        """.trimIndent(), quickReply = quickReply
        ).onSuccess {
            welcomeMessagesApi.show(it.welcomeMessage.id).shouldSuccess()
            welcomeMessagesApi.newRule(it.welcomeMessage.id).onSuccess {
                welcomeMessagesApi.showRule(it.id).shouldSuccess()
            }.shouldSuccess()

            welcomeMessagesApi.update(
                it.welcomeMessage.id,
                """
                    Welcome to Twitlin.
                    This message was created by testing. ${
                    Clock.System.now().toLocalDateTime(TwitlinConfig.defaultTimeZone).encodeToISOString()
                }
                """.trimIndent(),
                quickReply = quickReply
            ).shouldSuccess()
        }.shouldSuccess()
    }
})
