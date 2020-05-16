package de.mtorials.mtb

import de.mtorials.kda.command.Command
import de.mtorials.kda.command.CommandEvent
import de.mtorials.kda.message.InfixEmbedBuilder
import de.mtorials.kda.permissions.PermissionLevels

class ExampleCommand : Command(
    invoke = "ex",
    description = "This is an example command",
    permission = PermissionLevels.USER
) {

    override fun execute(event: CommandEvent) {
        event.sendMessage()
    }

    private fun CommandEvent.sendMessage() {
        event answer InfixEmbedBuilder {
            message title "This is an example command!"
        }.build()
    }
}
