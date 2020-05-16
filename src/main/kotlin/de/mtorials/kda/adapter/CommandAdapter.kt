package de.mtorials.kda.adapter

import de.mtorials.kda.*
import de.mtorials.kda.command.Command
import de.mtorials.kda.command.CommandContext
import de.mtorials.kda.command.CommandEvent
import de.mtorials.kda.config.GuildConfiguration
import de.mtorials.kda.permissions.PermissionLevels
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class CommandAdapter(
    private val listenerContext: ListenerContext,
    private val config: GuildConfiguration,
    private val commands: List<Command>,
    private val commandContext: CommandContext?
) : ListenerAdapter() {

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        if (event.author.isBot) return
        if (!event.message.contentRaw.startsWith(config.prefix)) return

        val list = event.message.contentRaw.removePrefix(config.prefix).split(" ").toMutableList()
        val invoke = list.removeAt(0)
        val filteredCommands: List<Command> = commands.filter { it.invoke == invoke }
        if (filteredCommands.isEmpty()) {
            event.channel.sendMessage(config.messageCommandNotFound).queue()
            return
        }
        val invokedCommand = filteredCommands[0]
        if (invokedCommand.permission > getPermissionLevel(event)) event.channel.sendMessage(config.messageCommandNotAllowed)
        val commandEvent = CommandEvent(
            command = invokedCommand,
            messageReceivedEvent = event,
            parameter = list,
            userPermissionLevel = getPermissionLevel(event),
            context = commandContext
        )
        listenerContext.commandListener.forEach {
            commandEvent.it(commandEvent)
        }
        filteredCommands[0].execute(commandEvent)
    }

    private fun getPermissionLevel(event: GuildMessageReceivedEvent) : PermissionLevels {
        if (event.member?.isOwner ?: return PermissionLevels.USER) return PermissionLevels.OWNER
        if (event.member?.permissions?.contains(Permission.ADMINISTRATOR) ?: return PermissionLevels.USER) return PermissionLevels.ADMIN
        if (event.member?.permissions?.contains(Permission.KICK_MEMBERS) ?: return PermissionLevels.USER) return PermissionLevels.MODERATOR
        return PermissionLevels.USER
    }
}