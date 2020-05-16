package de.mtorials.kda.command

import de.mtorials.kda.permissions.PermissionLevels

class StandardCommands {
    companion object {
        val ping = Command(
            "ping",
            "Simple ping command",
            PermissionLevels.USER
        ) {
            event answer "The Bot's ping is ${event.messageReceivedEvent.jda.gatewayPing}ms."
        }
    }
}