package de.mtorials.mtb

import de.mtorials.kda.KDA
import de.mtorials.kda.command.CommandContext
import de.mtorials.kda.message.InfixEmbedBuilder
import de.mtorials.kda.permissions.PermissionLevels

fun main() {
    KDA(
        name = "MTB",
        prefix = "%",
        token = "NDQyNjAzNzU0NTM0MjA3NDg5.XpmLmA.JrQrO2S0wP2-oPMXLMlDcGeNz7g"
    ).configure {
        listeners {
            onCommandEvent {
                event textChannel 464853248390594570 send "User ${it.messageReceivedEvent.author.name} invoked command ${it.command.invoke}"
            }
        }
        addCommand("context", "I demonstrate contexts", PermissionLevels.OWNER) {
            event answer (event.context as MyContext).iNeedThis
            event answerPrivate InfixEmbedBuilder {
                message title "Private"
                message description "This is a private embed message!"
            }.build()
        }
        addCommandContext {
            MyContext("Hi!")
        }
        addCommands(listOf(ExampleCommand()))
        installPing()
        installHelp()
        init()
    }
}

class MyContext(
    val iNeedThis: String
) : CommandContext()