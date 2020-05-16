package de.mtorials.kda.adapter

import de.mtorials.kda.ListenerContext
import de.mtorials.kda.MessageEventContext
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class KDAListenerAdapter(
    private val listenerContext: ListenerContext
) : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.author.isBot) return
        listenerContext.messageReceiveListener.forEach {
            MessageEventContext().it(event)
        }
    }

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        if (event.author.isBot) return
        listenerContext.messageGuildReceiveListener.forEach {
            MessageEventContext().it(event)
        }
    }
}