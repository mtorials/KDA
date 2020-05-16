package de.mtorials.kda

import de.mtorials.kda.command.Command
import net.dv8tion.jda.api.EmbedBuilder

class HelpCommand(
    commands: List<Command>
) : Command(
    invoke = "help",
    description = "This is the help command",
    onInvoke = {
        val builder = EmbedBuilder().setTitle("Help Command")
        commands.forEach{ command ->
            builder.addField(command.invoke, command.description, false)
        }
        event answer builder.build()
    }
)