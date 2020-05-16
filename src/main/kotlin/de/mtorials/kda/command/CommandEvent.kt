package de.mtorials.kda.command

import de.mtorials.kda.exceptions.ChannelNotFound
import de.mtorials.kda.permissions.PermissionLevels
import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class CommandEvent(
    val messageReceivedEvent: GuildMessageReceivedEvent,
    val command: Command,
    val parameter: List<String>,
    val guild : Guild = messageReceivedEvent.guild,
    val userPermissionLevel: PermissionLevels,
    val context: CommandContext?
) {

    val event = this

    infix fun CommandEvent.answer(answer: String) = this.messageReceivedEvent.channel.sendMessage(answer).queue()
    infix fun CommandEvent.answer(answer: MessageEmbed) = this.messageReceivedEvent.channel.sendMessage(answer).queue()
    infix fun CommandEvent.answerPrivate(answer: String) = this.messageReceivedEvent.author.openPrivateChannel().complete().sendMessage(answer).queue()
    infix fun CommandEvent.answerPrivate(answer: MessageEmbed) = this.messageReceivedEvent.author.openPrivateChannel().complete().sendMessage(answer).queue()

    infix fun CommandEvent.hasRole(role: Role) = this.messageReceivedEvent.member?.roles?.contains(role) ?: false

    infix fun CommandEvent.textChannel(id: Long) : TextChannel = this.guild.getTextChannelById(id) ?: throw ChannelNotFound()
    infix fun CommandEvent.textChannel(name: String) : TextChannel = this.guild.getTextChannelsByName(name, true)[0] ?: throw ChannelNotFound()

    infix fun MessageChannel.send(message: String) = this.sendMessage(message).queue()
    infix fun MessageChannel.send(message: MessageEmbed) = this.sendMessage(message).queue()
}