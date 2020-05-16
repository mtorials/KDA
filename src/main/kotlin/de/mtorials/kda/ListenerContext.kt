package de.mtorials.kda

import de.mtorials.kda.command.CommandEvent
import de.mtorials.kda.command.CommandEventContext
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class ListenerContext {

    val messageReceiveListener: MutableList<MessageEventContext.(MessageReceivedEvent) -> Unit> = mutableListOf()
    val messageGuildReceiveListener: MutableList<MessageEventContext.(GuildMessageReceivedEvent) -> Unit> = mutableListOf()
    val commandListener: MutableList<CommandEvent.(CommandEvent) -> Unit> = mutableListOf()

    fun onMessageReceive(block: MessageEventContext.(MessageReceivedEvent) -> Unit) = messageReceiveListener.add(block)
    fun onGuildMessageReceive(block: MessageEventContext.(GuildMessageReceivedEvent) -> Unit) = messageGuildReceiveListener.add(block)
    fun onCommandEvent(block: CommandEvent.(CommandEvent) -> Unit) = commandListener.add(block)
}