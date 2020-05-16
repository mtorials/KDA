package de.mtorials.kda

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

open class MessageEventContext {

    infix fun MessageReceivedEvent.answer(answer: String) = this.channel.sendMessage(answer).queue()
    infix fun MessageReceivedEvent.answerPrivate(answer: String) = this.author.openPrivateChannel().complete().sendMessage(answer).queue()
    infix fun GuildMessageReceivedEvent.answer(answer: String) = this.channel.sendMessage(answer).queue()
    infix fun GuildMessageReceivedEvent.answerPrivate(answer: String) = this.author.openPrivateChannel().complete().sendMessage(answer).queue()
}